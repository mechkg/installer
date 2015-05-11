package uk.ac.ncl.di.installer.actions

import javafx.fxml.FXML
import javafx.scene.control.TextField

class ChoosePathController {
  @FXML
  var path: TextField = null

  @FXML
  def browse = {
    println ("Browse!")
  }
}
