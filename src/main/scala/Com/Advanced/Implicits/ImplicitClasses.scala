package Com.Advanced.Implicits
object ImplicitClasses extends App{
  import Population.*
  //First, we need to make the implicit class Population available in scope by using import Population.*
  //Second The compiler knows that there is no amount.Alexandria method available on BigInt,
  //Third so it tries to find any available implicit class that adds the amount.Alexandria method to BigInt. Thanks to the import,
  // Class PopulationByProvinces is available in the scope and makes the compiler happy.
  val amount:BigInt = 5000000
  println(amount.Alexandria  equals   Population(5000000,EgyptProvinces.Alexandria)) // print true
}
sealed trait EgyptProvinces
object EgyptProvinces{
  case object Alexandria extends EgyptProvinces
  case object Cairo extends EgyptProvinces
  case object Mounifya extends EgyptProvinces
  case object Ismaylia extends EgyptProvinces
}
case class Population (amount:BigInt,egyptProvinces:EgyptProvinces)
object Population{
  implicit class PopulationByProvinces(val amount:BigInt) extends AnyVal{
    //extends AnyVal to avoid runtime allocation.
    def Alexandria:Population = Population(amount,EgyptProvinces.Alexandria)
    def Cairo:Population = Population(amount, EgyptProvinces.Cairo)
  }
}

