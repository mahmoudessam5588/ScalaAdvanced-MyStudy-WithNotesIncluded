package Com.Advanced.Concurrency


import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Random, Success}
import scala.concurrent.duration.*
import scala.language.postfixOps
object MimicApiCallExample extends App {
  //Profiles data info
  case class UserProfiles(id: String, name: String) {
    def like(anotherProfile: UserProfiles): Unit = {
      println(s"${this.name} likes ${anotherProfile.name} post ")
    }
  }

  //added transaction
  case class Transaction(sender: String, receiver: String, amount: Double, status: String)

  // Users database
  object UsersDataBase {
    val names: Map[String, String] = Map(
      "user_1.Ahmed" -> "Ahmed Yasser",
      "user_22.Omar" -> "Omar Mahmoud",
      "User_212.Yasmin" -> "Yasmin Ibrahim"
    )

    val mutualFriends: Map[String, String] = Map(
      "user_1.Ahmed" -> "User_212.Yasmin",
      "user_22.Omar" -> "user_1.Ahmed",
      "User_212.Yasmin" -> "user_1.Ahmed"
    )
    val random: Random = new Random()
  }

  import Com.Advanced.Concurrency.MimicApiCallExample.UsersDataBase._

  //API Calls
  def fetchUserProfileByID(id: String): Future[UserProfiles] = Future {
    Thread.sleep(random.nextInt(300))
    UserProfiles(id, names(id))
  }

  def fetchUserProfileByMutualFriends(profile: UserProfiles): Future[UserProfiles] = Future {
    Thread.sleep(random.nextInt(300))
    val idKeys = mutualFriends(profile.id)
    UserProfiles(idKeys, names(idKeys))
  }
  //mutual users like  Activity
  for {
    userOne <- fetchUserProfileByID("user_1.Ahmed")
    otherUserRelated <- fetchUserProfileByMutualFriends(userOne)
  } yield userOne.like(otherUserRelated)
  Thread.sleep(3000) // prints Ahmed Yasser likes Yasmin Ibrahim post

  //Now lets extend the functionality of this example by adding purchase of plans for premium users to unlock additional
  //functionality in this given App
  //over goal in this example to block the future to wait for purchase and transaction to be Successful before
  //provide the premium functionality for the User
  object purchasePlans {
    def fetchUser(userId: String, name: String): Future[UserProfiles] = Future {
      //simulate Fetching From Database
      Thread.sleep(500)
      UserProfiles(userId, name)
    }

    def createTransaction(user: UserProfiles, purchaseProvider: String, purchaseAmount: Double): Future[Transaction] = Future {
      //simulate transaction processing
      Thread.sleep(1000)
      Transaction(user.name, purchaseProvider, purchaseAmount, "Success")
    }

    def purchase(userId: String, username: String, purchasePlan: String, purchaseProvider: String, cost: Double): String /*status transaction*/ = {
      //we need several steps here to complete the purchase
      //1)fetch the user from DB
      //2)create a Transaction
      //3)Curial Part wait for transaction to finish
      val transactionStatus = for {
        user <- fetchUser(userId, username)
        transaction <- createTransaction(user, purchaseProvider, cost)
      } yield transaction.status
      //block on Transaction Status With
      Await.result(transactionStatus, 2 seconds) // implicit conversion + postfix notation ==> pimp my library
      /*Await the "completed" state of an Awaitable.
      Although this method is blocking, the internal use of blocking ensures that the underlying ExecutionContext
      is given an opportunity to properly manage the blocking.
      WARNING: It is strongly discouraged to supply lengthy timeouts
      since the progress of the calling thread will be suspended—blocked—until
      either the Awaitable becomes ready or the timeout expires*/
    }

  }

  import purchasePlans.*

  println(purchase("1010", "MahmoudEsam", "MonthlyPlan", "LinkedIn :(", 560))
  //prints Success
  //as you noticed we didn't need any sleeping threads for operation to be printed out
  //also if you noticed in futures we eliminate the use of vars we are only dealing with val of immutable
  //date and ready only immutable state so the question is what if we need to change or manipulate the Future
  //Here Enter The Concept of promises I mean at some time we need to complete of set a future
  // at a point of our choosing that why we need a promises
  //that's we well discuss in the upcoming posts Stay Tuned
  
}
