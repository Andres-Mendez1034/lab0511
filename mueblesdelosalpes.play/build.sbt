ThisBuild / scalaVersion := "2.13.16"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := "mueblesdelosalpes-play",
    version := "1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      guice,
      "com.typesafe.play" %% "play-json" % "2.10.7"
    )
  )
