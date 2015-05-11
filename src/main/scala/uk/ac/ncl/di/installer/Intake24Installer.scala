package uk.ac.ncl.di.installer

import uk.ac.ncl.di.installer.actions.{ChoosePath, WelcomeAction}
import uk.ac.ncl.di.installer.core.InstallContext
import uk.ac.ncl.di.installer.core.InstallContext

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.layout.Pane



object Intake24Installer extends JFXApp with InstallContext {

  val (mainContainerPane, mainController) = UI.createMainContainer

  stage = new JFXApp.PrimaryStage {
    title.value = "Intake24 local installation"
    width = 800
    height = 600

    scene = new Scene {
      root = mainContainerPane
    }
  }

  val workerThread = new Thread("Installer worker") {
    override def run() = {
      val install =
        for (
          _ <- WelcomeAction();
          path <- ChoosePath()
        ) yield ()

      val (result, undoSequence) = install.run(Intake24Installer.this)

      result match {
        case Right(_) => println ("Success!")
        case Left(Some(err)) => println ("Error: " + err)
        case Left(None) => println("Cancelled by the user!")
      }
    }
  }

  workerThread.start()

  def setInterface(pane: Pane): Unit = {
    mainContainerPane.center = pane
  }

  def enableNext(enabled: Boolean): Unit = {
    mainController.nextButton.setDisable(!enabled)
  }

  def waitForUser() = {
    mainController.lock.lock()
    mainController.userActionCond.await()
    mainController.lock.unlock()

    mainController.userAction
  }
}