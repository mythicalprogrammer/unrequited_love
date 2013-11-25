### Plugin and set up the default base first

app/controller/SecureViewPlugins.scala
```scala
package controllers


import play.api.mvc.{ RequestHeader, Request }
import play.api.templates.{Html, Txt}
import securesocial.controllers.Registration.RegistrationInfo
import securesocial.controllers.TemplatesPlugin
import securesocial.core.{ SecuredRequest, SocialUser, Identity }
import play.api.data.Form
import securesocial.core.SecureSocial._
import securesocial.controllers.PasswordChange.ChangeInfo


import play.api.{Logger, Plugin}
import play.api._
import play.api.mvc._
import play.api.data.Forms._
import play.api.data._

class SecureViewsPlugin(application: play.Application) extends TemplatesPlugin {
 /**
   * Returns the html for the login page
   * @param request
   * @tparam A
   * @return
   */
  override def getLoginPage[A](implicit request: Request[A], form: Form[(String, String)],
                               msg: Option[String] = None): Html =
  {
    securesocial.views.html.login(form, msg)
  }

  /**
   * Returns the html for the signup page
   *
   * @param request
   * @tparam A
   * @return
   */
  override def getSignUpPage[A](implicit request: Request[A], form: Form[RegistrationInfo], token: String): Html = {
    securesocial.views.html.Registration.signUp(form, token)
  }

  /**
   * Returns the html for the start signup page
   *
   * @param request
   * @tparam A
   * @return
   */
  override def getStartSignUpPage[A](implicit request: Request[A], form: Form[String]): Html = {
    securesocial.views.html.Registration.startSignUp(form)
  }
  

  /**
   * Returns the html for the reset password page
   *
   * @param request
   * @tparam A
   * @return
   */
  override def getStartResetPasswordPage[A](implicit request: Request[A], form: Form[String]): Html = {
    securesocial.views.html.Registration.startResetPassword(form)
  }

  /**
   * Returns the html for the start reset page
   *
   * @param request
   * @tparam A
   * @return
   */
  def getResetPasswordPage[A](implicit request: Request[A], form: Form[(String, String)], token: String): Html = {
    securesocial.views.html.Registration.resetPasswordPage(form, token)
  }

   /**
   * Returns the html for the change password page
   *
   * @param request
   * @param form
   * @tparam A
   * @return
   */
  def getPasswordChangePage[A](implicit request: SecuredRequest[A], form: Form[ChangeInfo]): Html = {
    securesocial.views.html.passwordChange(form)      
  }


  /**
   * Returns the email sent when a user starts the sign up process
   *
   * @param token the token used to identify the request
   * @param request the current http request
   * @return a String with the text and/or html body for the email
   */
  def getSignUpEmail(token: String)(implicit request: RequestHeader): (Option[Txt], Option[Html]) = {
    (None, Some(securesocial.views.html.mails.signUpEmail(token)))
  }

  /**
   * Returns the email sent when the user is already registered
   *
   * @param user the user
   * @param request the current request
   * @return a String with the text and/or html body for the email
   */
  def getAlreadyRegisteredEmail(user: securesocial.core.Identity)(implicit request: RequestHeader): (Option[Txt], Option[Html]) = {
    (None, Some(securesocial.views.html.mails.alreadyRegisteredEmail(user)))
  }

  /**
   * Returns the welcome email sent when the user finished the sign up process
   *
   * @param user the user
   * @param request the current request
   * @return a String with the text and/or html body for the email
   */
  def getWelcomeEmail(user: Identity)(implicit request: RequestHeader): (Option[Txt], Option[Html]) = {
    (None, Some(securesocial.views.html.mails.welcomeEmail(user)))
  }

  /**
   * Returns the email sent when a user tries to reset the password but there is no account for
   * that email address in the system
   *
   * @param request the current request
   * @return a String with the text and/or html body for the email
   */
  def getUnknownEmailNotice()(implicit request: RequestHeader): (Option[Txt], Option[Html]) = {
    (None, Some(securesocial.views.html.mails.unknownEmailNotice(request)))
  }

  /**
   * Returns the email sent to the user to reset the password
   *
   * @param user the user
   * @param token the token used to identify the request
   * @param request the current http request
   * @return a String with the text and/or html body for the email
   */
  def getSendPasswordResetEmail(user: Identity, token: String)(implicit request: RequestHeader): (Option[Txt], Option[Html]) = {
    (None, Some(securesocial.views.html.mails.passwordResetEmail(user, token)))
  }

  /**
   * Returns the email sent as a confirmation of a password change
   *
   * @param user the user
   * @param request the current http request
   * @return a String with the text and/or html body for the email
   */
  def getPasswordChangedNoticeEmail(user: Identity)(implicit request: RequestHeader): (Option[Txt], Option[Html]) = {
    (None, Some(securesocial.views.html.mails.passwordChangedNotice(user)))
  }

  /**
   * Returns the html of the Not Authorized page
   *
   * @param request the current http request
   * @return a String with the text and/or html body for the email
   */
  def getNotAuthorizedPage[A](implicit request: Request[A]): Html = {
     securesocial.views.html.notAuthorized()
  }

}
```

### Register the plugin

conf/playplugins.conf

Add this line
```scala
9998:controllers.SecureViewsPlugin
```

Remove this line
```scala
9997:securesocial.controllers.DefaultTemplatesPlugin
```

Here's an example of the full file:
```scala
1500:com.typesafe.plugin.CommonsMailerPlugin
9994:securesocial.core.DefaultAuthenticatorStore
9995:securesocial.core.DefaultIdGenerator
9996:securesocial.core.providers.utils.DefaultPasswordValidator
9998:controllers.SecureViewsPlugin
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

### Custom View (FINALLY)

Let's changed the signup page

app/controllers/SecureViewsPlugin.scala
```scala
override def getStartSignUpPage[A](implicit request: Request[A], form: Form[String]): Html = {             
     //securesocial.views.html.Registration.startSignUp(form)
     views.html.startSignUp(form)
}
```

app/views/startSignUp.scala.html
```scala
   @(startForm:Form[String])(implicit request: RequestHeader)
    <h1>hello world</h1>        
```

To see the origin securesocial views and start from there:
https://github.com/jaliss/securesocial/tree/master/module-code/app/securesocial/views