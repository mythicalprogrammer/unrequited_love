package controllers

import play.api._
import play.api.mvc._
import securesocial.core.{Identity, Authorization}

import models.Posts

import play.api.Play.current

object Application extends Controller with securesocial.core.SecureSocial {

  def index = Action {
    Ok(views.html.index(Posts.findAll))
  }

/*
  def index = SecuredAction { implicit request =>
    //Ok(views.html.index(request.user))
    Ok(views.html.index(Posts.findAll))
  }
  */

  def signin = Action {
    Ok(views.html.user.signin())
  }

}
