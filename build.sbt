import deployssh.DeploySSH.ArtifactSSH
import fr.janalyse.ssh.SSH

lazy val commonDependencies = Seq(
  "com.pi4j" % "pi4j-gpio-extension" % "1.1",
  "com.pi4j" % "pi4j-device" % "1.1"
)

lazy val akkaDependencies = Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.17",
  "com.typesafe.akka" %% "akka-slf4j" % "2.4.17"
)

lazy val akkaRemotingDependencies = akkaDependencies ++ Seq(
  "com.typesafe.akka" %% "akka-remote" % "2.4.17",
  "com.typesafe.akka" %% "akka-cluster-tools" % "2.4.17"
)

lazy val testDependencies = Seq(
  "org.scalactic" %% "scalactic" % "3.0.1",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)

lazy val `pi-ssd-1306-java` = (project in file("submodules/pi-ssd1306-java"))
  .settings(
    organization := "eu.ondryaso",
    name := "ssd1306",
    version := "1.0-SNAPSHOT",
    autoScalaLibrary := false,
    crossPaths := false,
    javaSource in Compile := baseDirectory.value / "src",
    libraryDependencies := commonDependencies
  )

lazy val commonSettings = Seq(
  version := "1.0",
  organization := "com.bradbrok.filmomatic",
  scalaVersion := "2.11.8"
)

lazy val core = (project in file("core"))
  .dependsOn(`pi-ssd-1306-java`)
  .settings(
    commonSettings,
    name := "Film-O-Matic Core",
    libraryDependencies := commonDependencies ++
      akkaDependencies ++
      testDependencies,
    packSettings,
    packMain := Map("filmomatic" -> "com.bradbrok.filmomatic.Main")
  )

lazy val integration = (project in file("integration"))
  .dependsOn(core)
  .settings(
    commonSettings,
    name := "Film-O-Matic Integration",
    libraryDependencies := akkaRemotingDependencies ++
      testDependencies ++
      Seq(
        "org.kaloz.pi4j.client" % "remote-client" % "0.1.0"
      ),
    packSettings,
    packMain := Map("filmomatic" -> "com.bradbrok.filmomatic.Main")
  )

lazy val remote = (project in file("remote"))
  .enablePlugins(DeploySSH)
  .settings(
    commonSettings,
    name := "Film-O-Matic Remote",
    libraryDependencies := commonDependencies ++ akkaRemotingDependencies ++ Seq(
      "org.kaloz.pi4j.client" % "pi4j-remote-server" % "0.1.0"
    ),
    packSettings,
    packMain := Map("server" -> "org.kaloz.pi4j.server.remote.Main"),
    deployHomeConfigFiles ++= Seq(".pis.conf"),
    deploySshExecBefore ++= Seq(
      (ssh: SSH) => {
        ssh execOnce "mkdir -p filmomatic/remote"
        ssh execOnce "touch filmomatic/remote/pid"
        val pid = ssh execOnceAndTrim "cat filmomatic/remote/pid"
        if (!pid.isEmpty)
          ssh execOnce s"sudo kill $pid"
      }
    ),
    deployArtifacts ++= Seq(
      ArtifactSSH(pack.value, s"filmomatic/remote/")
    ),
    deploySshExecAfter ++= Seq(
      (ssh: SSH) => {
        ssh execOnce s"chmod u+x filmomatic/remote/bin/server"
        val startCommand =
          s"""sudo JAVA_OPTS="
             | -Dakka.cluster.seed-nodes.0=akka.tcp://pi4j-remoting@${ssh.options.host}:2552
             | -Dakka.remote.netty.tcp.hostname=${ssh.options.host}
             | "
             | ./filmomatic/remote/bin/server &>> filmomatic/remote/out &
             | echo $$! > filmomatic/remote/pid
             |""".stripMargin.replaceAll("\n", "").trim
        println(s"Executing $startCommand")
        ssh execOnce startCommand
        ssh execOnce "touch filmomatic/remote/pid"
        val pid = ssh execOnceAndTrim "cat filmomatic/remote/pid"
        if (pid.isEmpty)
          throw new RuntimeException("Unable to start remote daemon")
      }
    )
  )

lazy val `film-o-matic` = (project in file("."))
  .aggregate(`pi-ssd-1306-java`, core, integration, remote)

