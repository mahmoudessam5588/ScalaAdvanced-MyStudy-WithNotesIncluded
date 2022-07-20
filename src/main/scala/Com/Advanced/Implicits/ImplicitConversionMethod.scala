package Com.Advanced.Implicits

import scala.language.implicitConversions


object ImplicitConversionMethod extends App {

  // 1 Hectare = 2.47 Acres
  case class Acre(value: Double) extends AnyVal

  case class Hectare(value: Double) extends AnyVal

  implicit def hectareToAcre(hectare: Hectare): Acre = Acre(hectare.value * 2.47)
  //Scala compiler knows that Hectare is not a subtype Acre.
  // However, instead of a compilation error,
  // it checks if there is an implicit conversion available that converts Hectare into Acre.
  //Therefore we have provided an implicit hectareToAcre method,
  // we are able to assign the Hectare(1) to variable Acre of type Acre.
  //compiler will look below for implicit conversion of method have the same signature
  val acre: Acre = Hectare(1)

  println(acre equals  Acre(2.47))//prints true
}




