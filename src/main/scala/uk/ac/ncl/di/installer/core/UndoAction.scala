package uk.ac.ncl.di.installer.core

trait UndoAction {
  def run(context: InstallContext)
}
