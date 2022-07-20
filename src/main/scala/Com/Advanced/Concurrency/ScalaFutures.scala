package Com.Advanced.Concurrency

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

object ScalaFutures extends App {
  def evenNumbers(n:LazyList[Int]):LazyList[Int]={
    Thread.sleep(3000)//simulate long computation
    n.filter(_%2 == 0)
  }
  //What Is Future??
  //Future represents a result of an asynchronous computation that may or may not be available yet.
  //When we create a new Future, Scala spawns a new thread and executes its code.
  // Once the execution is finished,
  // the result of the computation (value or exception) will be assigned to the Future.
  //if we look at future signature we notice that it has implicit def ExecutionContext
  //that needed to be imported for future  to work
  //Before we create any Future, we need to provide an implicit ExecutionContext.
  // This specifies how and on which thread pool the Future code will execute.
  // We can create it from Executor or ExecutorService:
  //What is ExecutionContext??
  //. This default ExecutionContext implementation is backed by a work-stealing thread pool.
  // It can be configured via the following system properties:
  //scala.concurrent.context.minThreads = defaults to "1"
  //scala.concurrent.context.numThreads =
  //            defaults to "x1" (i.e. the current number of available processors * 1)
  //scala.concurrent.context.maxThreads =
  //           defaults to "x1" (i.e. the current number of available processors * 1)
  //scala.concurrent.context.maxExtraThreads = defaults to "256"
  //The pool size of threads
  // is then numThreads bounded by minThreads on the lower end and maxThreads on the high end.
  //The global execution context can be used explicitly, by defining an implicit val
  // ec: scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global,
  // or by importing ExecutionContext.Implicits.global.
  val futureInstance = Future{
    //When we call Future on evenNumbersMethod
    //the Future runtime executes it on another thread.
    // It might look like we were passing the result of invoking the method to the Future,
    // however, Future.apply takes it as a by-name parameter.
    // It moves evaluation into a thread provided by implicit ExecutionContext.
    evenNumbers(LazyList(55,67,88,12,56,34,66,89,102))
  }
  //When we have an already computed value,
  // there’s no need to start an asynchronous computation in order to get a Future.
  // We can create a successfully completed Future
  // by using onComplete
  // Returns whether the future had already been completed with a value or an exception.
  //Returns:
  //true if the future was completed, false otherwise
  //here's onComplete signature
  //def onComplete[U](f: Try[T] => U)(implicit executor: ExecutionContext): Unit
  //so let's pattern match it using partial function
  println("Computing ......")
  futureInstance.onComplete{
    case Success(evenNumbers) => s"List= ${evenNumbers.foreach(println)}"
    case Failure(e) => println(s"failed computed operation ${e.printStackTrace()}")
  }
  //on complete draw backs:
  //it returns unit which has side effects
  //it a call back function by executing SOME Thread we can't
  //make any assumption on what thread execute this partial function
  Thread.sleep(4000)
  /*Console Output:
  Computing ......
  88
  12
  56
  34
  66
  102
*/
}
