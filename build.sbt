import sbtcrossproject.CrossPlugin.autoImport.crossProject
import sbtcrossproject.CrossPlugin.autoImport.CrossType

import scala.collection.immutable

lazy val V = new {
  val SCALA_2_12 = "2.12.17"
  val SCALA_2_13 = "2.13.10"
  val SCALA_3 = "3.2.2"
  val Scalas = Seq(SCALA_2_13, SCALA_2_12, SCALA_3)
  val literally = "1.0.2"
  val munit = "0.7.29"
}

ThisBuild / scalaVersion := V.Scalas.head
ThisBuild / crossScalaVersions := V.Scalas
ThisBuild / organization := "dev.holt"
ThisBuild / homepage := Option(url("https://github.com/bpholt/java-time-literals"))
ThisBuild / licenses += ("MIT", url("http://opensource.org/licenses/MIT"))
ThisBuild / developers := List(
  Developer(
    "bpholt",
    "Brian Holt",
    "bholt+github@planetholt.com",
    url("https://holt.dev")
  )
)
ThisBuild / githubWorkflowBuild := Seq(
  WorkflowStep.Sbt(List("test"), name = Option("Run tests")),
  WorkflowStep.Sbt(`java-time-literals`.componentProjects.map(p => s"${p.id} / mimaReportBinaryIssues").toList, name = Option("Check binary compatibility with MiMa")),
)
ThisBuild / githubWorkflowJavaVersions := Seq(JavaSpec.temurin("8"), JavaSpec.temurin("11"))
ThisBuild / githubWorkflowTargetTags ++= Seq("v*")
ThisBuild / githubWorkflowPublishTargetBranches := Seq(RefPredicate.StartsWith(Ref.Tag("v")))
ThisBuild / githubWorkflowPublish := Seq(
  WorkflowStep.Sbt(
    List("ci-release"),
    env = Map(
      "PGP_PASSPHRASE" -> "${{ secrets.PGP_PASSPHRASE }}",
      "PGP_SECRET" -> "${{ secrets.PGP_SECRET }}",
      "SONATYPE_PASSWORD" -> "${{ secrets.SONATYPE_PASSWORD }}",
      "SONATYPE_USERNAME" -> "${{ secrets.SONATYPE_USERNAME }}",
    )
  )
)
ThisBuild / startYear := Option(2021)
ThisBuild / sonatypeCredentialHost := "s01.oss.sonatype.org"

lazy val `java-time-literals` = crossProject(JSPlatform, JVMPlatform)
  .in(file("core"))
  .settings(Seq(
    description := "Parse string literals into `java.time` instances at compile time",
    mimaPreviousArtifacts := Set(organization.value %% moduleName.value % "1.0.0"),
    libraryDependencies ++= {
      val scalaReflect: immutable.Seq[ModuleID] =
        if (scalaVersion.value.startsWith("3")) Nil
        else List("org.scala-lang" % "scala-reflect" % scalaVersion.value % "provided")

      scalaReflect ++
        Seq(
          "org.typelevel" %% "literally" % V.literally,
          "org.scalameta" %% "munit" % V.munit % Test,
          "org.scalameta" %% "munit-scalacheck" % V.munit % Test,
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
