import Dependency._
$if(!multiModule.truthy) $
lazy val root =
  (project in file("."))
    .settings(BuildPlugin.projectSettings)
    .settings(
      name := "$name$",
      libraryDependencies ++=
        Seq(
          spark.core            % "provided",
          spark.sql             % "provided",
          spark.hadoopCloud     % "provided",
          scalatest.scalaTest        % "test",
          holdenkarau.sparktest % "test"
        )
    )
$else$
lazy val root =
  (project in file("."))
    .disablePlugins(BuildPlugin, AssemblyPlugin, HeaderPlugin)
    .settings(
      name           := "$name$",
      publish / skip := true
    )

lazy val m1 =
  (project in file("./m1"))
    .settings(
      name := "$name$-m1",
      libraryDependencies ++=
        Seq(
          spark.core            % "provided",
          spark.sql             % "provided",
          spark.hadoopCloud     % "provided",
          scalatest.scalaTest   % "test",
          holdenkarau.sparktest % "test"
        ),
      publish / skip := false
    )
$endif$
