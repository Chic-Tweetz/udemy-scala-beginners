package lectures.part2oop

// Case Classes (we'll abbreviate to CC)
// Are a fast way of defining lightweight data structions with little boilerplate
// - companions already implemented, with handy apply methods
// - sensible methods implemented OOTB like equals, hashCode, toString
// - auto-promoted params to fields
// - cloning
// - serializable
// - extractable for pattern matching
// - case objects, which act like case classes but are objects!
object CaseClasses extends App {
  /*
    to avoid having to write lots of boilerplate code for simple classes, ie
    equals, hashCode, toString, etc
    case classes let us define a class, companion object, sensible default values in one go
  */

  // Only difference in declaration is the "case" keyword
  case class Person(name: String, age: Int)
  // case keyword achieves multiple things:

  // 1. class parameters are promoted to fields
  val jim = new Person("Jim", 34)
  println(jim.name)

  // 2. sensible toString for ease of debugging
  println(jim.toString)
  // An aside, Java already has this syntactic sugar:
  // println(instance) = println(instance.toString)
  println(jim) // = jim.toString

  // 3. equals and hashCode implemented OOTB
  val jim2 = new Person("Jim", 34)
  println(jim == jim2) // returns true

  // 4. CCs have handy copy method
  // creates a new instance of the case class
  // & can also receive named parameters
  val jim3 = jim.copy(age = 45)
  println(jim3)

  // 5. CCs have companion objects
  val thePerson = Person
  // and companion objects have handy factory methods
  val mary = Person("Mary", 23)

  // 6. CCs are serializable
  // - especially useful when dealing with distrubuted systems
  // - useful for sending over networks between JVMs
  // Akka framework for instance

  // 7. CCs have extractor patterns
  // = CCs can be used in PATTERN MATCHING
  // which we'll talk about later in the course!

  // 8. There is such a thing as a case object
  // Same properties as case classes, except they don't
  // get companion objects because they ARE companion objects
  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

  /*
    Excercise:

    Expand MyList to use case classes and case objects

    - added case keyword to Empty and Cons definitions
    and that's all you need!
  */


}
