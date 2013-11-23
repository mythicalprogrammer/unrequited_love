name := "ModernMan"

version := "1.0-SNAPSHOT"

resolvers ++= Seq(
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)

resolvers += Resolver.url("sbt-plugin-releases",
  new URL("http://repo.scala-sbt.org/scalasbt/sbt-plugin-releases/"))(Resolver.ivyStylePatterns)

libraryDependencies ++= {
  val slickVersion = "1.0.0"
  val postgresqlVersion = "9.2-1002-jdbc4"
  val prettyTimeVersion = "3.2.1.Final"
  val jodaTimeVersion = "2.2"
  val slickJodaMapperVersion = "0.4.0"
  val securesocialVersion = "2.1.2"
  Seq(
      jdbc,
      anorm,
      cache,
      "com.typesafe.slick" %% "slick" % slickVersion,
      "org.postgresql" % "postgresql" % postgresqlVersion,
      "org.ocpsoft.prettytime" % "prettytime" % prettyTimeVersion,
      "joda-time" % "joda-time" % jodaTimeVersion,
      "com.github.tototoshi" %% "slick-joda-mapper" % slickJodaMapperVersion,
      "securesocial" %% "securesocial" % securesocialVersion
     )
}

play.Project.playScalaSettings
