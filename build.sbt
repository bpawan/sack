name := "cassandra-phantom"

version := "1.0"

scalaVersion := "2.12.4"

ivyScala := ivyScala.value map {
  _.copy(overrideScalaVersion = true)
}

lazy val Versions = new {
  val phantom = "2.14.5"
  val util = "0.30.1"
  val scalaTest = "3.0.4"
  val akkaHttp = "10.0.11"
  val akkaHttpSprayJson = "10.0.1"
  val akkaStreamKafka = "0.19"
  val logbackClassic = "1.2.3"
}

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.typesafeRepo("releases"),
  Resolver.bintrayRepo("websudos", "oss-releases")
)

libraryDependencies ++= Seq(
  "com.outworkers" %% "phantom-dsl" % Versions.phantom,
  "com.outworkers" %% "phantom-streams" % Versions.phantom,
  "com.outworkers" %% "util-testing" % Versions.util % Test,
  "org.scalatest" %% "scalatest" % Versions.scalaTest % Test,
  "com.typesafe.akka" %% "akka-http" % Versions.akkaHttp,
  "com.typesafe.akka" %% "akka-http-spray-json" % Versions.akkaHttpSprayJson,
  "com.typesafe.akka" %% "akka-stream-kafka" % Versions.akkaStreamKafka,
  "ch.qos.logback" % "logback-classic" % Versions.logbackClassic
)
