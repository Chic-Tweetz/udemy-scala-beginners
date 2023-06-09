package lectures.part3fp

object AnonymousFunctions extends App {

  // In order to get away from the OOP-style Function type instantiation (like the following)
  val doubler1 = new Function1[Int, Int] {
    override def apply(x: Int) = x * 2
  }

  // Scala allows us to use the following syntax instead:
  val doubler2 = (x: Int) => x * 2

  // Syntactic sugar for all the FunctionX[Type1, ... , TypeN] { override def apply ... etc
  // This (the RHS after =) is called an Anonymous Function or Lambda

  // By the by, "Lambda" comes from Lambda Calculus, the mathematical foundation of functional programming

  // Our lambda is an instance of Function1
  // We can see that doubler's type is "Int => Int" and we can declare it as such:
  val doubler3: Int => Int = (x: Int) => x * 2

  // And now, given that we've declared doubler's type, we can shorten the definition to:
  val doubler4: Int => Int = x => x * 2

  // And as long as we provide a type for x, the compiler can infer a type for doubler:
  val doubler5 = (x: Int) => x * 2

  // Multiple params in a lambda:
  val adder1 = (a: Int, b: Int) => a + b

  // If we want to provide a type for adder, we'll need to provide the params' types in parentheses:
  val adder2: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // No parameters = empty parentheses:
  val justDoSomething1 = () => 3

  // And the type for justDoSomething is "() => Int":
  val justDoSomething2: () => Int = () => 3

  // Note the difference between calling justDoSomething with () and omitting it here:
  println(justDoSomething2) // function itself
  println(justDoSomething2()) // call

  // Despite methods being callable when omitting the (), lambdas must be called with parentheses ()

  // Curly braces with lambdas, a common style:
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // MOAR SYNTACTIC SUGAR!
  val niceIncrementer: Int => Int = _ + 1 // Equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // Equivalent to (a, b) => a + b

  /*
    Exercises:
    1. MyList: replace all FunctionX calls with lambdas
    2. Rewrite the "super" adder from "WhatsAFunction" as an anonymous function
  */

  // 2

  // Original definition:
  //  val superAdder: Function1[Int, Function[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
  //    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
  //      override def apply(y: Int): Int = x + y
  //    }
  //  }

  // Easiest to puzzle out:
  val sa = { (x: Int) =>
    (y: Int) => x + y
  }

  // Let the compiler deduce types:
  val sa1 = (x: Int) => (y: Int) => x + y

  // Explicit type, with the _ style - could we get two underscores here?:
  val sa2: Int => Int => Int = (y: Int) => _ + y

  // Teach's superAdd:
  val superAdd = (x: Int) => (y: Int) => x + y // Hey that's my sa1, nice!

  println(superAdd(3)(4))


}
