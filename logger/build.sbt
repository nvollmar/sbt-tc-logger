sbtPlugin := true

name := "sbt-teamcity-logger"

organization := "ch.scaling"

crossSbtVersions := Seq("2.0.0")

licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0.html"))

Test / publishArtifact := false

publishMavenStyle := true

pomExtra :=
  <licenses>
    <license>
      <name>Apache 2</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
      <distribution>repo</distribution>
    </license>
  </licenses>

// Do NOT publish the normal thin plugin jar
Compile / packageBin / publishArtifact := false

// Publish assembly as the main jar, without classifier
Compile / assembly / artifact := {
  val art = (Compile / packageBin / artifact).value
  art.withClassifier(None)
}

// Register assembly as the published main artifact
addArtifact(Compile / assembly / artifact, Compile / assembly)

// Optional, but keeps filename stable
Compile / assembly / assemblyJarName := "sbt-teamcity-logger.jar"