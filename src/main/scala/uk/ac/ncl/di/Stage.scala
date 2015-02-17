package uk.ac.ncl.di

trait Stage[A] {

  def unit(v: A) = new Stage[A] {
    def run(context: StageContext) = v
  }

  def map[B](f: A => B) = {
    val outer = this
    new Stage[B] { def run(context: StageContext) = f(outer.run(context)) }
  }

  def flatMap[B](f: A => Stage[B])  = {
    val outer = this
    new Stage[B] {
      def run(context: StageContext) = f(outer.run(context)).run(context)
    }
  }

  def run(context: StageContext): A
}
