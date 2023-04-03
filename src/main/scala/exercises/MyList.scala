package exercises

/*
    1. Generic trait MyPredicate[-T]
        - test[T] => Boolean
    2. Generic trait MyTransformer[-A, B]
        - transform[A] => B
    3. MyList:
        - map(transformer) => MyList
        - filter(predicate) => MyList
        - flatMap(transformer from A to MyList[B]) => MyList[B]

    class EvenPredicate extends MyPredicate[Int]
    class StringToIntTransformer extends MyTransformer[String, Int]

    [1,2,3].map(n * 2) = [2,4,6]
    [1,2,3,4].filter(n % 2) = [2,4]
    [1,2,3].flatMap(n => [n, n + 1]) => [1,2,2,3,3,4]
  */

trait MyPredicate[-T] {
  def test(n: T): Boolean
  def apply(n: T): Boolean = test(n)
}

trait MyTransformer[-A, B] {
  def transform(n: A): B
  def apply(n: A): B = transform(n)
}

abstract class MyList[+A] {

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  // Polymorphic call:
  // Delegate to subclass implementation of printElements:
  def map[B](transformer: MyTransformer[A, B]): MyList[B]
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
  def filter[A](predicate: MyPredicate[A]): MyList[A]

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
object Empty extends MyList[Nothing] {
  // Empty lists have no head or tail so we'll throw exceptions instead
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  // Adding to an empty list will return a Cons list with element as the head and the Empty list as the tail:
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  def printElements: String = ""

  def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty
  def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty
  def filter[Nothing](predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

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

  def filter(predicate: MyPredicate[A]): MyList[A] =
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  def map[B](transformer: MyTransformer[A, B]): MyList[B] =
    new Cons[B](transformer.transform(h), t.map(transformer))

  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list) // niiiice!

  // Compared to how I was trying to do it, this is amazing.
  // (And I would never have come up with it!)
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] = {
    transformer.transform(h) ++ t.flatMap(transformer)
  }

}

object ListTest extends App {
  val listOfIntegers = new Cons(1, new Cons(2, new Cons(3, Empty))).add(4).add(5).add(6).add(7).add(8)
  val listOfStrings = new Cons("Hello", new Cons("world", Empty))

  // Cleaned up all my tests

}