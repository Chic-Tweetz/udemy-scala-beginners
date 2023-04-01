package lectures.part1basics

object CallByNameOrValue extends App {

  def calledByValue(x: Long): Unit = {
    println("By value: " + x)
    println("By value: " + x)
  }

  // By name uses different syntax: =>
  def calledByName(x: => Long): Unit = {
    println("By name: " + x)
    println("By name: " + x)
  }

  // Value of System.nanoTime() passed, printed twice
  calledByValue(System.nanoTime())

  // System.nanoTime() evaluated twice, two different values
  calledByName(System.nanoTime())

  // Essentially the difference between passing value and reference
  // except with => instead of &

  // Execution of called by name parameter is delayed until it is used
  // See this example: infinite() would cause a stack overflow, but
  // as it's called by name it never actually executes

  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)

  // Works, never actually evaluating infinite():
  printFirst(32, infinite())

  // Doesn't work, attempts to evaluate infinite to pass its value,
  // causing a stack overflow:
  // printFirst(infinite(), 32)
}
