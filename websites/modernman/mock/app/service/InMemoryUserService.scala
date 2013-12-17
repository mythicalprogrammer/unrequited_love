/**
 * Copyright 2012 Jorge Aliss (jaliss at gmail dot com) - twitter: @jaliss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package service

import play.api.{Logger, Application}
import securesocial.core._
import securesocial.core.providers.Token
import securesocial.core.IdentityId

import models.Users

/**
 * A Sample In Memory user service in Scala
 *
 * IMPORTANT: This is just a sample and not suitable for a production environment since
 * it stores everything in memory.
 */
class InMemoryUserService(application: Application) extends UserServicePlugin(application) {
  private var users = Map[String, Identity]()
  private var tokens = Map[String, Token]()

  def find(id: IdentityId): Option[Identity] = {
    if ( Logger.isDebugEnabled ) {
      Logger.debug("users = %s".format(users))
      //Logger.debug(id.userId)
      //Logger.debug(id.providerId)
    }
    //users.get(id.userId + id.providerId)
   id.providerId match {
      /*
      case "facebook" => {}
      case "google" => {}
      case "twitter" => {}
      */
      case "userpass" => {
        Logger.debug("Authenticate to postgresql implement here")

        val user = Users.getUserByEmail(id.userId)
        user match {
          case Nil => None
          case _ => {
            import securesocial.core.SocialUser
            val socialUser = new SocialUser(
                  securesocial.core.IdentityId(user.head.id.toString,id.providerId),
                  null,
                  null,
                  "",
                  Option(user.head.email),
                  None,
                  securesocial.core.AuthenticationMethod("userPassword"),
                  null,
                  null,
                  Some(PasswordInfo(
                        securesocial.core.providers.utils.PasswordHasher.BCryptHasher,
                        user.head.password)))
            Option(socialUser)
          }
        }


        //Logger.debug("typeOf: "+user.getClass)
        //Logger.debug(user.username)
        //Logger.debug("password"+user.password)
        //import org.mindrot.jbcrypt._
        //val pass = BCrypt.hashpw("easy123", BCrypt.gensalt(10))
        //Logger.debug("password: "+pass)

       // users.get(id.userId + id.providerId)
      }
    }
  }

  def findByEmailAndProvider(email: String, providerId: String): Option[Identity] = {
    if ( Logger.isDebugEnabled ) {
      Logger.debug("users = %s".format(users))
    }
    users.values.find( u => u.email.map( e => e == email && u.identityId.providerId == providerId).getOrElse(false))
  }

  def save(user: Identity): Identity = {
    Logger.debug("username: "+user.firstName)
    Logger.debug("user pass: "+user.passwordInfo.get.password.toString)
    Logger.debug("user email: "+user.email.get.toString)
    Users.newUser(
      user.firstName,
      user.passwordInfo.get.password.toString,
      user.email.get.toString)
    users = users + (user.identityId.userId + user.identityId.providerId -> user)
    //val user = Users.getUserByEmail(id.userId)
    // this sample returns the same user object, but you could return an instance of your own class
    // here as long as it implements the Identity trait. This will allow you to use your own class in the protected
    // actions and event callbacks. The same goes for the find(id: IdentityId) method.
    user
  }

  def save(token: Token) {
    tokens += (token.uuid -> token)
  }

  def findToken(token: String): Option[Token] = {
    tokens.get(token)
  }

  def deleteToken(uuid: String) {
    tokens -= uuid
  }

  def deleteTokens() {
    tokens = Map()
  }

  def deleteExpiredTokens() {
    tokens = tokens.filter(!_._2.isExpired)
  }
}
