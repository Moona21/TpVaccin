ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.13"

lazy val root = (project in file("."))
  .settings(
    name := "ExamVaccin"
  )
libraryDependencies += "com.twitter" % "bijection-avro_2.11" % "0.7.1"
libraryDependencies +="org.apache.jena" % "jena-core" % "4.3.2"

libraryDependencies ++= Seq("com.fasterxml" % "jackson-xml-databind" % "0.6.2", "com.github.javafaker" % "javafaker" % "1.0.2")

libraryDependencies += "com.fasterxml.jackson.core" % "jackson-core" % "2.13.1"

libraryDependencies += "org.apache.kafka" % "kafka-clients" % "2.8.0"

libraryDependencies += "org.apache.avro" % "avro" % "1.11.0"



