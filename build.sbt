name := "salesforce-developer-workshop-java"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  filters,
  javaJdbc,
  javaEbean,
  "org.postgresql" % "postgresql" % "9.3-1102-jdbc41",
  "org.webjars" % "jquery" % "2.1.1",
  "org.webjars" % "ratchet" % "2.0.2"
)
