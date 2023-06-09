package lectures.part3fp

import exercises.{ MyList, Cons, Empty }

object MapFlatmapFilterFor extends App {

  // Standard library list implementation
  // https://www.scala-lang.org/api/2.13.3/scala/collection/immutable/List.html
  val list = List(1,2,3)
  println(list)

  // Has methods we implemented in our MyList:
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  val toPair = (x: Int) => List(x, x+1) // (1, 2) , (2, 3), (3, 4)
  println(list.flatMap(toPair))

  // foreach
  list.foreach(println)


  // Excercise:

  // "iterating"

  // print all combinations between two lists:
  val numbers = List(1,2,3,4)
  val chars = List('a','b','c','d')

  // Result should print all combos ie:
  // List("a1", "a2", ... "d4")

  // use flatMap to generate a list for each number
  // then use map to traverse chars and append each number to each char
  // "" just to get an empty string to + to
  val combinations = numbers.flatMap(n => chars.map(c => "" + c + n))
  println(combinations)

  // This is a kind of functional equivalent to imperative loops
  // - a combination of flatMap and map

  // and an example where we have three lists:
  val colours = List("red", "green", "blue")

  // note that we've switched from char.map to char.flatMap
  val tripleCombo = numbers.flatMap(n => chars.flatMap(ch => colours.map(col => "" + ch + n + "-" + col)))
  println(tripleCombo)

  // Scala has a way of writing these long chains of maps and flatMaps in a much more readable way:

  // for-comprehensions
  // <- operator
  // yield keyword

  // we can filter using a guard - see the if statement after numbers

  val forCombinations = for {
    n <- numbers if n % 2 == 0 // filtering to keep only even using a guard
    c <- chars
    colour <- colours
  } yield ("" + c + n + '-' + colour)

  println(forCombinations)

  // side effects like using foreach:
  for {
    n <- numbers
  } println(n)

  // compiler will rewrite these for-comprehensions into actual functional calls

  // syntax overload
  // this style is sometimes used:
  val listDoubled = list.map { x=>
    x * 2
  }

  println(listDoubled)

  // Exercises
  /*
    1. See whether MyList supports for comprehensions
    2. A small collection of at most ONE element - Maybe[+T]
       - only either an empty or containing 1 element of type T
       - map, flatMap, filter
  */

  // 1.

  val myList = Cons(1, Cons(2, Cons(3, Cons(4, Empty))))
  println(myList)

  val forMyList = for {
    ml <- myList
  } yield (ml + 3)

  println(forMyList)

  // MyList supports for-comprehensions due to our implementing filter, map, flatMap & foreach

  // these need to match these signatures:

  // map(f: A => B) => MyList[B]
  // filter(p: A => Boolean) => MyList[A]
  // flatMap(f: A => MyList[B]) => MyList[B]



  // 2.

  // -- moved to exercises/Maybe 
}
