package lectures.part2oop

object AbstractDataTypes extends App {

  // Abstract - at least one declaration without a definition
  abstract class Animal {
    val creatureType: String = "wild" // Note that it's still abstract despite this
    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType = "dog"

    // As in C++, override is not a necessity, but does cause an error
    // when trying to override something that doesn't exist
    def eat: Unit = println("crunch crunch")

  }

  // Traits
  // Extend classes with members of traits as well as superclasses:
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  // You can mix in as many traits as you want

  trait ColdBlooded {
    def blood: Unit = println("brrr my blood is cold")
    val preferredMeal: String = "fresh meat"
  }

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"
    override def eat: Unit = println("SNAP")
    override def eat(animal: Animal): Unit = {
      println(s"$creatureType eats ${animal.creatureType}")
    }
  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)
  croc.blood

  // Traits vs abstract classes
  // 1 - traits do not have constructor parameters
  // 2 - classes can inherit from one superclass but multiple traits
  // 3 - traits = behaviour, abstract = thing

  // Scala Type Hierarchy:
  // scala.Any <-- mother of all types!
  //  - scala.AnyRef (java.lang.Object) <-- mapped to java's object type
  //    all classes you use will derive from AnyRef unless specified otherwise
  //        - scala.Null extends everything - you can replace anything with scala.Null
  //  - scalaAnyVal - Int, Unit, Boolean, float, etc, primitive types
  //    rarely used in practice
  // Finally, scala.Nothing extends all the above
  // scala.Nothing can replace anything


}
