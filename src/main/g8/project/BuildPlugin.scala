import de.heikoseeberger.sbtheader.HeaderPlugin.autoImport.{headerLicense, HeaderLicense}
import de.heikoseeberger.sbtheader.{HeaderPlugin, License}
import sbt.Keys._
import sbt._
import sbt.plugins.JvmPlugin
$if(scoverageEnabled.truthy) $
import scoverage.ScoverageKeys._
$endif$

object BuildPlugin extends AutoPlugin {

  override def requires: Plugins = JvmPlugin && HeaderPlugin

  override def trigger: PluginTrigger = allRequirements

  lazy val localJvmSettings: Seq[String] =
    Seq(
      "-Xms8G",
      "-Xmx8G",
      "-XX:MaxPermSize=4048M",
      "-XX:+CMSClassUnloadingEnabled",
      "-Duser.timezone=GMT",
      "-XX:+PrintCommandLineFlags",
      "-XX:+CMSClassUnloadingEnabled"
    )

  override def projectSettings: Seq[Setting[_]] = Seq(
    organizationName   := "$name$",
    organization       := "$organization$",
    scalaVersion       := Version.Scala,
    crossScalaVersions := Vector(scalaVersion.value),
    javacOptions := Seq(
      "-g:none",
      "--add-exports=java.base/sun.nio.ch=ALL-UNNAMED"
    ),
    run / javaOptions ++= localJvmSettings,
    run / fork               := true,
    Test / fork              := true,
    Test / parallelExecution := false,
    $if(scoverageEnabled.truthy) $
      run / coverageHighlighting := true,
    $endif$
      headerLicense := Some (headerIOLicense),
    scalacOptions ++= Vector(
//      "-release:8",
//      "-target:11",
      "-Ymacro-annotations",
      "-deprecation", // Emit warnings for deprecated APIs.
//      "-Ypartial-unification", // Just for Scala 2.12.x - Enable partial unification in type constructor inference
//      "-Wnonunit-statement", // Just for Scala 2.13.x - Warn when a block that doesn't contain a statement (e.g. an if with an else clause without the else clause) evaluates to Unit.
//      "-encoding:utf-8",        // Specify character encoding used by source files.
      "-explaintypes",          // Explain type errors in more detail.
      "-feature",               // Emit warnings for features that should be imported explicitly.
      "-language:existentials", // Allow existential types (besides wildcard types).
      "-language:experimental.macros", // Allow macro definition (besides implementation and application).
      "-language:higherKinds",         // Allow higher-kinded types.
      "-language:implicitConversions", // Allow definition of implicit functions called views.
      "-unchecked",       // Enable additional warnings where generated code depends on assumptions.
      "-Xcheckinit",      // Wrap field accessors to throw an exception on uninitialized access.
      "-Xfatal-warnings", // Fail compilation if there are any warnings.
      "-Xlint:adapted-args",       // Warn if an argument list is modified to match the receiver.
      "-Xlint:constant",           // Warn if constant expressions evaluate to an error.
      "-Xlint:delayedinit-select", // Warn about selecting members of DelayedInit.
      "-Xlint:doc-detached",       // Warn if Scaladoc comments appear detached from their element.
      "-Xlint:inaccessible",       // Warn about inaccessible types in method signatures.
      "-Xlint:infer-any",          // Warn when a type argument is inferred to be Any.
      "-Xlint:missing-interpolator",   // Warn if a string literal is missing an interpolator id.
      "-Xlint:nullary-unit",           // Warn if nullary methods return Unit.
      "-Xlint:option-implicit",        // Warn about implicit views in Option.apply.
      "-Xlint:package-object-classes", // Warn if classes or objects are defined in package objects.
      "-Xlint:poly-implicit-overload", // Warn if parameterized overloaded implicit methods are not visible as view bounds.
      "-Xlint:private-shadow", // Warn if a private field shadows a superclass field.
      "-Xlint:stars-align",    // Warn if wildcard patterns do not align with sequence components.
      "-Xlint:type-parameter-shadow", // Warn if a local type parameter shadows a type already in scope.
//      "-Xlint:unsound-match", // Warn if a pattern match may not be typesafe.
      "-Ywarn-dead-code",        // Warn when dead code is identified.
      "-Ywarn-extra-implicit",   // Warn when more than one implicit parameter section is defined.
      "-Ywarn-unused:implicits", // Warn if an implicit parameter is unused.
//      "-Ywarn-unused:imports",   // Warn if an import selector is not referenced.
      "-Ywarn-unused:locals",    // Warn if a local definition is unused.
      "-Ywarn-unused:explicits", // Warn if a value parameter is unused.
      "-Ywarn-unused:params",    // Warn if a parameter is unused.
      "-Ywarn-unused:patvars",   // Warn if a variable bound in a pattern is unused.
      "-Ywarn-unused:privates"   // Warn if a private member is unused.
//      "-Ywarn-macros:after"      // Warn about macro annotations after expansion.
//      "-Ymacro-annotations"      // Scala 2.13.x - Allow the use of macro annotations.
    ),
    Compile / console / scalacOptions ~= (_.filterNot(
      Set("-Xfatal-warnings", "-Ywarn-unused:imports")
    )),
    updateOptions           := updateOptions.value.withCachedResolution(cachedResolution = false),
    Compile / doc / sources := Seq.empty,
    // uses compile classpath for the run task, including "provided" jar (cf http://stackoverflow.com/a/21803413/3827)
    Compile / run := Defaults
      .runTask(Compile / fullClasspath, Compile / run / mainClass, Compile / run / runner)
      .evaluated,
    Test / testOptions += Tests.Argument("-oDF"),
    Test / testOptions += Tests.Argument(TestFrameworks.JUnit, "-v", "-a")
  ) ++ Assembly.projectSettings

  /**
   * SBT Header Plugin
   */

  lazy val headerText: String =
    """Copyright 2024 [name of copyright owner]
      |
      |Licensed under the Apache License, Version 2.0 (the "License");
      |you may not use this file except in compliance with the License.
      |You may obtain a copy of the License at
      |
      |http://www.apache.org/licenses/LICENSE-2.0
      |
      |Unless required by applicable law or agreed to in writing, software
      |distributed under the License is distributed on an "AS IS" BASIS,
      |WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
      |See the License for the specific language governing permissions and
      |limitations under the License.
      |""".stripMargin

  lazy val headerIOLicense: License.Custom =
    HeaderLicense.Custom(headerText)
}
