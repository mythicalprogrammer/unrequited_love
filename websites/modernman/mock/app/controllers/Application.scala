package controllers

import play.api._
import play.api.mvc._

import models.Posts

import play.api.data.Form

import play.api.data._
import play.api.data.Forms._

import play.api.Play.current

object Application extends Controller {

  def index = Action {
    Ok(views.html.index(Posts.findAll))
  }

  def signin = Action {
    Ok(views.html.user.signin())
  }

}
