package exercises

abstract class MyList[+A] {

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  // Polymorphic call:
  // Delegate to subclass implementation of printElements:

  // Higher-order functions
  def map[B](transformer: A => B): MyList[B]

  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  def filter(predicate: A => Boolean): MyList[A]

  override def toString: String = "[" + printElements + "]"

  def doop: MyList[A] = {
    if (tail.isEmpty) new Cons[A](head, Empty)
    else new Cons(head, tail.doop)
  }

// Flips it but it is tail recursive (also hey maybe you want to flip a list every now and then)
// (Trying to figure out how to tail recurse a list copy function without flipping order!)
  def reverse: MyList[A] = {
    def helper(list: MyList[A], check: => MyList[A]): MyList[A] = {
      if (check.isEmpty) list
      else helper(new Cons[A](check.head, list), check.tail)
    }
    helper(Empty, this)
  }

  // Concatenation
  def ++[B >: A](list: MyList[B]): MyList[B]

}

// Extend an object from the class - always the same for an empty list so makes sense
// Nothing inherits from everything so we can use it for our empty list
case object Empty extends MyList[Nothing] {
  // Empty lists have no head or tail so we'll throw exceptions instead
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  // Adding to an empty list will return a Cons list with element as the head and the Empty list as the tail:
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  def printElements: String = ""

  def map[B](transformer: Nothing => B): MyList[B] = Empty
  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty
  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h

  def tail: MyList[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  def printElements: String = {
    if (t.isEmpty) "" + h
    else h.toString + ", " + t.printElements
  }

  def filter(predicate: A => Boolean): MyList[A] = {
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }

  def map[B](transformer: A => B): MyList[B] =
    new Cons[B](transformer(h), t.map(transformer))

  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list) // niiiice!

  // Compared to how I was trying to do it, this is amazing.
  // (And I would never have come up with it!)
  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

}

object ListTest extends App {
  val listOfIntegers = new Cons(1, new Cons(2, new Cons(3, Empty))).add(4).add(5).add(6).add(7).add(8)

  val listOfStrings = new Cons("Hello", new Cons("world", Empty))

  val transformer = new Function1[Int, MyList[Int]] {
    override def apply(n: Int): MyList[Int] = {
      new Cons[Int](n, new Cons[Int](n + 1, Empty))
    }
  }

  println(transformer(1).toString)

  println(listOfIntegers)
  println(listOfIntegers.flatMap(transformer).toString)

  val transformedOnTheSpot = listOfIntegers.flatMap(
    new Function1[Int, MyList[Int]] {
      override def apply(n: Int): MyList[Int] = {
        new Cons[Int](n, new Cons[Int](n * 2, new Cons[Int](n * 3, Empty)))
      }
    })

  println(transformedOnTheSpot)

  println(transformedOnTheSpot.filter(new Function1[Int, Boolean] {
    override def apply(n: Int): Boolean = n % 3 == 0
  }))

  // Now that we've made Cons and Empty case classes:
  val cloneListOfIntegers = new Cons(1, new Cons(2, new Cons(3, Empty))).add(4).add(5).add(6).add(7).add(8)
  // Equals is defined for us:
  println(cloneListOfIntegers == listOfIntegers) // true thanks to keyword case!
  // We would otherwise need to implement a recursive equality method
}