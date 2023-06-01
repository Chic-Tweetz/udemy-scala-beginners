package lectures.part2oop
// ^^^^ This line means that whatever we define in this file
// will be part of the lectures.part2oop package

// Side note: this is one of the few times in Scala
// where a line of code is NOT an expression!

// Import other packages to access package classes, objects, top-level definitions...
// Import them OR fully qualify the name you're trying to access
 import playground.{PrinceCharming, Cinderella}
//import playground._ // import everything in a package with ._

// Aliasing is useful if for example you import two different
// things with the same name:
import java.sql.Date // two types both called Date
import java.util.{Date => SqlDate} // Alias this one to SqlDate

object PackagingAndImports extends App {
  // Package members are accessible by their simple name
  val writer = new Writer("Daniel", "Rock the JVM", 2018)

  // If you are working in a different package,
  // import the package you want to use
  // val princess = new Cinderella // Requires "import playground.Cinderella"

  // Or fully qualify the name of whatever it is you're trying to use
  val princess = new playground.Cinderella // playground.Cinderella = fully qualified name

  // Packages are ordered hierarchically
  // Given by the dot notation, eg "lectures.part2oop"
  // Matching folder structure (best practise)

  // Package object - Scala specific
  // - definitions within are available throughout the package
  // rarely used in practise
  // rarely broken convention is to have one per package - called package.scala
  // which is where this method and constant are defined:
  sayHello
  println(SPEED_OF_LIGHT)

  // Imports

  // note that the IDE has grouped PrinceCharming with Cinderella at the top
  val prince = new PrinceCharming

  // Alias names on import with =>
  // see the two Date imports up top
  // val date = new Date

  // Option 1 on name collisions: use fully qualified name:
  val sqlDate1 = new java.sql.Date(2018, 5, 4)

  // Option 2 - use aliasing:
  val sqlDate2 = new SqlDate(2018, 5, 4)

  // Default imports:

  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???
}
