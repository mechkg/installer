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
}

