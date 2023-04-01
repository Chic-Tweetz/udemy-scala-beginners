package lectures.part2oop
import scala.language.postfixOps
object Notations extends App {

  class Person(val name: String, favouriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favouriteMovie
    def hangOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(person: Person): String = hangOutWith(person)
    def unary_! : String = s"$name says WHAT MY LITTLE DUCK?!"
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favouriteMovie"

    // Exercises:
    // 1.
    def +(nickname: String): Person = new Person(s"$name ($nickname)", favouriteMovie, age)

    // 2.
    def unary_+ : Person = Person(name, favouriteMovie, age + 1)

    // 3.
    def learns(subject: String): String = s"$name learns $subject"
    def learnsScala: String = this learns "Scala"

    // 4.
    def apply(n: Int): String = s"$name watched $favouriteMovie $n times"

  }

  val mary = new Person("Mary", "Inception")

  // Note this natural-language style method calling:
  println(mary.likes("Inception"))
  println(mary likes "Inception")

  // infix notation = operator notation (syntactic sugar)

  // only works with methods with one parameter
  val hairy = new Person("Hairy", "Fight Club")
  println(mary hangOutWith hairy)

  // Called operator notation as the method name acts like an operator
  // Cool!

  // "operators" in Scala
  val contrary = new Person("Contrary", "Silence of the Lambs")
  // And Scala has very permissive method naming (method is called + ):
  println(mary + contrary)

  // In fact ALL operators are methods and can be called as such:
  println(1.+(2))

  // Prefix notation (more syntactic sugar)
  // All about unary operators
  val x = -1 // equivalent to 1.unary_-
  val y = 1.unary_-
  // unary_ prefix only works with - + ~ !

  println(!mary) // equivalent to:
  println(mary.unary_!)

  // Postfix notation
  // methods which require 0 params
  // in practice, the only difference is a . vs a space
  println(mary.isAlive)
  println(mary isAlive)

  // Requires import scala.language.postfixOps since scala 2.13.0

  // apply
  println(mary.apply()) // Equivalent to:
  println(mary())

  // So that's like overloading operator() in c++

  // Apply is important!

  /* Exercises:
    1.  Overload the + operator
          mary + "the rockstar" => new Person "Mary (the rockstar)"

    2.  Add an age to the Person class
        Add a unary + operator => new Person with age + 1
        +mary => mary with the age incremented

    3.  Add a "learns" method in the Person class => "Mary learns Scala"
        Add a learnScala method, calls learns method with "Scala" as a param
        Use it in postfix notation

    4.  Overload the apply method
        mary.apply(2) => "Mary watched Inception 2 times"

  */

  // 1.
  println((mary + "the rockstar")())

  // 2.
  println(s"${mary.name} turned ${(+mary).age}")

  // 3.
  println(mary.learns("C++"))
  println(mary learns "Java")
  println(mary learnsScala) // import scala.language.postfixOps since 2.13.0!

  // 4.
  println(mary.apply(46))
  println(mary(47))

}
