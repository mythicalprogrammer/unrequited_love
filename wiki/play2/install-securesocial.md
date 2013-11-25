## Installation

### Create a new project

```bash
$ play new userplugin 
```

### Install Securesocial Library
build.sbt
```scala
name := "usertest"

version := "1.0-SNAPSHOT"

resolvers += Resolver.url("sbt-plugin-releases",
      new URL("http://repo.scala-sbt.org/scalasbt/sbt-plugin-releases/"))(Resolver.ivyStylePatterns)


libraryDependencies ++={
  val securesocialVersion = "2.1.2"
  Seq(
    jdbc,
    anorm,
    cache,
    "securesocial" %% "securesocial" % securesocialVersion
  )
}

play.Project.playScalaSettings
```

### Add the routes

conf/routes
```scala
# Routes
# This file defines all application routes (Higher priority routes first)
# ~~~~

# Home page
GET     /                           controllers.Application.index

# Map static resources from the /public folder to the /assets URL path
GET     /assets/*file               controllers.Assets.at(path="/public", file)

# Login page
GET     /login                      securesocial.controllers.LoginPage.login
GET     /logout                     securesocial.controllers.LoginPage.logout

# User Registration and password handling 
GET     /signup                     securesocial.controllers.Registration.startSignUp
POST    /signup                     securesocial.controllers.Registration.handleStartSignUp
GET     /signup/:token              securesocial.controllers.Registration.signUp(token)
POST    /signup/:token              securesocial.controllers.Registration.handleSignUp(token)
GET     /reset                      securesocial.controllers.Registration.startResetPassword
POST    /reset                      securesocial.controllers.Registration.handleStartResetPassword
GET     /reset/:token               securesocial.controllers.Registration.resetPassword(token)
POST    /reset/:token               securesocial.controllers.Registration.handleResetPassword(token)
GET     /password                   securesocial.controllers.PasswordChange.page
POST    /password                   securesocial.controllers.PasswordChange.handlePasswordChange

# Providers entry points
GET     /authenticate/:provider     securesocial.controllers.ProviderController.authenticate(provider)
POST    /authenticate/:provider     securesocial.controllers.ProviderController.authenticateByPost(provider)
GET     /not-authorized             securesocial.controllers.ProviderController.notAuthorized
```

### Register the Plugin in Conf
conf/play.plugins
```scala
1500:com.typesafe.plugin.CommonsMailerPlugin
9994:securesocial.core.DefaultAuthenticatorStore
9995:securesocial.core.DefaultIdGenerator
9996:securesocial.core.providers.utils.DefaultPasswordValidator
9997:securesocial.controllers.DefaultTemplatesPlugin
#9998:controllers.SecureViewsPlugin
9999:service.InMemoryUserService
10000:securesocial.core.providers.utils.BCryptPasswordHasher
10001:securesocial.core.providers.TwitterProvider
10002:securesocial.core.providers.FacebookProvider
10003:securesocial.core.providers.GoogleProvider
10004:securesocial.core.providers.LinkedInProvider
10005:securesocial.core.providers.UsernamePasswordProvider
10006:securesocial.core.providers.GitHubProvider
10007:service.MyEventListener
10008:securesocial.core.providers.FoursquareProvider
10009:securesocial.core.providers.XingProvider
100010:securesocial.core.providers.VkProvider
100011:securesocial.core.providers.InstagramProvider
```

### Configure SecureSocial

conf/application.conf
```scala
application.secret="Bs97PV_GKWpKTf2ACcaj3t9`YSyMIFMk@Zw^y@wBY:E=`dubQSHWjEMQoqcCAnQu"
application.langs="en"

# Database configuration
db.default.driver=org.postgresql.Driver
db.default.url="jdbc:postgresql://localhost:5432/modernman"
db.default.user=postgres
db.default.password=easy123

# SecureSocial Configuration
include "securesocial.conf"
```

conf/securesocial.conf
```scala
#####################################################################################
#
# SecureSocial 2 Settings
#
#####################################################################################

smtp {
        #host=smtp.gmail.com
        host=smtp.example.com
        #port=25
        ssl=true
        user="your_user"
        password=your_password
        from="your_from_address"
}

securesocial {
        #
        # If enabled, sets the path to your copy of Bootstrap.css to be used instead of the default one provided by SecureSocial
        #
        #bootstrapCssPath="your path"


        #
        # If enabled, sets the Favicon to display when in SecureSocial pages
        #
        #faviconPath="your path"


        #
        # If enabled, sets the path to your copy of JQuery to be used instead of the default one provided by SecureSocial
        #
        #jqueryPath="your path"


        #
        # If enabled, injects this Css file into all SecureSocial pages, allowing for minor style customizations
        # If you want to do major changes, please read about custom templates in SecureSocial
        #
        #customCssPath="your path"

        #
        # Where to redirect the user if SecureSocial can't figure that out from
        # the request that led the use to the login page
        #
        onLoginGoTo=/

        #
        # Where to redirect the user when he logs out. If not set SecureSocial will redirect to the login page
        #
        onLogoutGoTo=/login

        #
        # Where to redirect the user when he/she starts the signup process.
        # If not set SecureSocial will redirect to the login page
        #
        #onStartSignUpGoTo=/login

        #
        # Where to redirect the user when he/she signs up.
        # If not set SecureSocial will redirect to the login page
        #
        #onSignUpGoTo=/login

        #
        # Where to redirect the user when he starts the password reset process.
        # If not set SecureSocial will redirect to the login page
        #
        #onStartResetPasswordGoTo=/login

        #
        # Where to redirect the user when he resets his/her password.
        # If not set SecureSocial will redirect to the login page
        #
        #onResetPasswordGoTo=/login

        #
        # Enable SSL for oauth callback urls, login/signup/password recovery pages and the authenticator cookie
        #
        ssl=false


        #
        # Parameters for the cookie used to track users.
        #
        cookie {
                #
                # The cookie name (defaults to 'id')
                #name=id

                #
                # The path for which the cookie should be sent by the browser (defaults to /)
                #
                #path=/

                #
                # The domain for which the cookie should be sent (it is left empty by default)
                #
                #domain=some_domain

                #
                # If set to true, the cookie is not readable by a client side script (defaults to true).
                #
                #httpOnly=true

                #
                # The amount of time the session id will remain valid since the last request
                #
                idleTimeoutInMinutes=30

                #
                # The amount of time the session id will be valid since the user authenticated.
                # After this the user will need to re-authenticate
                #
                absoluteTimeOutInMinutes=720
        }

        twitter {
                requestTokenUrl="https://twitter.com/oauth/request_token"
                accessTokenUrl="https://twitter.com/oauth/access_token"
                authorizationUrl="https://twitter.com/oauth/authenticate"
                consumerKey=your_consumer_key
                consumerSecret=your_consumer_secret
        }

        facebook {
                authorizationUrl="https://graph.facebook.com/oauth/authorize"
                accessTokenUrl="https://graph.facebook.com/oauth/access_token"
                clientId=your_client_id
                clientSecret=your_client_secret
                # this scope is the minimum SecureSocial requires.  You can add more if required by your app.
                scope=email
        }

        google {
                authorizationUrl="https://accounts.google.com/o/oauth2/auth"
                accessTokenUrl="https://accounts.google.com/o/oauth2/token"
                clientId=your_client_id
                clientSecret=your_client_secret
                scope="https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email"
        }

        linkedin {
                requestTokenUrl="https://api.linkedin.com/uas/oauth/requestToken"
                accessTokenUrl="https://api.linkedin.com/uas/oauth/accessToken"
                authorizationUrl="https://api.linkedin.com/uas/oauth/authenticate"
                consumerKey=your_consumer_key
                consumerSecret=your_consumer_secret
        }

        github {
                authorizationUrl="https://github.com/login/oauth/authorize"
                accessTokenUrl="https://github.com/login/oauth/access_token"
                clientId=your_client_id
                clientSecret=your_client_secret
        }

        foursquare {
                authorizationUrl="https://foursquare.com/oauth2/authenticate"
                accessTokenUrl="https://foursquare.com/oauth2/access_token"
                clientId=your_client_id
                clientSecret=your_client_secret
        }

        xing {
                requestTokenUrl="https://api.xing.com/v1/request_token"
                accessTokenUrl="https://api.xing.com/v1/access_token"
                authorizationUrl="https://api.xing.com/v1/authorize"
                consumerKey=your_consumer_key
                consumerSecret=your_consumer_secret
        }

        instagram {
                authorizationUrl="https://api.instagram.com/oauth/authorize"
                accessTokenUrl="https://api.instagram.com/oauth/access_token"
                clientId=your_client_id
                clientSecret=your_client_secret
        }

        vk {
                authorizationUrl="http://oauth.vk.com/authorize"
                accessTokenUrl="https://oauth.vk.com/access_token"
                clientId=your_client_id
                clientSecret=your_client_secret
        }

        userpass {
                #
                # Enable username support, otherwise SecureSocial will use the emails as user names
                #
                withUserNameSupport=false
                sendWelcomeEmail=true
                enableGravatarSupport=true
                tokenDuration=60
                tokenDeleteInterval=5
                signupSkipLogin=false
        }
}
```

### InMemoryUserService

app/service/InMemoryUserService.scala

```scala
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
    }
    users.get(id.userId + id.providerId)
  }

  def findByEmailAndProvider(email: String, providerId: String): Option[Identity] = {
    if ( Logger.isDebugEnabled ) {
      Logger.debug("users = %s".format(users))
    }
    users.values.find( u => u.email.map( e => e == email && u.identityId.providerId == providerId).getOrElse(false))
  }

  def save(user: Identity): Identity = {
    users = users + (user.identityId.userId + user.identityId.providerId -> user)
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
```

### MyEventListener

app/service/MyEventListener.scala

```scala
package service

import securesocial.core._
import play.api.mvc.{Session, RequestHeader}
import play.api.{Application, Logger}

/**
 * A sample event listener
 */
class MyEventListener(app: Application) extends EventListener {
  override def id: String = "my_event_listener"

  def onEvent(event: Event, request: RequestHeader, session: Session): Option[Session] = {
    val eventName = event match {
      case e: LoginEvent => "login"
      case e: LogoutEvent => "logout"
      case e: SignUpEvent => "signup"
      case e: PasswordResetEvent => "password reset"
      case e: PasswordChangeEvent => "password change"
    }

    Logger.info("traced %s event for user %s".format(eventName, event.user.fullName))
    // Not changing the session so just return None
    // if you wanted to change the session then you'd do something like
    // Some(session + ("your_key" -> "your_value"))
    None
  }
}
```

### Change CSS and Javascript

conf/securesocial.conf
```scala
securesocial.bootstrapCssPath="stylesheets/bootstrap.min.css"
securesocial.jqueryPath="javascripts/jquery-2.0.3.min.js"
```