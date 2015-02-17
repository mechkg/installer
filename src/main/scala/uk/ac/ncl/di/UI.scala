package uk.ac.ncl.di

import java.net.URL
import javafx.fxml.FXMLLoader
import javafx.scene.layout.BorderPane
import scalafx.Includes._

object UI {

  def createMainContainer = {
    val fxmlLoader = new FXMLLoader()

    fxmlLoader.setLocation(getClass.getClassLoader.getResource("fxml/main.fxml"))
    val pane: scalafx.scene.layout.BorderPane = fxmlLoader.load[BorderPane]()
    val controller = fxmlLoader.getController[MainWindowController]

    (pane, controller)
  }

  def createWelcomePane(htmlContent: URL) = {
    val fxmlLoader = new FXMLLoader()

    fxmlLoader.setLocation(getClass.getClassLoader.getResource("fxml/welcome.fxml"))
    val pane: scalafx.scene.layout.BorderPane = fxmlLoader.load[BorderPane]()
    val controller = fxmlLoader.getController[WelcomeController]

    controller.webView.getEngine.load(getClass.getClassLoader.getResource("html/welcome.html").toExternalForm)

    (pane, controller)
  }

  def welcomeStage(htmlContent: URL) = new Stage[Unit] {
    def run(stageContext: StageContext) = {

      val (pane, controller) = createWelcomePane(htmlContent)

      stageContext.setInterface(pane)

    }
  }
}
