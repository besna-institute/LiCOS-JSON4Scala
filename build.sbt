val jsonLibraryName: String = "LiCOS-JSON4Scala"

name := jsonLibraryName

//organization := "online.licos"

//maintainer in Linux := "Kotaro Sakamoto <sakamoto-kotaro-pn@ynu.jp>"

sources in (Compile, doc) := Seq.empty

resolvers += Resolver.sonatypeRepo("snapshots")

lazy val javaVersion: String = "1.8"

lazy val commonSettings = Seq(
  scalaVersion := "2.12.8",
  organization := "online.licos",
  fork in run := true
) ++ {
  scalacOptions ++= Seq(
    "-deprecation",
    "-encoding", "UTF-8", // yes, this is 2 args
    "-feature",
    "-language:implicitConversions",
    "-unchecked",
    "-Xlint",
    s"-target:jvm-$javaVersion"
  )
} ++ {
  javacOptions ++= Seq(
    "-source", javaVersion,
    "-target", javaVersion
  )
}

val fileProtocol: String = "file://"
val mavenRepo: String = "maven-repo"
val snapshotsStr: String = "snapshots"
val releasesStr: String = "releases"

def getPublishTo(isSnapshot: Boolean, f: String, n: String): Option[Resolver] = {
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
      file(s"$f/$mavenRepo/$version")
    )
  )
}
val licensesTemplate = Seq(
  "Apache License Version 2.0" -> url("https://raw.githubusercontent.com/ktr-skmt/LiCOS-JSON4Scala/master/LICENSE"))
val homepageTemplate = Some(url("https://github.com/ktr-skmt/LiCOS-JSON4Scala"))
val pomExtraTemplate = {
  <scm>
    <url>git@github.com:ktr-skmt/LiCOS-JSON4Scala.git</url>
    <connection>scm:git@github.com:ktr-skmt/LiCOS-JSON4Scala.git</connection>
  </scm>
    <developers>
      <developer>
        <id>ktr-skmt</id>
        <name>sakamoto-kotaro-pn@ynu.jp</name>
        <url>https://linkedin.com/in/kotaro-sakamoto-19168b4a</url>
      </developer>
    </developers>
}

val jsonLibraryProjectName: String = "json"

lazy val json = (project in file(".")).
  //enablePlugins(GhpagesPlugin).
  //enablePlugins(SiteScaladocPlugin).
  settings(commonSettings: _*).
  settings(
    scalacOptions in (Compile, doc) ++= Seq(
      "-groups",
      "-implicits",
      "-doc-root-content", (sourceDirectory in Compile).value + "/rootdoc.txt"
    ),
    autoAPIMappings := true
  ).settings(
  isSnapshot := true,
  version := "0.0.1",
  name := jsonLibraryName,
  publishMavenStyle := true,
  publishArtifact in Test := false,
  pomIncludeRepository := { _ => false},
  publishTo := getPublishTo(isSnapshot.value, jsonLibraryProjectName, name.value),
  licenses := licensesTemplate,
  homepage := homepageTemplate,
  pomExtra := pomExtraTemplate
  ).settings(
    libraryDependencies ++= Seq(
      "com.typesafe.play" %% "play-json" % "2.7.4",
      "org.mongodb.morphia" % "morphia" % "1.3.2",
      "org.projectlombok" % "lombok" % "1.16.20",
      "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
    )
  )//.settings(
    //git.remoteRepo := "git@github.com:ktr-skmt/LiCOS-JSON4Scala.git"
  //)