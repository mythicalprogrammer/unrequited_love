import play.api.GlobalSettings

import play.api.db.DB
import play.api.Application
import play.api.Play.current

import scala.slick.driver.PostgresDriver.simple._
import Database.threadLocalSession



object Global extends GlobalSettings {
  override def onStart(app: Application) {
    lazy val database = Database.forDataSource(DB.getDataSource())
  }
}
