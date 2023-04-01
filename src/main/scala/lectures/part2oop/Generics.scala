package lectures.part2oop

// Scala's templating equivalent

object Generics extends App {

  // Use [A] to denote a generic type
  // Think template<typename A>

  // This is a Generic Class, with [A] being a type parameter
  class MyList[+A] {
    // Now we can use the type A

    // We pass in supertypes of A for B
    // And then return a list of type B:
    def add[B >: A](element: B): MyList[B] = ???
    /*
      A = Cat
      B = Animal
    */
  }

  // MyList[Int] ~ MyList<int>
  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // We can also have multiple type parameters:
  class MyMap[Key, Value]

  // And it works with traits too
  trait someTrait[A]

  // Objects can not be type parameterized

  // Generic Methods
  object MyList {
    // Here our method takes in a type parameter, A
    // and returns a list of type A (or would if it had a definition!)
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfIntegers = MyList.empty[Int]

  // Variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // Question: if Cat extends Animal,
  // does a MyList of Cat also extend MyList of Animal?

  // Answer 1: Yes
  // COVARIANCE:
  class covariantList[+A] // <-- +A means this is a covariant list
  // Now we can use the covariantList like we do when assigning
  // a subclass to a val of its superclass:
  val animal: Animal = new Cat
  val animalList: covariantList[Animal] = new covariantList[Cat]
  // Can we now add any Animal to this list?
  // animalList.add(new Dog) ??? Hard question

  // HERE IS THE ANSWER:
  // we return a list of Animals

  // Answer 2: No!
  // INVARIANCE
  class InvariantList[A]
  // Can't be done:
  // val InvariantAnimalList: InvariantList[Animal] = new InvariantList[Cat]

  // Answer 3: Hell, no!
  // CONTRAVARIANCE
  class ContravariantList[-A] // <-- Defined with a "-"
  // Note that the relationship is reversed here (Cat / Animal reversed):
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]


  // Bounded types
  // Upper bounded types (<:) :
  class Cage[A <: Animal](animal: A)  // Only accept SUBTYPES of Animal
  val cage = new Cage(new Dog)

  class Car
  // var newCage = new Cage(new Car) // Nope! Car does not extend Animal

  // Lower bounded types (only super classes) use the > sign instead ( >: )


  // Exercise: Expand MyList to be generic
}
