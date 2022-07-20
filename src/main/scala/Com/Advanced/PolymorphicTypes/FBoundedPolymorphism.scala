package Com.Advanced.PolymorphicTypes

object FBoundedPolymorphism extends App{
  /*what if you want to design generic trait that have a concrete subclass that you want
to access it ??
like having a generic trait wanted to be compared to particular subclass of that trait*/
  //to do so we use parameterized trait
  //trait MyCollection[A] extends Ordered[A]//trait for data have single natural ordering
  // can you guess what problem we have here ??
  //the problem is that the type A is not bounded by anything,
  // and you could potentially do something like this
  //  def compare(that: String): Int
  //to reconcile this, we instead use F-bounded polymorphism.
  trait MYCollection[A <: MYCollection[A]] extends Ordered[A]
  //the subclass can now represented as the following

  class MyCollectionClass extends MYCollection[MyCollectionClass]{
    // override the compare method of ordered trait
    override def compare(that: MyCollectionClass): Int = 0
  }

  println(List(new MyCollectionClass,new MyCollectionClass,new MyCollectionClass))
  println(List(new MyCollectionClass,new MyCollectionClass,new MyCollectionClass).min)
  //prints the data below
  /*List(Com.Advanced.PolymorphicTypes.FBoundedPolymorphism$MyCollectionClass@57829d67, Com.Advanced.PolymorphicTypes.FBoundedPolymorphism$MyCollectionClass@19dfb72a, Com.Advanced.PolymorphicTypes.FBoundedPolymorphism$MyCollectionClass@17c68925)
Com.Advanced.PolymorphicTypes.FBoundedPolymorphism$MyCollectionClass@6108b2d7*/
  //this will discussed in detailed with more readable data
  // in dependency injection cake pattern future posts
}
