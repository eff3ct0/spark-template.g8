/** Build */
addSbtPlugin("com.eed3si9n"      % "sbt-assembly" % "2.3.0")
addSbtPlugin("org.scalameta"     % "sbt-scalafmt" % "2.5.2")
addSbtPlugin("de.heikoseeberger" % "sbt-header"   % "5.10.0")

/** Publish */
addSbtPlugin("com.github.sbt" % "sbt-ci-release" % "1.9.2")

$if(scoverageEnabled.truthy) $
  /** Testing */
  addSbtPlugin("org.scoverage" % "sbt-scoverage" % "2.2.2")
$endif$

$if(scoverageEnabled.truthy) $
import scoverage.ScoverageKeys._
$endif$