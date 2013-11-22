package controllers

import play.api._
import play.api.mvc._

import models.Comments

import play.api.Play.current

import play.api.libs.json._

object Comment extends Controller {

  def getComments = Action {
    Ok(Comments.findAllJSON).as(JSON)
  }

}
