package uk.ac.ncl.di.installer.actions

import javafx.fxml.FXMLLoader
import javafx.scene.layout.BorderPane

import uk.ac.ncl.di.installer.core.{UndoAction, InstallContext, Action}

import scalafx.application.Platform
import scalafx.Includes._

class ChoosePath extends Action[String] {
  private def loadUi() = {
    val fxmlLoader = new FXMLLoader()

    fxmlLoader.setLocation(getClass.getClassLoader.getResource("fxml/choosePath.fxml"))
    val pane: scalafx.scene.layout.BorderPane = fxmlLoader.load[BorderPane]()
    val controller = fxmlLoader.getController[ChoosePathController]

    (pane, controller)
  }

  override def run(context: InstallContext): (Either[Option[String], String], Seq[UndoAction]) = {
    Platform.runLater {
      val (pane, _) = loadUi()

      context.setInterface(pane)

      context.enableNext(true)

    }

    context.waitForUser()

    (Right("test"), Seq())
  }
}

object ChoosePath {
  def apply() = new ChoosePath()
}