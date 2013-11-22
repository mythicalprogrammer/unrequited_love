package models

import java.util.UUID
import com.github.tototoshi.slick.JodaSupport._
import org.joda.time.DateTime

import play.api.db._
import play.api.Play.current
import play.api.libs.json._


import slick.driver.PostgresDriver.simple._
import Database.threadLocalSession

case class Comment(
  id: UUID,
  comment_html: String,
  comment_text: String,
  owner: String,
  owner_id: UUID,
  reply_to: Option[UUID],
  post_id: UUID,
  last_update: Option[DateTime],
  created_on: DateTime,
  up: Option[Int],
  down: Option[Int])

object Comments extends Table[Comment]("comments") {

  lazy val database = Database.forDataSource(DB.getDataSource())

  def id = column[UUID]("id", O.PrimaryKey) // This is the primary key column
  def comment_html = column[String]("comment_html")
  def comment_text = column[String]("comment_text")
  def owner = column[String]("owner")
  def owner_id = column[UUID]("owner_id")
  def reply_to = column[Option[UUID]]("reply_to")
  def post_id = column[UUID]("post_id")
  def last_update = column[Option[DateTime]]("last_update")
  def created_on = column[DateTime]("created_on")
  def up = column[Option[Int]]("up")
  def down = column[Option[Int]]("down")

  implicit object CommentWrites extends Writes[Comment] {

    def writes(c: Comment) = Json.obj(
         "id" -> JsString(c.id.toString),
         "comment_html" -> JsString(c.comment_html),
         "comment_text" -> JsString(c.comment_text),
         "owner" -> JsString(c.owner),
         "owner_id" -> JsString(c.owner_id.toString),
         "reply_to" -> JsString(c.reply_to.getOrElse("").toString),
         "post_id" -> JsString(c.post_id.toString),
         "last_update" -> JsString(c.last_update.getOrElse("").toString),
         "created_on" -> JsString(c.created_on.toString),
         "up" -> JsNumber(c.up.getOrElse(0).toInt),
         "down" -> JsNumber(c.down.getOrElse(0).toInt)
    )
  }

  def * = id ~ comment_html ~ comment_text ~ owner ~ owner_id ~ reply_to ~
    post_id ~ last_update ~ created_on ~ up ~ down <>
    (Comment, Comment.unapply _)

  def findByPK(pk: UUID) =
    for (entity <- Comments if entity.id === pk) yield entity

  def findAll = database withSession {
    Query(Comments).list
  }

  def findAllJSON = database withSession {
    val comments = for (c <- Comments) yield c
    Json.toJson(comments.list)
  }

}
