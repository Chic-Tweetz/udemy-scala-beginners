package lectures.part2oop

object Exceptions extends App {

   val x: String = null
  // println(x.length) // Crash! NullPtrException

  // 1. Throwing exceptions

  // Exceptions are instances of classes

  // Valid value: "throw new NullPointerException"
  // but its type is Nothing, so we can assign it to any type:
  // val aWeirdValue: String = throw new NullPointerException

  // Throwable classes extend the Throwable class
  // Exceptions and Errors
  // Exceptions - something went wrong with the program
  // Errors - something went wrong with the system (eg stack overflow)

  // 2. Catching exceptions

  def getInt(withExceptions: Boolean) : Int =
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42

  // try catch finally expressions
  // as it's Scala, these can be assigned to a value

  // This particular try catch finally expression returns type AnyVal:

  // try block, thanks to getInt, tries to return Int
  // catch block returns println which is Unit

  // these can't be unified so the compiler chooses AnyVal

  // If our catch block returned Int,
  // potentialFail would be type Int instead of AnyVal

  val potentialFail = try {
    // Code that might throw
    getInt(true)
  } catch {
    // Catch types you expect might be thrown
    case e: RuntimeException => println("caught a runtime exception")
  } finally {
    // finally block is OPTIONAL
    // does not influence return type of try catch expression
    // use only for side effects
    // code that will be executed NO MATTER WHAT
    println("finally")
  }

  // 3. Defining your own exceptions
  class MyException extends Exception
  val exception = new MyException

  try {
    throw exception
  } catch {
    case e: MyException => println("caught a MyException")
  }

  /*
    Exercises

    1. Crash your program wih an OutOfMemoryError
    2. Crash with SOError
    3. PocketCalculator
         - add(x, y)
         - subtract(x, y)
         - multiply(x, y)
         - divide(x, y)

         Throw
           - OverflowException if add exceeds Int.MAX_VALUE
           - UnderflowException if subtract goes below Int.MIN_VALUE
           - MathCalculationException for division by 0
  */

  // 1.
  // OOM error
  try
  {
    val eatMemoryArr: Array[Int] = Array.ofDim(Int.MaxValue)
  } catch {
    case e: OutOfMemoryError => println("OOM error")
  }


  // 2.
  // SO
  def overflowStack: Int = 1 + overflowStack

  try{
    overflowStack
  } catch {
    case e: StackOverflowError => println("SO error")
  }


  // 3.
  class UnderflowException extends RuntimeException("integer underflow")
  class OverflowException extends RuntimeException("integer overflow")
  class MathCalculationException extends RuntimeException("divide by zero")

  object PocketCalculator {

    def add(x: Int, y: Int) =
      val result = x + y
      if (x > 0 && y > 0 && 0 > result) throw new OverflowException
      else if (y < 0 && x < 0 && 0 < result) throw new UnderflowException
      else result

    def sub(x: Int, y: Int) =
      add(x, -1 * y)

    def mul(x: Int, y: Int) =
      val result = x * y
      if (((x > 0 && y > 0) || (x < 0 && y < 0)) && 0 > result) throw new OverflowException
      else if ((x < 0 && y > 0) || (x > 0 && y < 0) && 0 < result) throw new UnderflowException
      else result

    def div(x: Int, y: Int) =
      if y == 0 then throw new MathCalculationException
      else x / y
  }

//  min = -2147483648, max = 2147483647

  try {
    PocketCalculator.add(2147483647, 1)
  } catch {
    case e: Exception => println(e.getMessage)
  }

  try {
    PocketCalculator.sub(-2147483648, 1)
  } catch {
    case e: Exception => println(e.getMessage)
  }

  try {
    PocketCalculator.div(5, 0)
  } catch {
    case e: Exception => println(e.getMessage)
  }
}
