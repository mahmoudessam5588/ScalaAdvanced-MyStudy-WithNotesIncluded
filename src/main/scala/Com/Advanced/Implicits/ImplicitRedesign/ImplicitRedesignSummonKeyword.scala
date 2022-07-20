package Com.Advanced.Implicits.ImplicitRedesign

object ImplicitRedesignSummonKeyword extends App {
  def ExcessThrustFighterJet(acceleration: BigInt): BigInt = {
    // summon keyword instead of implicitly
    val massConstant = summon[BigInt]
    massConstant * acceleration
  }
  //given replace implicit val massConstant:BigInt = 37500
  given BigInt = 37500
  println(ExcessThrustFighterJet(200)) // prints 7500000


}
