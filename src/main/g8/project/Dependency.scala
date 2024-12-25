import sbt._
object Dependency {

  object spark {
    lazy val core: ModuleID        = "org.apache.spark" %% "spark-core"         % Version.Spark
    lazy val sql: ModuleID         = "org.apache.spark" %% "spark-sql"          % Version.Spark
    lazy val hadoopCloud: ModuleID = "org.apache.spark" %% "spark-hadoop-cloud" % Version.Spark
  }

  object scalatest {
    lazy val scalaTest: ModuleID = "org.scalatest" %% "scalatest" % Version.ScalaTest
  }

  object holdenkarau {
    lazy val sparktest: ModuleID = "com.holdenkarau" %% "spark-testing-base" % Version.HoldenVersion
  }

}
