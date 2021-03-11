lazy val V = new {
  val SCALA_2_12 = "2.12.12"
  val SCALA_2_13 = "2.13.4"
  val Scalas = Seq(SCALA_2_13, SCALA_2_12)
  val literally = "0.1-360f22d"
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
  githubWorkflowTargetTags ++= Seq("v*"),
  githubWorkflowPublishTargetBranches := Seq(RefPredicate.StartsWith(Ref.Tag("v"))),
  githubWorkflowBuild := Seq(WorkflowStep.Sbt(List("undeclaredCompileDependenciesTest", "unusedCompileDependenciesTest", "test"), name = Some("Build and test project"))),
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
))

lazy val `java-time-literals`: Project = (project in file("core"))
  .settings(Seq(
    description := "Scala string interpolaters for parsing string literals into `java.time` instances at compile time",
    libraryDependencies ++= {
      Seq(
        "org.scalameta" %% "munit" % "0.7.22" % Test,
      )
    },
    testFrameworks += new TestFramework("munit.Framework"),
  ))

lazy val `java-time-literals-root`: Project = (project in file("."))
  .settings(skip in publish := true)
  .aggregate(`java-time-literals`)
