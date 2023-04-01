package exercises

abstract class MyList {

  /*
    Singly-linked list which holds integers
    head = first el
    tail = remainder of list
    isEmpty = is this list empty?
    add(int) => newList with this element added
    toString => a string representation of the list
  */
  def head: Int
  def tail: MyList

  def isEmpty: Boolean
  def add(x: Int): MyList
  def printElements: String
  // Polymorphic call:
  // Delegate to subclass implementation of printElements:

  override def toString: String = "[" + printElements + "]"

}

//// Empty list defined separately
//class EmptyList() extends MyList {
//  override def head: MyList = null
//  override def tail: MyList = null
//  override def isEmpty: Boolean = true
//  override def add(x: Int): IntList = new IntList(x)
//  override def toString: String = "Empty List"
//
//}
//
//// This does work, but it's certainly not how teach did it!
//// I was treating it as if head was the very start of the list which is what threw me here!
//// Head was only supposed to return the data stored in the head of the list regardless of whether it
//// was pointed to by another's tail
//class IntList(val data: Int, parent: => IntList = null, child: => IntList = null) extends MyList {
//  override def head: IntList = if(parent != null) parent.head else this
//  override def tail: IntList = child
//  override def isEmpty: Boolean = false
//  override def add(x: Int): IntList = new IntList(x, null, head)
//
//  override def toString: String = {
//    def helper(acc: String = data.toString, node: => IntList = head): String = {
//      if (node.tail == null) acc
//      else helper(acc + ", " + node.tail.data, node.tail)
//    }
//    helper()
//  }
//}

// Now for teach's wisdom

// Extend an object from the class - always the same for an empty list so makes sense
object Empty extends MyList {
  // Empty lists have no head or tail so we'll throw exceptions instead
  def head: Int = throw new NoSuchElementException
  def tail: MyList = throw new NoSuchElementException
  def isEmpty: Boolean = true
  // Adding to an empty list will return a Cons list with element as the head and the Empty list as the tail:
  def add(element: Int): MyList = new Cons(element, Empty)

  def printElements: String = ""
}

class Cons(h: Int, t: MyList) extends MyList {
  def head: Int = h

  def tail: MyList = t

  def isEmpty: Boolean = false

  def add(element: Int): MyList = new Cons(element, this)
  def printElements: String = {
    if (t.isEmpty) "" + h
    else h + ", " + t.printElements
  }
}

object ListTest extends App {
  val list = new Cons(1, Empty)
  println(list.head)

  println(list.add(2).add(3).add(9).add(12).toString)
}