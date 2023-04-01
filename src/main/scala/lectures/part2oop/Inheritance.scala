package lectures.part2oop

object Inheritance extends App {

  // Access modifiers are the same as C++
  sealed class Animal {
    val creatureType = "Wild"
    protected def eat = println("nomnomnom")
    def speak = println("Animal noises")
  }

  // Single class inheritance - inherits non-private members of the superclass
  class Cat extends Animal {
    def crunch = {
      // Animal.Eat is protected so is accessible within this subclass
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  // cat.eat // eat is inaccessible outside of Animal and its subclasses
  cat.crunch

  // Constructors

  class Person(name: String, age: Int)

  // Superclass constructor must be called correctly
  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)

  // Overriding
  class Dog extends Animal {
    // Precede overrides with "override" keyword
    override def eat = {
      super.eat
      println("crunch crunch woof")
    }

    // vals can be overridden too:
    override val creatureType: String = "Domestic"
  }

  val dog = new Dog

  // This time eat is accessible because it's public in the Dog class
  dog.eat
  println(dog.creatureType)

  // Overrides can also be used in class parameters:
  final class Mouse(override val creatureType: String) extends Animal {
    override def speak = println("mew mew mew")
  }

  // Override creatureType directly in a constructor:
  val mouse = Mouse("Rodent")
  println(mouse.creatureType)

  // Type substitution (Polymorphism)
  val unknownAnimal: Animal = new Mouse("Danger Mouse")
  unknownAnimal.speak

  // The most overriden version of a function will be called whenever possible
  // (Seems the same as I'm used to)

  // Super - reference a method or field from a superclass
  // see definition of Dog.eat

  // Preventing overrides
  // 1 - use keyword "final" (same as C++) on member
  // 2 - use "final" on an entire class (see Mouse)
  // 3 - seal the class = extend class in THIS FILE, prevent extension in other files
  //    - see definition of Animal

}
