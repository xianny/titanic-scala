name := "titanic"

version := "1.0"
libraryDependencies ++= Seq(
    "org.apache.spark" %% "spark-core" % "2.1.0",
    "org.apache.spark" %% "spark-mllib" % "2.1.0",
    "org.apache.spark" % "spark-sql_2.11" % "2.1.0",
    "com.databricks" % "spark-csv_2.11" % "1.5.0")
scalaVersion := "2.11.9"
resolvers := Seq(Resolver.sonatypeRepo("snapshots"))
    