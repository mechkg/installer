name := "Installer"

version := "1.0"

scalaVersion := "2.11.5"

scalacOptions ++= Seq("-unchecked", "-feature")

libraryDependencies ++= Seq("commons-io" % "commons-io" % "2.4", "org.scalafx" % "scalafx_2.11" % "8.0.31-R7")

unmanagedJars in Compile += Attributed.blank(file(scala.util.Properties.javaHome) / "/lib/jfxrt.jar")
