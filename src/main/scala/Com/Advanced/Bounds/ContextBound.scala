package Com.Advanced.Bounds

import scala.math.Ordered.orderingToOrdered

object ContextBound extends App {
  val fullName = new FirstNameIfGreater("Mahmoud","Essam")
  println(fullName.greater)

}
/*Scala View Bound DEPRECATED Since Scala 2.8 Syntax Below DEPRECATED
* class Person[T <% Ordered[T]](val firstName: T, val lastName: T) {
  def greater = if (firstName > lastName) firstName else lastName
}
* What The Difference ??
* view bounds and context bounds is that the first transfers implicit conversions from the caller's scope.
* The second transfers implicit objects from the caller's scope.*/
class FirstNameIfGreater[T :  Ordering](val firstName: T, val lastName: T) {
  def greater: T = if (firstName > lastName) firstName else lastName
  //In Scala, Context Bound is used when we want to use existing Implicit Conversions automatically.
}

trait Container[M[_]] { def put[A](x: A): M[A]; def get[A](m: M[A]): A }

