package lectures.part2oop

object Enums {

  // First-class support since Scala 3

  // Define with keyword enum,
  // create instances in a list after the keyword case

  // enums are datatypes, so we can add fields and methods

  enum Permissions {
    case READ, WRITE, EXECUTE, NONE

    // Add fields / methods
    def openDocument(): Unit =
      if (this == READ) println("opening document...")
      else println("reading not allowed.")
  }

  // Access instances with "."
  val somePermissions: Permissions = Permissions.READ

  // Constructor args - a bit wordy, but it works!
  enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4) // 100
    case WRITE extends PermissionsWithBits(2) // 010
    case EXECUTE extends PermissionsWithBits(1) // 001
    case NONE extends PermissionsWithBits(0) // 000
  }

  // Can have companion objects
  object PermissionsWithBits {
    def fromBits(bits: Int): PermissionsWithBits = // whatever
      PermissionsWithBits.NONE // return some const instance from our enum
  }

  // Standard API:
  // ability to inspect the index or ordinal at which particular enum instance was defined
  // eg somePermissions' ordinal is 0 because READ is defined first in Permissions
  private val somePermissionsOrdinal = somePermissions.ordinal

  // Iterate over / get all possible instances of enum
  private val allPermisions = PermissionsWithBits.values // array of all possible values of the enum

  // Create an instance of an enum from a string
  val readPermission: Permissions = Permissions.valueOf("READ") // Permissions.READ

  def main(args: Array[String]): Unit = {
    somePermissions.openDocument()
    println(somePermissionsOrdinal) // prints 0
  }



}
