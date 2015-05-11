package uk.ac.ncl.di.installer.actions

import javafx.fxml.FXMLLoader
import javafx.scene.layout.BorderPane
import uk.ac.ncl.di.installer.core._

import scalafx.Includes._
import scalafx.application.Platform

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

      context.enableNext(true)
    }

    println ("Waiting for user")

    context.waitForUser() match {
      case UserAction.Next => {
        println ("Hello action!")
        Thread.sleep(3000)
        println ("Done!")

        (Right(()), Seq())
      }
      case UserAction.Cancel => {
        (Left(None), Seq())
      }
    }
  }
}

object WelcomeAction {
  def apply() = new WelcomeAction
}