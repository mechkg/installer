package uk.ac.ncl.di

import java.util.concurrent.locks.ReentrantLock
import javafx.fxml.FXML
import javafx.scene.control.Button

class MainWindowController {

  val lock = new ReentrantLock()

  val userActionCond = lock.newCondition()

  @FXML
  var nextButton: Button = null

  var userAction: UserAction = null

  @FXML
  def next = {

    lock.lock()

    println ("Next")

    userAction = UserAction.Next
    userActionCond.signal()
    lock.unlock()
  }
}
