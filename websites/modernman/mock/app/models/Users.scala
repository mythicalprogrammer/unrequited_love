package models

import java.util.UUID
import com.github.tototoshi.slick.JodaSupport._
import org.joda.time.DateTime
import org.mindrot.jbcrypt.BCrypt


import play.api.db._
import play.api.Play.current
import play.api.libs.json._


import slick.driver.PostgresDriver.simple._
import Database.threadLocalSession

case class User(
    id: UUID,
    username: String,
    password: String,
    email: String,
    comment_score_down: Option[Int],
    comment_score_up: Option[Int],
    post_score_down: Option[Int],
    post_score_up: Option[Int],
    created_on: Option[DateTime])

  object Users extends Table[User]("users") {

    lazy val database = Database.forDataSource(DB.getDataSource())

      def id = column[UUID]("id", O.PrimaryKey) // This is the primary key column
      def username = column[String]("username")
      def password = column[String]("password")
      def email = column[String]("email")
      def comment_score_down = column[Option[Int]]("comment_score_down")
      def comment_score_up = column[Option[Int]]("comment_score_up")
      def post_score_down = column[Option[Int]]("post_score_down")
      def post_score_up = column[Option[Int]]("post_score_up")
      def created_on = column[Option[DateTime]]("created_on")

      implicit object UserWrites extends Writes[User] {

        def writes(u: User) = Json.obj(
            "id" -> JsString(u.id.toString),
            "username" -> JsString(u.username),
            "password" -> JsString(u.password),
            "email" -> JsString(u.email),
            "comment_score_down" -> JsNumber(u.comment_score_down.getOrElse(0).toInt),
            "comment_score_up" -> JsNumber(u.comment_score_up.getOrElse(0).toInt),
            "post_score_down" -> JsNumber(u.post_score_down.getOrElse(0).toInt),
            "post_score_up" -> JsNumber(u.post_score_up.getOrElse(0).toInt),
            "created_on" -> JsString(u.created_on.getOrElse("").toString)
            )
      }

    def * = id ~ username ~ password ~ email ~ comment_score_down ~
      comment_score_up ~ post_score_down ~ post_score_up ~ created_on <>
      (User, User.unapply _)

      def findByPK(pk: UUID) = database withSession {
        val user = for (u <- Users if u.id === pk) yield u
          Json.toJson(user.list)
      }

    def getPasswordByEmail(email: String) = database withSession {
      val user = for (u <- Users if u.email === email) yield u
        //user.list.head.password
        user.list
    }

    def getUserByEmail(email: String) = database withSession {
      (for (u <- Users if u.email === email) yield u).list
    }

    def findAll = database withSession {
      Query(Users).list
    }

    def findAllJSON = database withSession {
      val users = for (u <- Users) yield u
        Json.toJson(users.list)
    }

    def newUser(
        username: String,
        password: String,
        email: String) = database withSession {


      Users.insert(new User(
        UUID.randomUUID(),
        username,
        BCrypt.hashpw(password, BCrypt.gensalt(10)),
        email,
        Option(0),
        Option(0),
        Option(0),
        Option(0),
        Option(DateTime.now)
      ))

    }

  }
