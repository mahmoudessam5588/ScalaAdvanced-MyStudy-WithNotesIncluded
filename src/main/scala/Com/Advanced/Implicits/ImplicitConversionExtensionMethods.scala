package Com.Advanced.Implicits

import scala.language.implicitConversions

object ImplicitConversionExtensionMethods extends App {

  case class Centimeters(value: Double) extends AnyVal
  case class Meters(value: Double) extends AnyVal
  case class Kilometers(value: Double) extends AnyVal

  class LengthSyntax(length: Double) {
    def centimeters: Centimeters = Centimeters(length)
    def meters: Meters = Meters(length)
    def kilometers: Kilometers = Kilometers(length)
  }

  implicit def double2richSyntax(value: Double): LengthSyntax =
    new LengthSyntax(value)

  val length: Double = 2.5

  /*In this case, the compiler is looking for an implicit conversion
  that converts Double into anything that has centimeters,meters, or kilometers methods.
   Therefore we provided an implicit double2richSyntax method,
    and the compiler knows what to do.*/

  println(length.centimeters equals Centimeters(length))
  println(length.meters equals Meters(length))
  println(length.kilometers equals Kilometers(length))

}
