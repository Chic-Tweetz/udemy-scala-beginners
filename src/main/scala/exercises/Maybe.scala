package exercises


abstract class Maybe[+T] {
  def map[A](transformer: T => A): Maybe[A]

  def flatMap[A](transformer: T => Maybe[A]): Maybe[A]

  def filter(predicate: T => Boolean): Maybe[T]

}

case object MaybeNot extends Maybe[Nothing] {
  def map[A](f: Nothing => A): Maybe[A] = MaybeNot

  def flatMap[A](f: Nothing => Maybe[A]): Maybe[A] = MaybeNot

  def filter(p: Nothing => Boolean): Maybe[Nothing] = MaybeNot

  override def toString = "maybe: []"

}

case class MaybeSo[T](value: T) extends Maybe[T] {
  def map[A](f: T => A): Maybe[A] = MaybeSo(f(value))

  def flatMap[A](f: T => Maybe[A]): Maybe[A] = f(value)

  def filter(p: T => Boolean): Maybe[T] =
    if (p(value)) this
    else MaybeNot

  override def toString = "maybe: [" + value.toString + ']'
}

object MaybeTest extends App {
  val maybe1 = MaybeSo(5)
  val maybe2 = MaybeSo(8)
  println(maybe1)

  println(maybe1.filter(_ % 2 == 0)) // empty
  println(maybe2.filter(_ % 2 == 0)) // 8

  val maybe3 = MaybeSo("hello!")
  println(maybe3)

  println(maybe1.map(_ + 5)) // 10
  println(maybe2.flatMap(n => maybe3.map(s => s + n)))

  val maybeNot = MaybeNot

  val forCompMaybe = for {
    m1 <- maybe1
    m2 <- maybe2
    m3 <- maybe3
  } yield ("" + m1 + '-' + m2 + '-' + m3)

  println(forCompMaybe)
}