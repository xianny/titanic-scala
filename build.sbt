name := "titanic"

version := "1.0"
libraryDependencies ++= Seq(
    "org.apache.spark" %% "spark-core" % "2.1.0",
    "org.apache.spark" %% "spark-mllib" % "1.3.1",
    "com.nrinaudo" %% "kantan.csv" % "0.1.18")
scalaVersion := "2.11.9"
resolvers := Seq(Resolver.sonatypeRepo("snapshots"))
    