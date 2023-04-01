package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  // Tail Recursion is important to avoid stack overflow
  // Redefining factorial with a helper function:
  // (I already did this because I'm so smart wow)
  def factorial(n: Int): Int =
    @tailrec
    def factHelper(x: Int, accumulator: Int): Int =
      if (x <= 1) accumulator
      else factHelper(x - 1, x * accumulator)

    factHelper(n, 1)

  println(factorial(10))


  // 1. Multi concat function with tail recursion

  def concatNTimes(str: String, n: Int): String = {
    @tailrec
    def concatHelper(strAcc: String, i: Int): String =
      if (i <= 1) strAcc
      else concatHelper(strAcc + str, i - 1)

    concatHelper(str, n)
  }
  println(concatNTimes("hi! ", 5))

  // Start at 1 and count up seems to be the way for my brain
  def fibonacciTailRec(n: Int): Int = {
    @tailrec
    def fibHelper(i: Int, curr: Int, prev: Int): Int = {
      if (i >= n) curr
      else fibHelper(i + 1, curr + prev, curr)
    }

    fibHelper(1, 1, 0)
  }

  def printNFibTerms(n: Int): Unit = {
    @tailrec
    def printHelper(i: Int): Unit = {
      if (i <= n) {
        println(fibonacciTailRec(i))
        printHelper(i + 1)
      }
    }
    printHelper(1)
  }

  printNFibTerms(10)


}
