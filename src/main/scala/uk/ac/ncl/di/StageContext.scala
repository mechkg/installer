package uk.ac.ncl.di

import scalafx.scene.layout.Pane

trait StageContext {
  def setInterface(pane: Pane)
  def setNextEnabled(enabled: Boolean)
}
