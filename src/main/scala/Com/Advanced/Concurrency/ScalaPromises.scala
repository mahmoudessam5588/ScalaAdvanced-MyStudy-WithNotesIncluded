package Com.Advanced.Concurrency

import java.lang.Thread
import scala.concurrent.Promise
import scala.util.Success
import scala.concurrent.ExecutionContext.Implicits.global

object ScalaPromises extends App {

  val futureToBeFulfilled = Promise[Int]()
  val promisedForBetterFuture = futureToBeFulfilled.future
  promisedForBetterFuture.onComplete {
    case Success(value) =>
      println(s"Achieving Goal at $value")
  }
  val supporting = new Thread(() => {
    Thread.sleep(700)
    futureToBeFulfilled.success(34)
    println("Finished His Studying ")
  })
  supporting.start()
  Thread.sleep(4000)
}