package Com.Advanced.Implicits.ImplicitRedesign

object ImplicitRedesignImplicitConversionExtensionMethod extends App {
  import obj._
  val aMeter: Meter = Meter(20)
  println(aMeter.meterToCentimeter)
}

case class Meter(value: Double) extends AnyVal

object obj {
  //instead of implicit def meterToCentimeter(meter: Meter):Double = meter.value * 100
  extension (meter: Meter) def meterToCentimeter: Double = meter.value * 100
}

