// Fair play Tom, already up to speed with these absolute basics thanks

package lectures.part1basics

object ValuesVariablesTypes extends App {

  // VALUES
  val x: Int = 42
  println(x)

  // x = 5 // <-- not allowed
  // vals are immutable, but think of them as intermediate values rather than consts

  // Types in val declarations are optional
  // The compiler can infer types:
  val y = 14
  println(y)

  // If you do specify a type, you'll need to stick with it or the compiler will complain

  // Semicolons are allowed but not needed, although you can use them to write multiple
  // expressions on one line (generally bad style):
  val aString: String = "hello!"; val anotherString = "... world"

  // And here are some types
  val bBool: Boolean = false
  val ch: Char = 'a'
  val shorty: Short = 1304
  val longo: Long = 238923404L  // <-- Denote longs with a trailing L
  val floaty: Float = 2.0f      // <-- Floats need the f else they'll be treated as doubles
  val dub: Double = 2.123

  // VARIABLES - can be reassigned:
  var aVariable: Int = 4
  aVariable = 5 // Side effects - we'll be mindful of side effects!

}
