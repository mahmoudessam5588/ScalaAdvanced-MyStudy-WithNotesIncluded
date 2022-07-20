package Com.Advanced.Concurrency

import Com.Advanced.Concurrency.MimicApiCallExample.fetchUserProfileByID
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
object FutureFaultToleranceHandling extends App {
  //fault tolerance Handling methods
  //1)recover in case of wrong && || Invalid data to prevent throw exception
  val InvalidData: Future[Any] = fetchUserProfileByID("Whatever").recover {
    //Throwable Partial Function With Signature
    //def recover[U >: T](pf: PartialFunction[Throwable, U])(implicit executor: ExecutionContext): Future[U] =
    //    transform { _ recover pf }
    //Creates a new future that will handle any matching throwable
    // that this future might contain by assigning it a value of another future.
    //If there is no match, or if this future contains a valid result
    // then the new future will contain the same result.
    case e: Throwable => println("Wrong Data Input " + e.printStackTrace())
  }
  //2)recover with
  val handlingFailureInputData: Future[MimicApiCallExample.UserProfiles] =
    fetchUserProfileByID("Blaaaa").recoverWith {
      //signature
      /* def recoverWith[U >: T](pf: PartialFunction[Throwable, Future[U]])(implicit executor: ExecutionContext): Future[U] =
      transformWith {
        t =>
          if (t.isInstanceOf[Failure[T]]) {
            val result = pf.applyOrElse(t.asInstanceOf[Failure[T]].exception, Future.recoverWithFailed)
            if (result ne Future.recoverWithFailedMarker) result
            else this
          } else this
      }*/
      //Zips the values and creates a new future holding the tuple of their results.
      //If either input future fails, the resulting future is failed with the same throwable,
      // without waiting for the other input future to complete
      //in case failure fetch other userprofile either default one or present on the userprofiles database
      case e: Throwable => fetchUserProfileByID("User000_Default" + e.printStackTrace())
    }
  val fallbackHandling = fetchUserProfileByID("blablabla")
    .fallbackTo(fetchUserProfileByID("User000_Default"))
  /*Creates a new Future[S] which is completed with this Future's result if that conforms to S's erased type or a ClassCastException otherwise.
Params:
tag – the ClassTag which will be used to cast the result of this Future
Type parameters:
S – the type of the returned Future
Returns:
a Future holding the casted result of this Future or a ClassCastException otherwise*/
}
