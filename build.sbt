val jsonLibraryName: String = "LiCOS-JSON4Scala"

name := jsonLibraryName

//organization := "online.licos"

//Compile / doc / sources := Seq.empty

resolvers += Resolver.sonatypeRepo("snapshots")

lazy val javaVersion: String = "8"

lazy val scalafmtSettings = Seq(
  scalafmtOnCompile := true,
  version := "3.4.0"
)

lazy val commonSettings = Seq(
  scalaVersion := "2.13.8",
  organization := "online.licos",
  run / fork := true,
  semanticdbEnabled := true,
  semanticdbVersion := scalafixSemanticdb.revision // only required for Scala 2.x
) ++ {
  scalacOptions ++= Seq(
    "-deprecation",
    "-encoding",
    "UTF-8", // yes, this is 2 args
    "-feature",
    "-language:implicitConversions",
    "-unchecked",
    "-Xlint",
    "-Xsource:3",
    s"-target:jvm-1.$javaVersion"
  )
  scalacOptions ++= (if (scalaVersion.value.startsWith("3")) Seq("-explain-types", "-Ykind-projector")
  else Seq("-explaintypes",  "-Wunused"))
} ++ {
  javacOptions ++= Seq(
    "-source",
    javaVersion,
    "-target",
    javaVersion,
    "-Xlint:unchecked",
    "-Xdoclint:accessibility,reference,syntax"
  )
}

val fileProtocol: String = "file://"
val mavenRepo:    String = "maven-repo"
val snapshotsStr: String = "snapshots"
val releasesStr:  String = "releases"

def getPublishTo(isSnapshot: Boolean, n: String): Option[Resolver] = {
  val version: String = {
    if (isSnapshot) {
      snapshotsStr
    } else {
      releasesStr
    }
  }

  Option(
    Resolver.file(
      s"$n-$version-repository",
      file(s"$mavenRepo/$version")
    )
  )
}
val licensesTemplate = Seq(
  "Apache License Version 2.0" -> url(
    "https://raw.githubusercontent.com/besna-institute/LiCOS-JSON4Scala/master/LICENSE"
  )
)
val homepageTemplate = Some(url("https://github.com/besna-institute/LiCOS-JSON4Scala"))
val pomExtraTemplate = {
  <scm>
    <url>git@github.com:besna-institute/LiCOS-JSON4Scala.git</url>
    <connection>scm:git@github.com:besna-institute/LiCOS-JSON4Scala.git</connection>
  </scm>
    <developers>
      <developer>
        <id>besna-institute</id>
        <name>sakamoto.github@besna.institute</name>
        <url>https://www.besna.institute</url>
      </developer>
    </developers>
}

lazy val json = (project in file("."))
  .settings(commonSettings: _*)
  .settings(scalafmtSettings: _*)
  .settings(
    Compile / doc / scalacOptions ++= Seq(
      "-groups",
      "-implicits",
      "-doc-root-content",
      (Compile / sourceDirectory).value + "/rootdoc.txt"
    ),
    autoAPIMappings := true
  )
  .settings(
    isSnapshot := true,
    version := "0.6.2",
    name := jsonLibraryName,
    publishMavenStyle := true,
    Test / publishArtifact := false,
    ThisBuild / versionScheme := Some("early-semver"),
    pomIncludeRepository := { _ => false },
    publishTo := getPublishTo(isSnapshot.value, name.value),
    licenses := licensesTemplate,
    homepage := homepageTemplate,
    pomExtra := pomExtraTemplate
  )
  .settings(
    libraryDependencies ++= {
      Seq(
        "com.typesafe.play" %% "play-json" % "2.9.2",
        "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
        "org.slf4j" % "slf4j-api" % "1.7.35" % Compile,
        "ch.qos.logback" % "logback-classic" % "1.2.10",
        "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4",
        "org.typelevel" %% "cats-core" % "2.7.0",
      )
    }
  )

json / Test / classLoaderLayeringStrategy := ClassLoaderLayeringStrategy.ScalaLibrary
json / Test / classLoaderLayeringStrategy := ClassLoaderLayeringStrategy.Flat
