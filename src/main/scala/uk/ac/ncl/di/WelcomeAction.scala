package uk.ac.ncl.di

import javafx.fxml.FXMLLoader
import javafx.scene.layout.BorderPane
import scalafx.application.Platform
import scalafx.Includes._

class WelcomeAction extends Action[Unit] {

  private def loadUi() = {
    val fxmlLoader = new FXMLLoader()

    fxmlLoader.setLocation(getClass.getClassLoader.getResource("fxml/welcome.fxml"))
    val pane: scalafx.scene.layout.BorderPane = fxmlLoader.load[BorderPane]()
    val controller = fxmlLoader.getController[WelcomeController]

    controller.webView.getEngine.load(getClass.getClassLoader.getResource("html/welcome.html").toExternalForm)

    (pane, controller)
  }

  def run(context: InstallContext): (Either[Option[String], Unit], Seq[UndoAction]) = {

    Platform.runLater {
      val (pane, _) = loadUi()

      context.setInterface(pane)
    }

    println ("Welcome action...")
    Thread.sleep(5000)
    println ("Done")

    Platform.runLater {
      context.enableNext(true)
    }

    (Right(()), Seq())
  }
}

object WelcomeAction {
  def apply() = new WelcomeAction
}