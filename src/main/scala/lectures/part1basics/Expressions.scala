package lectures.part1basics

object Expressions extends App {
  val x = 1 + 2 // RHS (1 + 2) = an expression, evaluates to a value with a type
  println(x)

  println(2 + 2 * 4)
  // Some maths and bitwise operators:
  // + - * / & | ^ << >>   and also this Scala specific: >>> (right shift with zero extension)

  println(1 == x) // Evaluates to a bool
  // And of course the comparison operators: == != > >= < <=

  println(!(1 == x))
  // ! && ||

  var aVariable = 2
  aVariable += 3 // Plus -= *= /= ... Side effects
  println(aVariable)

  // Instructions (DO) vs Expressions (VALUE)

  // IF expression
  val aCondition = true

  // Seems to work like ternary operator ? :
  // Rather than branching
  // This is why it's an IF EXPRESSION, not an INSTRUCTION
  val aConditionedValue = if(aCondition) 5 else 3 // IF EXPRESSION
  println(aConditionedValue)

  println(if(aCondition) 5 else 3) // As it's an expression, we can use it in place of the val

  // There are loops in Scala, but they're DISCOURAGED -
  // they don't return anything meaningful and only execute side effects
  // Here is a while loop, the last of its kind you will see in Scala:

  var i = 0
  while (i < 10) {
    println(i)
    i += 1
  }

  // NEVER WRITE THIS AGAIN! BAD!

  // We'll learn how to approach iteration later

  // Loops are for imperative programming, we don't like them for functional programming!

  // EVERYTHING in Scala is an EXPRESSION!
  // Only definitions are not


  val aWeirdValue = (aVariable = 3) // Type is Unit === void
  println(aWeirdValue) // () - the only value Unit will give

  // Side effects are expressions returning Unit
  // While expressions return Unit

  // Side effects: println(), whiles, reassigning of vars
  // These all return Unit

  // These are all EXPRESSIONS, not INSTRUCTIONS, they return a value and that value is UNIT

  // Code Blocks

  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2) "hello" else "goodbye"
  }

  // Code blocks are, of course, expressions!
  // The value of a block is the value of its last expression

  // Vals can be declared within code blocks and their scope will be limited to it

  // Questions

  // 1. Difference between "hello world" and println("hello world")
  //    1st is a string expression (a literal), 2nd is a side effect - it returns Unit
  //    while having the SIDE EFFECT of printing to the console

  // 2. What are the values of:

  val someValue = {
    2 < 3
  }

  //  The last line in the code black is a comparison expression returning a true boolean (2 is less than 3)

  val someOtherValue = {
    if (someValue) 239 else 986
    42
  }

  // The last line of the expression is 42, so that's what someOtherValue will be

}
