package Com.Advanced.Variances

object ScalaContravariance extends App{
  val upperTier = new UpperTier()
  val lowerTier = new LowerTier()
  val theHandler = new TierHandler
   theHandler.tierHandler(upperTier)
   theHandler.tierHandler(lowerTier)

}

  abstract class TopTier [-T]{
    def tierHierarchy() : Unit
  }

  class UpperTier extends TopTier[AnyVal]{
    override def tierHierarchy(): Unit = {
      println("UpperTier")
    }
  }
  class LowerTier extends TopTier[Int]{
    override def tierHierarchy(): Unit = {
      println("LowerTier")
    }
  }

  class TierHandler{
    def tierHandler(t: TopTier[Int]): Any ={
      t.tierHierarchy()
    }
  }

