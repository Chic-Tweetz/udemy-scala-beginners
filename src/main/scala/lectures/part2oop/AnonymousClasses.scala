package lectures.part2oop

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  // ANONYMOUS CLASS:
  // We can instantiate abstract classes by overriding its abstract methods within the instance initialisation:
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("haha! lol, rofl, lmao")
  }
  funnyAnimal.eat

  // funnyAnimal's class is not actually abstract, it's real:
  println(funnyAnimal.getClass) // => "class lectures.part2oop.AnonymousClasses$$anon$1"

  // The compiler created a class: (AnonymousClasses$$anon$1") and funnyAnimal was instantiated as that type

  /* Equivalent to:
  class AnonymousClasses$$anon$1 extends Animal {
    override def eat: Unit = println("haha! lol, rofl, lmao")
  }
  val funnyAnimal: AnonymousClasses$$anon$1 = new AnonymousClasses$$a
  */

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name!")
  }

  // We can extend concrete classes anonymously, but you must pass any required constructor parameters:
  val jim = new Person("Jim") {
    override def sayHi: Unit = println("Jim Jim Jim Jim Jim!")
  }
  jim.sayHi

}
