import scala.collection.immutable

lazy val V = new {
  val SCALA_2_12 = "2.12.20"
  val SCALA_2_13 = "2.13.17"
  val SCALA_3 = "3.3.7"
  val Scalas = Seq(SCALA_2_13, SCALA_2_12, SCALA_3)
  val literally = "1.2.0"
  val scalaJavaTime = "2.7.0"
  val munit = "1.2.1"
  val munitScalacheck = "1.2.0"
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

ThisBuild / mergifyStewardConfig ~= { _.map {
  _.withMergeMinors(true)
}}
ThisBuild / mergifySuccessConditions += MergifyCondition.Custom("#approved-reviews-by>=1")
ThisBuild / mergifyRequiredJobs ++= Seq("validate-steward")

ThisBuild / tlJdkRelease := Option(8)
ThisBuild / githubWorkflowJavaVersions := Seq(JavaSpec.temurin("17"))
ThisBuild / githubWorkflowScalaVersions := Seq("3", "2.13", "2.12")

lazy val `java-time-literals` = crossProject(JSPlatform, JVMPlatform, NativePlatform)
  .in(file("core"))
  .settings(
    description := "Parse string literals into `java.time` instances at compile time",
    libraryDependencies ++= {
      val scalaReflect: immutable.Seq[ModuleID] =
        if (scalaVersion.value.startsWith("3")) Nil
        else List("org.scala-lang" % "scala-reflect" % scalaVersion.value % Provided)

      scalaReflect ++
        Seq(
          "org.typelevel" %%% "literally" % V.literally,
          "io.github.cquiroz" %%% "scala-java-time" % V.scalaJavaTime,
          "org.scalameta" %%% "munit" % V.munit % Test,
          "org.scalameta" %%% "munit-scalacheck" % V.munitScalacheck % Test,
          "io.github.cquiroz" %%% "scala-java-time-tzdb" % V.scalaJavaTime % Test,
        )
    },
  )
  .jsSettings(
    Test / scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
  )
  .nativeSettings(
    tlVersionIntroduced := Map("2.12" -> "1.2.0", "2.13" -> "1.2.0", "3" -> "1.2.0"),
  )

lazy val `java-time-literals-root` = tlCrossRootProject
  .aggregate(
    `java-time-literals`
  )
