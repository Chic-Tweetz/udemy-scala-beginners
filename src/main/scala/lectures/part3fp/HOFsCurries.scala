package lectures.part3fp

object HOFsCurries extends App {

   var superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null

  // Higher Order Function (HOF)

  // function that applies a function n times over a value x:
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x))) = nTimes(f, 2, f(x)) = f(f(f(x)))
  // nTimes(f, n, x) = f(f(...(x))) = nTimes(f, n-1, f(x))

  // See how functional programming is a direct mapping of branches of mathematics?

  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n-1, f(x))

  val plusOne = (x: Int) => x + 1
  println(nTimes(plusOne, 10, 1)) // Prints 11 (1 incremented 10 times)

  // nTimesBetter(f, n) = x => f(f(f...(x)))
  // increment10 = ntb(plusOne, 10) = x => plusOne(plusOne...(x))
  // val y = increment10(1)

  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n-1)(f(x)) // nTimesBetter APPLIED to f(x)

  val plus10 = nTimesBetter(plusOne, 10)
  println(plus10(1)) // plus10 applies plusOne to whatever value it's given

  // Curried Functions

  // Going back to our superAdder
  val superAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y
  val add3 = superAdder(3) // add3 is a lambda: y => 3 + y
  println(add3(10)) // 13
  println(superAdder(3)(10)) // 13

  // functions with multiple parameter lists (to act like curried functions):
  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  // Using curriedFormatter, we can now create as many formats as we like

  // Reminder: %4.2f = minimum 4 digits, 2 decimals precision
  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI)) // 3.14
  println(preciseFormat(Math.PI))  // 3.14159265

  // Curried functions like these can have as many parameter lists as you like

  /*

  Exercises

  1. Expand MyList
     - foreach A => Unit
       [1,2,3].foreach(x => println(x)) // should print each element in the list

     - sort ((A, A) => Int) => MyList
       [1,2,3].sort((x, y) => y - x) => [3,2,1] // -ve or +ve return decides sort order

     - zipWith (list, (A, A) => B) => MyList[B]
       [1,2,3].zipWith([4,5,6]), x * y) => [1 * 4, 2 * 5, 3 * 6] = [4, 10, 18]

     - fold(start)(function) => a value
       [1,2,3].fold(0)(x + y) = 6

  2.  toCurry(f: (Int, Int) => Int) => (Int => Int => Int)
      fromCurry(f: (Int => Int => Int)) => (Int, Int) => Int

  3.  compose(f,g) => f(g(x))
      andThen(f,g) => g(f(x))

  */

  // 2

  def toCurry(f: (Int, Int) => Int): (Int => Int => Int) =
    x => y => f(x, y)

  def fromCurry(f: (Int => Int => Int)): (Int, Int) => Int =
    (x, y) => f(x)(y)

  def uncurriedAdd(x: Int, y: Int): Int = x + y
  def curriedAdd(x: Int)(y: Int): Int = x + y

  val curriedUncurriedAdd = toCurry(uncurriedAdd)
  val uncurriedCurriedAdd = fromCurry(curriedAdd)

  println(curriedAdd(25)(12))
  println(uncurriedAdd(57, 128))

  val superAdder2: (Int => Int => Int) = toCurry(_ + _) // That's neat!
  val add4 = superAdder2(4)

  println(add4(17)) // 21

  val simpleAdder = fromCurry(superAdder2)
  println(simpleAdder(4, 17)) // 21

  // hey they worked!

  // 3

  // Generalising from just Ints initially
  // g must return a type that matches f's parameter's type
  // x must be whatever type g takes
  // compose must return whatever type f returns
  // it's all very logical!
  def compose[A, B, C](f: A => B, g: C => A): C => B =
    (x: C) => f(g(x))

  def add50(x: Int) = x + 50
  def mul12(x: Int) = x * 12

  val composed = compose(add50, mul12)
  println(composed(3)) // (3 * 12) + 50 = 86

  def andThen[A, B, C](f: A => B, g: B => C): A => C =
    (x: A) => g(f(x))

  val andThened = andThen(add50, mul12)
  println(andThened(3)) // 12 * (3 + 50) = 636

  // Generalised


}
