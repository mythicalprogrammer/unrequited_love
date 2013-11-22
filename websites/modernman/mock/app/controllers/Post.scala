package controllers

import play.api._
import play.api.mvc._

import models.Posts

import play.api.Play.current

import play.api.libs.json._

object Post extends Controller {

  def getPosts = Action {
    Ok(Posts.findAllJSON).as(JSON)
  }

}
