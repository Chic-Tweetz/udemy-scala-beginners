package lectures.part2oop
import scala.annotation.{tailrec, targetName}

object OOBasics extends App {

  val person = new Person("John", 26)
  println(person)
  // println(person.age) // Doesn't work - params are not fields!
  println(person.name) // Does work, name is preceded by "val"
  println(person.x)

  person.greet("Daniel")
  person.greet()

  // Exercise instances:
  // 1.
  val writer = new Writer("Mister", "Inkyfinger", 1842)

  println(s"The author, ${writer.fullName()} was born in ${writer.birthYear}.")

  val novel = new Novel("Star Trek II: The Wrath of Khan", 1982,
    new Writer("Jack", "Sowards", 1929))

  println(s"${novel.name} is a movie not a novel and was written by ${novel.author.fullName()} in ${novel.releaseYear}.")
  println(s"FACT or FICTION?: ${writer.fullName()} wrote ${novel.name}: ${novel.isWrittenBy(writer)}")
  println(s"FACT or FICTION?: ${novel.author.fullName()} wrote ${novel.name}: ${novel.isWrittenBy(novel.author)}")

  val rerelease = novel.copy(2022)

  println(s"${rerelease.name} had a cinematic run for its 40th anniversary in ${rerelease.releaseYear}.")
  println(s"${novel.author.fullName()} would be ${novel.author.age()} years old.")

  println(s"${novel.name} was released when he was ${novel.authorAge()}.")

  //2.
  // Well this is taking up a lot of space
  val counter = new Counter()
  println(s"Counter: ${counter.getCount()}")

  val counter2 = counter.increment()
  println(s"Counter.increment(): ${counter2.getCount()}")

  val counter3 = counter2.increment(33)
  println(s"Counter.increment(33): ${counter3.getCount()}")

  val counter4 = counter3.decrement()
  println(s"Counter.decrement(): ${counter4.getCount()}")

  val counter5 = counter4.decrement(12)
  println(s"Counter.decrement(12): ${counter5.getCount()}")

  val counter6 = counter5 + 5
  println(s"Counter + 5: ${counter6.getCount()}")

  val counter7 = counter6 - 7
  println(s"Counter - 7: ${counter7.getCount()}")

  println("Logging (& recursing) versions:")
  counter7.inc().inc(6).dec().dec(3)


}

// Constructor syntax:
class Person (val name: String, age: Int) {
  // body

  // val / var definitions
  // method definitions
  // expressions
  val x = 2 // This is a field
  println(1 + 4) // 4 will be printed every time the class is instantiated

  // method
  // Note that greet's "name" parameter shadows Person's name field
  // Instances' fields can be accessed with this eg this.name
  def greet(name: String): Unit = println(s"$this.name says, \"Hi, $name!\"")

  // this is usually implied, as in here where $name == $this.name
  def greet(): Unit = println(s"$name says \"Hi!\"")

  // Which also brings us to overloading & polymorphism
  // which follows familiar rules

  // Multiple constructors:
  // Pass in only a name, and call the primary constructor with that
  // as the name param:
  def this(name: String) = this(name, 0)

  // Unfortunately auxiliary constructors can only be used to
  // call another constructor, meaning they're generally used
  // for default values
  // eg this one calls that last one (which calls the primary one):
  def this() = this("John Doe")

  // So we might as well just supply a default parameter in the
  // class definition:

  // class Person(name: String = "John Doe", age: Int = 0) {}

  /* Exercises:
  1.
  Implement a Novel and a Writer class
  Writer: first name, surname, birth year
    - method fullname

  Novel: name, release year, author
    - methods:
      authorAge
      isWrittenBy(author)
      copy(new year of release) = new instance of Novel with new year of release

  2.
  Create a counter class
    - receives an int
    - method returning current count
    - increment and decrement methods returning new Counter
    - overload inc/dec to receive an amount

  */
}

// 1.
class Writer(val firstName: String, val surname: String, val birthYear: Int) {
  def fullName(): String = firstName + " " + surname
  // I misunderstood Novel.authorAge a bit there didn't I but let's keep it here
  def age(): Int = {
    // Or 2023 if you want to be magical about it
    java.time.LocalDate.now.getYear - birthYear
  }

}

// Teach didn't make these parameters into fields which is worth mentioning
// just so you know the methods will all still work and that y'know
class Novel(val name: String, val releaseYear: Int, val author: Writer) {
  def authorAge(): Int = releaseYear - author.birthYear
  // Personally I would name isWrittenBy's author something else
  // but it was an example earlier so we'll use this.author
  def isWrittenBy(author: Writer): Boolean = author == this.author

  def copy(year: Int): Novel = new Novel(name, year, author)

}

class Counter(val count: Int = 0) {
  // Teach just uses Counter.count which does make sense
  // I'm just used to writing getters
  // But seeing as this is all immutable there's no danger
  // in accessing fields directly is there
  def getCount(): Int = count

  // This is important! We're extending immutability to classes here
  // Not incrementing this.count, but returning a NEW instance
  // with count + 1 as its count value

  // btw look at me spelling the full words out instead of inc and dec pssht
  def increment(): Counter = new Counter(count + 1)
  // Would make more sense to default to 1 but the exercise demands overloads!
  def increment(by: Int): Counter = new Counter(count + by)
  def decrement(): Counter = new Counter(count - 1)
  def decrement(by: Int): Counter = new Counter(count - by)

  // Can we overload operators? (Intellij told me to add @targetName)
  @targetName("add")
  def +(add: Int): Counter = increment(add) // It would appear so!
  @targetName("sub")
  def -(sub: Int): Counter = decrement(sub)

  // Teach is making logging versions which we want to recurse:
  def inc(): Counter = {
    println(s"incrementing $count to ${count + 1}")
    new Counter(count + 1)
  }

  def inc(by: Int): Counter = {
    if (by <= 0) this
    else inc().inc(by - 1)
  }

  def dec(): Counter = {
    println(s"decrementing $count to ${count - 1}")
    new Counter(count - 1)
  }

  def dec(by: Int): Counter = {
    if (by <= 0) this
    else dec().dec(by - 1)
  }


}

// name and age are parameters but NOT MEMBERS
// CLASS PARAMETERS ARE NOT FIELDS!

// Add the val (or var) keyword before the parameter to make them so