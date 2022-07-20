package Com.Advanced.Implicits.ImplicitRedesign

object ImplicitRedesignImplicitConversion extends App {
  import ImplicitConversion.given
  val acre: Acre = Hectare(1)
  println(acre equals Acre(2.47)) //prints true
}

case class Acre(value: Double) extends AnyVal

case class Hectare(value: Double) extends AnyVal

object ImplicitConversion {
  //Instead of scala 2 syntax
  // implicit def hectareToAcre(hectare: Hectare): Acre = Acre(hectare.value * 2.47)
  //use given Conversion[] with apply  like this Not The Prettiest In My Opinion
  given Conversion[Hectare, Acre] with {
    def apply(hectare: Hectare): Acre = Acre(hectare.value * 2.47)
  }
}


/*object ImplicitRedesignShortHandImplicitConversion extends App {
  import scala.language.implicitConversions
  // a method that expects an Int
  def plus1(i: Int): Int = i + 1
  // pass it a String that converts to an Int
  println(plus1("1")) //prints 2

}
//use can short hand this syntax with alias in predefined package or conversion here's an example
//instead of this long commented syntax below
/*given Conversion[String, Int] with
def apply(s: String): Int = Integer.parseInt(s)*/

//Using an alias this can be expressed more concisely as
given Conversion[String, Int] = Integer.parseInt(_)
*/
