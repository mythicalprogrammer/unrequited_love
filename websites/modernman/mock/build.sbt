name := "ModernMan"

version := "1.0-SNAPSHOT"

resolvers ++= Seq(
  "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
  "Sonatype public" at "http://oss.sonatype.org/content/groups/public/",
  "Sonatype" at "http://nexus.scala-tools.org/content/repositories/public"
)

libraryDependencies ++= {
  val slickVersion = "1.0.0"
  val postgresqlVersion = "9.2-1002-jdbc4"
  val prettyTimeVersion = "3.2.1.Final"
  val jodaTimeVersion = "2.2"
  val slickJodaMapperVersion= "0.4.0"
  Seq(
      jdbc,
      anorm,
      cache,
      "com.typesafe.slick" %% "slick" % slickVersion,
      "org.postgresql" % "postgresql" % postgresqlVersion,
      "org.ocpsoft.prettytime" % "prettytime" % prettyTimeVersion,
      "joda-time" % "joda-time" % jodaTimeVersion,
      "com.github.tototoshi" %% "slick-joda-mapper" % slickJodaMapperVersion
     )
}

play.Project.playScalaSettings
