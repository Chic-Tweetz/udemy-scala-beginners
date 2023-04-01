package lectures.part1basics

import scala.annotation.tailrec

object DefaultArgs extends App {

  @tailrec
  def trFactorial(n: Int, acc: Int = 1): Int =
    if (n <= 1) acc
    else trFactorial(n - 1, n * acc)

  // This is only useful if the acc argument starts with 1
  // So we can either wrap trFactorial in another function with 1 parameter
  // Or we can make acc have a default value of 1

  def someMultiArgFunction(str: String = "hello", a: Int = 500, b: Int = 100): Unit = println("foobar")

  // Instead of passing in values which match the default up to one you want to change like this:
  someMultiArgFunction("hello", 500, 20) // <-- only the last param differs from default

  // Arguments can be named:
  someMultiArgFunction(b = 20)

  // And can even be passed in a different order thanks to this:
  someMultiArgFunction(a = 1, str = "world")

}
