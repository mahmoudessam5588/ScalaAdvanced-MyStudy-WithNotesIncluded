package Com.Advanced.Implicits

object ScalaImplicitParameters extends App {
  implicit val firstName: FirstName = FirstName("Mahmoud")
  implicit val lastName: LastName = LastName("Essam")
  //When Scala matches the implicit values, the parameter and variable names are ignored.
  // Scala will match implicit value by type.
  //best practise to use only one implicit parameter of the same type (firstName or LastName)
  //In  case, we have 2 implicit parameters both by type firstName
  //Scala will match the value Mahmoud in each of the FirstName parameter list resulting in duplicate Mahmoud Entry
  //However,if we Explicitly define 2 different implicit FirstName value
  //This will result in an error message of ambiguous implicit values.
  println(goalToAchieve("Scala Programming Language"))
  //prints ==> My Name is Mahmoud Essam ,
  // I Set Goal To My Self To Secure A Job In Software Industry By Learning Scala Programming Language
  /*
  * However we can pass in  Implicit values in non implicit way like the commented code below
  *implicit val firstName:FirstName = FirstName("Mahmoud")
  implicit val firstName2:FirstName = FirstName("Mohammed")
  implicit val lastName: LastName = LastName("Essam")
  *println(goalToAchieve("Scala Programming Language")(firstName,firstName2,LastName))*/

}

case class FirstName(name: String)

case class LastName(name: String)

def goalToAchieve(text: String)(implicit firstName: FirstName, lastName: LastName): String =
  s"""My Name is ${firstName.name} ${lastName.name} ,
     | I Set Goal To My Self To Secure A Job In Software Industry By Learning $text""".stripMargin
