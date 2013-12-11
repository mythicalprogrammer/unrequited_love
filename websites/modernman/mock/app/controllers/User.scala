package controllers

import play.api._
import play.api.mvc._

import models.Users
import java.util.UUID

import play.api.Play.current

import play.api.libs.json._

object User extends Controller {

  def getUsers = Action {
    Ok(Users.findAllJSON).as(JSON)
  }

  def getUserByID(id: UUID) = Action {
    Ok(Users.findByPK(id)).as(JSON)
  }

}
