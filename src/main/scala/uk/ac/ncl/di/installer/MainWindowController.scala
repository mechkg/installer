package uk.ac.ncl.di.installer

import java.util.concurrent.locks.ReentrantLock
import javafx.fxml.FXML
import javafx.scene.control.Button

import uk.ac.ncl.di.installer.core.UserAction

class MainWindowController {

  val lock = new ReentrantLock()

  val userActionCond = lock.newCondition()

  @FXML
  var nextButton: Button = null

  @FXML
  var cancelButton: Button = null

  var userAction: UserAction = null

  @FXML
  def next = {

    lock.lock()

    println ("Next")

    userAction = UserAction.Next
    userActionCond.signal()
    lock.unlock()
  }

  @FXML
  def cancel = {
    lock.lock()

    println ("Cancel")

    userAction = UserAction.Cancel
    userActionCond.signal()
    lock.unlock()
  }
}
