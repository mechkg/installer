package uk.ac.ncl.di

import scalafx.scene.layout.Pane

sealed trait UserAction

object UserAction {
  case object Next extends UserAction
  case object Cancel extends UserAction
}

trait InstallContext {
  def setInterface(pane: Pane)
  def enableNext(enabled: Boolean)

  def waitForUser(): UserAction
}
