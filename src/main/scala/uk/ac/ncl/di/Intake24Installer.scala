package uk.ac.ncl.di


import java.net.URL
import javafx.fxml.FXMLLoader
import javafx.scene.layout.{AnchorPane, BorderPane}

import org.apache.commons.io.IOUtils

import scala.concurrent.{Future, Await}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.sys.process._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.Includes._
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

object Intake24Installer extends JFXApp {

  val (mainContainerPane, mainController) = UI.createMainContainer

  val (welcomePane, welcomeController) = UI.createWelcomePane(getClass.getClassLoader.getResource("html/welcome.html"))

  stage = new JFXApp.PrimaryStage {
    title.value = "Intake24 local installation"
    width = 800
    height = 600

    mainContainerPane.center = welcomePane

    scene = new Scene {
      root = mainContainerPane
    }
  }


  // val x = getClass().getClassLoader().getResourceAsStream("thirdparty/jetty-distribution-9.2.7.v20150116.zip")

  // FileUtil.extractZip(x, "/home/ivan/tmp/test")

  // val x = Seq("sudo","-S","apt-get","update") #< is

  // println (x !)

  // Future.sequence()

  println (System.getProperty("os.name"))
  println (System.getProperty("os.version"))



}