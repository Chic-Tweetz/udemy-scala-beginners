package exercises

abstract class MyList[+A] {

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  // Polymorphic call:
  // Delegate to subclass implementation of printElements:

  override def toString: String = "[" + printElements + "]"

}

// Extend an object from the class - always the same for an empty list so makes sense
// Nothing inherits from everything so we can use it for our empty list
object Empty extends MyList[Nothing] {
  // Empty lists have no head or tail so we'll throw exceptions instead
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  // Adding to an empty list will return a Cons list with element as the head and the Empty list as the tail:
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  def printElements: String = ""
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h

  def tail: MyList[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  def printElements: String = {
    if (t.isEmpty) "" + h
    else h.toString + ", " + t.printElements
  }
}

object ListTest extends App {
  val listOfIntegers = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listOfStrings = new Cons("Hello", new Cons("world", Empty))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)
}