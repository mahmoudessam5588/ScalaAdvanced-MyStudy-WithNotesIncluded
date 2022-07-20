package Com.Advanced.Implicits

import scala.annotation.tailrec

object ScalaImplicitly extends App {
  implicit val m: BigInt = 37500
  //Second Implicitly:
  // Visualize Implicitly with Dog Fetch Ball we assign BigInt To Implicitly
  //then ask the scala compiler to go fetch an implicit value of type BigInt
  // Implicitly have to be in the same scope of implicit val m
  def FexVersionTwo(a: BigInt): BigInt = {
    val m = implicitly[BigInt]
    m * a
  }
  println(Fex(400))

  println(FexVersionTwo(500))
}

//We need to calculate The Excessive Thrust needed for F16 Fighter jet to reach certain speed
// Fex = excessive thrust equals  Thrust - Drag (Fex = T-D)
//According to Second law of newton Fex = m * a
//where  m = mass and a = acceleration
//m = will be constant knowing the weight of F16 fighter jet equals 37500 kg in the air
//we will only need a function to calculate different acceleration to a given constant (m)
// def Fex(m:BigInt,a:BigInt):BigInt = m * a
//by following Solid Principles without violation of DRY AND KISS principles
//we will have two options either using implicit parameter or using implicitly

//First Implicit Parameter
def Fex(a: BigInt)(implicit m: BigInt): BigInt = m * a


