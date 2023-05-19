import scala.collection.immutable

lazy val V = new {
  val SCALA_2_12 = "2.12.17"
  val SCALA_2_13 = "2.13.10"
  val SCALA_3 = "3.2.2"
  val Scalas = Seq(SCALA_2_13, SCALA_2_12, SCALA_3)
  val literally = "1.1.0"
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
ThisBuild / startYear := Option(2021)
ThisBuild / tlBaseVersion := "1.1"
ThisBuild / tlCiReleaseBranches := Seq("main")

ThisBuild / mergifyStewardConfig ~= {
  _.map(_.copy(mergeMinors = true))
}
ThisBuild / mergifySuccessConditions += MergifyCondition.Custom("#approved-reviews-by>=1")
ThisBuild / mergifyRequiredJobs ++= Seq("validate-steward")

tpolecatScalacOptions += ScalacOptions.release("8")
ThisBuild / githubWorkflowJavaVersions := Seq(JavaSpec.temurin("17"))
ThisBuild / githubWorkflowScalaVersions := Seq("3", "2.13", "2.12")

ThisBuild / tlSonatypeUseLegacyHost := false

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
          "org.scalameta" %% "munit" % V.munit % Test,
          "org.scalameta" %% "munit-scalacheck" % V.munit % Test,
        )
    },
  ))
  .jsSettings(
    Test / scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
  )

lazy val `java-time-literals-root`: Project = (project in file("."))
  .settings(
    publish / skip := true,
    publishArtifact := false,
  )
  .aggregate(
    `java-time-literals`.jvm,
    `java-time-literals`.js,
  )
