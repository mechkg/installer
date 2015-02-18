package uk.ac.ncl.di

trait Action[A] {

  def unit(v: A) = new Action[A] {
    def run(context: InstallContext) = (Right(v), Seq())
  }

  def map[B](f: A => B) = {
    val outer = this
    new Action[B] {
      def run(context: InstallContext) = {
        val (result, undo) = outer.run(context)
        (result.right.map(f), undo)
      }
    }
  }

  def flatMap[B](f: A => Action[B])  = {
    val outer = this
    new Action[B] {
      def run(context: InstallContext) = {
        val (outerResult, outerUndo) = outer.run(context)

        outerResult match {
          case Left(errorMessage) => (Left(errorMessage), outerUndo)
          case Right(result) => {
            val innerAction = f(result)

            context.waitForUser() match {
              case UserAction.Next => {
                context.enableNext(false)

                val (innerResult, innerUndo) = innerAction.run(context)

                (innerResult, outerUndo ++ innerUndo)
              }
              case UserAction.Cancel => {
                (Left(None), outerUndo)
              }
            }
          }
        }
      }
    }
  }

  def run(context: InstallContext): (Either[Option[String], A], Seq[UndoAction])
}
