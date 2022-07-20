package Com.Advanced.Implicits.ImplicitRedesign

object ImplicitRedesignUsingPlusGivenKeyword extends App {
  //The given keyword is used to define an instance of implicit value
  //In Scala 3, it’s not required to name a given instance. We can directly assign a value without the name
  given String = "Scala Software Engineer"
  println(attachedJob("Mahmoud Essam"))
  //prints Full Name : Mahmoud Essam Job Title: Scala Software Engineer

}
//The using keyword is used to pass an implicit parameter to a method
def attachedJob(fullName:String)(using jobTitle:String):String = s"Full Name : ${fullName} Job Title: ${jobTitle}"



