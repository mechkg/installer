package uk.ac.ncl.di

trait UndoAction {
  def run(context: InstallContext)
}
