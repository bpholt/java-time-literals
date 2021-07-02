import sbtcrossproject.CrossPlugin.autoImport.crossProject
import sbtcrossproject.CrossPlugin.autoImport.CrossType

import scala.collection.immutable

lazy val V = new {
  val SCALA_2_12 = "2.12.14"
  val SCALA_2_13 = "2.13.6"
  val SCALA_3 = "3.0.0"
  val Scalas = Seq(SCALA_2_13, SCALA_2_12, SCALA_3)
  val literally = "1.0.2"
}

inThisBuild(List(
  scalaVersion := V.Scalas.head,
  crossScalaVersions := V.Scalas,
  organization := "dev.holt",
  homepage := Option(url("https://github.com/bpholt/java-time-literals")),
  licenses += ("MIT", url("http://opensource.org/licenses/MIT")),
  developers := List(
    Developer(
      "bpholt",
      "Brian Holt",
      "bholt+github@planetholt.com",
      url("https://holt.dev")
    )
  ),
  githubWorkflowJavaVersions := Seq("adopt@1.8", "adopt@1.11"),
  githubWorkflowTargetTags ++= Seq("v*"),
  githubWorkflowPublishTargetBranches := Seq(RefPredicate.StartsWith(Ref.Tag("v"))),
  githubWorkflowPublish := Seq(
    WorkflowStep.Sbt(
      List("ci-release"),
      env = Map(
        "PGP_PASSPHRASE" -> "${{ secrets.PGP_PASSPHRASE }}",
        "PGP_SECRET" -> "${{ secrets.PGP_SECRET }}",
        "SONATYPE_PASSWORD" -> "${{ secrets.SONATYPE_PASSWORD }}",
        "SONATYPE_USERNAME" -> "${{ secrets.SONATYPE_USERNAME }}",
      )
    )
  ),
  startYear := Option(2021),
  sonatypeCredentialHost := "s01.oss.sonatype.org",
))

lazy val `java-time-literals` = crossProject(JSPlatform, JVMPlatform)
  .in(file("core"))
  .settings(Seq(
    description := "Parse string literals into `java.time` instances at compile time",
    libraryDependencies ++= {
      val scalaReflect: immutable.Seq[ModuleID] =
        if (scalaVersion.value.startsWith("3")) Nil
        else List("org.scala-lang" % "scala-reflect" % scalaVersion.value % "provided")

      scalaReflect ++
        Seq(
          "org.typelevel" %% "literally" % V.literally,
          "org.scalameta" %% "munit" % "0.7.27" % Test,
        )
    },
    Compile / unmanagedSourceDirectories ++= { // needed until https://github.com/portable-scala/sbt-crossproject/issues/70 is fixed
      val major = if (scalaVersion.value.startsWith("3")) "-3" else "-2"
      List(CrossType.Pure, CrossType.Full).flatMap(
        _.sharedSrcDir(baseDirectory.value, "main").toList.map(f => file(f.getPath + major))
      )
    },
    sonatypeCredentialHost := "s01.oss.sonatype.org",
  ))
  .jsSettings(
    Test / scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
  )

lazy val `java-time-literals-root`: Project = (project in file("."))
  .settings(
    publish / skip := true,
    sonatypeCredentialHost := "s01.oss.sonatype.org",
  )
  .aggregate(
    `java-time-literals`.jvm,
    `java-time-literals`.js,
  )
