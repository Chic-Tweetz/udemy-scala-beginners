package lectures.part1basics

object StringOps extends App {

  val str: String = "Hello, I am learning Scala"

  // Strings have a number of standard methods:

  // Java methods
  println(str.charAt(2))
  println(str.substring(7, 11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello")) // true / false
  println(str.replace(" ", "-"))
  println(str.toLowerCase())
  println(str.length)

  // Scala specific
  val aNumberString = "45"
  val aNumber = aNumberString.toInt
  println('a' +: aNumberString :+ 'z') // prepend, append: +: :+
  println(str.reverse)
  println(str.take(2)) // First 2 chars

  // Scala specific: String interpolators

  // S-interpolators
  val name = "David"
  val age = 12
  val greeting = s"Hello, my name is $name and I am $age years old"
  val anotherGreeting = s"Hello, my name is $name and I will be turning ${age + 1} years old"

  println(greeting)
  println(anotherGreeting)

  // F-interpolators (printf style formatted strings)
  val speed = 1.2f

  // %s = string, %2.2f = float, 2 chars min, 2 decimals precision
  val myth = f"$name%s can eat $speed%2.2f burgers per minute"

  // Can also be used for type correctness:
  // val x = 1.1f // a Float
  // val s = f"$x%3d" // %3d expects an int, but x is a float so the compiler will complain

  // Raw-interpolator
  println(raw"This is a \n newline") // Will literally print the \n

  // However, injected variables will still be escaped:
  val escaped = "This is a \n newline"
  println(raw"$escaped")

}
