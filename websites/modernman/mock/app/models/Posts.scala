package models

import java.util.UUID
import com.github.tototoshi.slick.JodaSupport._
import org.joda.time.DateTime
import java.net.URI

import play.api.db._
import play.api.Play.current
import play.api.libs.json._


import slick.driver.PostgresDriver.simple._
import Database.threadLocalSession

case class Post(
  id: UUID,
  domain: URI,
  media_embed: Option[String],
  media_type: Option[String],
  post_html: Option[String],
  post_text: Option[String],
  owner: String,
  owner_id: UUID,
  over_18: Boolean,
  thumbnail: Option[URI],
  last_update: Option[DateTime],
  created_on: DateTime,
  url: URI,
  title: String,
  downs: Option[Int],
  ups: Option[Int],
  tags: Option[String]) {

  lazy val database = Database.forDataSource(DB.getDataSource())

  def createdOnPretty = {
    import org.ocpsoft.prettytime.PrettyTime
    import java.sql.Timestamp
    val p = new PrettyTime
    p.format(new Timestamp(created_on.getMillis))
  }

  def commentsTotal = database withSession {
    import models.Comments
    (for { c <- Comments if (c.post_id === id)} yield (c)).list.length
  }

  def score = ups.getOrElse(0) - downs.getOrElse(0)

}

object Posts extends Table[Post]("posts") {

  lazy val database = Database.forDataSource(DB.getDataSource())

  import scala.slick.lifted.MappedTypeMapper.base
  import scala.slick.lifted.TypeMapper

  implicit val URITypeMapper: TypeMapper[URI] = base[URI, String](
      uri =>  uri.toString,
      str => new URI(str)
    )

  def id = column[UUID]("id", O.PrimaryKey) // This is the primary key column
  def domain = column[URI]("domain")
  def media_embed = column[Option[String]]("media_embed")
  def media_type = column[Option[String]]("media_type")
  def post_html = column[Option[String]]("post_html")
  def post_text = column[Option[String]]("post_text")
  def owner = column[String]("owner")
  def owner_id = column[UUID]("owner_id")
  def over_18 = column[Boolean]("over_18")
  def thumbnail = column[Option[URI]]("thumbnail")
  def last_update = column[Option[DateTime]]("last_update")
  def created_on = column[DateTime]("created_on")
  def url = column[URI]("url")
  def title = column[String]("title")
  def downs = column[Option[Int]]("downs")
  def ups = column[Option[Int]]("ups")
  def tags = column[Option[String]]("tags")

  def * = id ~ domain ~ media_embed ~ media_type ~ post_html ~ post_text ~
    owner ~ owner_id ~ over_18 ~ thumbnail ~ last_update ~ created_on ~ url ~
    title ~ downs ~ ups ~ tags <> (Post, Post.unapply _)


  def user = foreignKey("USER_FK", owner_id, Users)(_.id)

  implicit object PostWrites extends Writes[Post] {

    def writes(p: Post) = Json.obj(
         "id" -> JsString(p.id.toString),
         "domain" -> JsString(p.domain.toString),
         "media_embed" -> JsString(p.media_embed.getOrElse("")),
         "media_type" -> JsString(p.media_type.getOrElse("")),
         "post_html" -> JsString(p.post_html.getOrElse("")),
         "post_text" -> JsString(p.post_text.getOrElse("")),
         "owner" -> JsString(p.owner),
         "owner_id" -> JsString(p.owner_id.toString),
         "over_18" -> JsBoolean(p.over_18),
         "thumbnail" -> JsString(p.thumbnail.getOrElse("").toString),
         "last_update" -> JsString(p.last_update.getOrElse("").toString),
         "created_on" -> JsString(p.created_on.toString),
         "url" -> JsString(p.url.toString),
         "title" -> JsString(p.title),
         "downs" -> JsNumber(p.downs.getOrElse(0).toInt),
         "ups" -> JsNumber(p.ups.getOrElse(0).toInt),
         "tags" -> JsString(p.tags.getOrElse(""))
    )
  }

  def findByPK(pk: UUID) =
    for (entity <- Posts if entity.id === pk) yield entity

  def findAll = database withSession {
    Query(Posts).list
  }

  def findAllJSON = database withSession {
    val posts = for (p <- Posts) yield p
    Json.toJson(posts.list)
  }

}
