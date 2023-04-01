package lectures.part1basics

import scala.annotation.tailrec

object Functions extends App {

  // With or without curly braces
  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("hello", 3))

  def aParameterLessFunction(): Int = 42

  println(aParameterLessFunction())
  // println(aParameterLessFunction) // <-- this would work in Scala 2 (call function with just its name)

  // Now we'll write a function to concat a string n times
  // Without an imperative style loop
  // Recursion is the way:
  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n - 1)
  }

  println(aRepeatedFunction("hello", 3))

  // WHEN YOU NEED LOOPS, USE RECURSION

  // The compiler can infer function return types as it can with val types
  // BUT you cannot delete the return type from a recursive function
  // For best practice, specify a return type either way

  // Return Unit if you only want side effects (like void remember)
  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  // Functions can have functions within them:
  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n - 1)
  }

  /* Tasks:
    1. A greeting function (name, age) => "Hi, my name is $name and I am $age years old"
    2. A factorial function
    3. A fibonacci function
    4. A test if prime function
  */

  // 1:

  def greet(name: String, age: Int): Unit =
    println("Hi, my name is " + name + " and I am " + age + " years old")


  greet("Paris", 5)

  // 2:
  def factorial(n: Int): Int =
    if (n <= 2) n else n * factorial(n - 1)

  // This version is tail recursive:
  @tailrec
  def factorialTR(n: Int, acc: Int = 1): Int = {
    if (n == 2) acc * 2 else {
      factorialTR(n - 1, acc * n)
    }
  }

  println("Factorial: " + factorialTR(5))

  // 3:
  // Also not tail recursive but I'm struggling to think how to make it so:
  def fibonacci(n: Int): Int =
    if (n <= 2) 1 else {
      fibonacci(n - 2) + fibonacci(n - 1)
    }

  println("Fibonacci: ")
  def printFibonacci(n: Int): Unit = {
    @tailrec
    def fromOne(i: Int = 1): Unit = {
        if (i <= n) {
          println(fibonacci(i))
          fromOne(i + 1)
        }
    }

    fromOne()
    }

  printFibonacci(10)

  def isPrime(n: Int): Boolean = {
    @tailrec
    def primeCheck(divisor: Int = 2): Boolean  = {
      if (divisor > n / 2) true
      else if (n % divisor == 0) false
      else primeCheck(divisor + 1)
    }
    primeCheck()
  }

  println("List primes: ")

  // Definitely not efficient, but seems correct at a glance
  def listPrimesTo(n: Int = 50): Unit = {
    @tailrec
    def fromOne(i: Int = 1): Unit = {
      if (i <= 200) {
        if (isPrime(i)) {
          println(i)
        }
        fromOne(i + 1)
      }
    }

    fromOne()
  }

  listPrimesTo(200)

}
