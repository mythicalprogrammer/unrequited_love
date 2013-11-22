## build.sbt

```scala
name := "mock"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "com.typesafe.slick" %% "slick" % "1.0.0",
  "org.postgresql" % "postgresql" % "9.2-1002-jdbc4"
)

play.Project.playScalaSettings
```

## Global file

### app/Global.scala

```scala
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
```