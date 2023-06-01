package lectures.part3fp

object WhatsAFunction extends App {

  // DREAM: Use and work with functions as first class elements
  // problem: OOP - writing classes with methods

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  // We can now call double like a function:
  println(doubler(2))

  // Function types are supported by default in Scala
  // function types = Function1 - Function22 (why 22?)
  // function types = Function1[A, B]

  // Override apply
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("3") + 4)

  // type of adder = (Int, Int) => Int
  // syntactic sugar for Function2
  val adder: (Int, Int) => Int = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  // Function types Function2[A, B, R] === (A, B) => R

  // ALL SCALA FUNCTIONS ARE OBJECTS
  // Or instances of classes deriving from Function1, Function2, etc

  /*
    Exercises

    1. Function which takes 2 strings and concatenates them
    2. Transform the MyPredicate and MyTransformer into function types in MyList
    3. Function which takes an arg (Int) and returns another function which takes an Int and returns an Int
       - what's the type of this function
       - how do you do it?
  */

  // 1.

  val conc = new ((String, String) => String) {
    override def apply(s1: String, s2: String): String = s1 + s2
  }

  println(conc("Hello, ", "Scala"))

  // 3.

  // Higher-order function
  val returnFunction = new (Int => (Int => Int)) {
    override def apply(n0: Int) = new (Int => Int) {
      override def apply(n1: Int) = n1 + n0
    }
  }

  val returnedFunction = returnFunction(4)
  println(returnedFunction(8))

  // This is what teach does:
  val superAdder: Function1[Int, Function[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val adder3 = superAdder(3)
  println(adder3(4))

  // We can also use superAdder like this:
  // CURRIED FUNCTION
  println(superAdder(3)(4)) // superAdder(3) returns its function which is immediately called with 4

}

// OOP approach:
// to access execute, we would instantiate the class
// (anonymously or not)
class ActionOop1 {
  def execute(element: Int): String = ???
}

// At most, an OOP-based approach would get us to something like:
trait ActionOop2[A, B] {
  def execute(element: A): B
}

// In Scala, we can go further (and further still):
trait MyFunction[A, B] {
  def apply(element: A): B
}