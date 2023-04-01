package lectures.part2oop

object Objects /* extends App */{ // App already has the main method defined
  // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")
  // ... it has something better!

  // To use class level definitions, we use objects
  object Person { // Define its type and its only instance
    // "static" / "class"-level functionality
    val N_EYES = 2
    def canFly: Boolean = false

    // Factory method (builds instances of the Person class):
    def from(mother: Person, father: Person): Person = new Person("Bobby")

    // Which are often conveniently defined using apply:
    def apply(mother: Person, father: Person): Person = new Person("Bobby")
  }
  class Person(val name: String) {
    // Instance-level functionality
  }
  // COMPANIONS - reside in the same scope with the same name

  def main(args: Array[String]): Unit = {

    println(Person.N_EYES)
    println(Person.canFly)

    // Scala object = SINGLETON INSTANCE by definition
    val mary = Person
    val john = Person

    // Prints true as they both point to the Person singleton
    println(mary == john)

    // This means that whether we're accessing the object or the class,
    // we are actually accessing an INSTANCE
    // (either the singleton object, or an instance of the class)
    // Scala is truly object oriented! Even more so than most others!

    val harry = new Person("Harry")
    val juan = new Person("Juan")

    // Prints false, harry and juan are two different instances of the Person class
    println(harry == juan)

    // Use the factory method in Person object to create a Person class instance
    val bobby = Person.from(harry, juan) // I guess Bobby is adopted

    // Using the overloaded apply(), now the factory method behaves like a constructor:
    val bigBobby = Person(harry, juan)

    // Very widely used design pattern

    // Scala Applications = Scala object with

    // def main(args: Array[String]): Unit

    // They require this signature because it becomes a Java Virtual Machine app
    // whose entry points need to be
    // public static void main(args: Array[String])

    // So we have Unit becoming void
    // and our singleton App becoming the static part

    // If we remove the extends App part of this object, it won't compile
    // UNLESS we re-add that function signature (we'll do this now!)



  }

}
