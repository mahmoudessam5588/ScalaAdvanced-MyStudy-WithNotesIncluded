package Com.Advanced.Implicits.ImplicitRedesign

object ImplicitRedesignContextualAbstraction {
  val changedBehaviorSortedList: List[Int] = List(2, 3, 7, 9, 5, 1, 10).sorted //contextual argument:Descending Ordering

  //so what is sorted and what is it's signature
  //sorted is override method of covariance trait seq[+A]
  //have that signature
  /*trait Seq[+A]
  extends Iterable[A]
    with PartialFunction[Int, A]
    with SeqOps[A, Seq, Seq[A]]
    with IterableFactoryDefaults[A, Seq]
    with Equals {
      override def sorted[B >: A](implicit ord: Ordering[B]): C = super.sorted(ord)
      }
*/
  //lets analyse the sorted method syntax as you notice it has Implicit Ordering
  //what do you think  this Implicit Ordering is is it an implicit {{val parameter}}
  // or {{{ABSTRACTED TRAIT OF IMPLICIT TYPE ORDERING}}} well it's the later
  // let's analyse more the ordering syntax
  //   type Ordering[T] = scala.math.Ordering[T]
  // if we hover over the right Ordering[T] you will see that ordering is also  a trait
  // {ABSTRACTED TRAIT FROM THE USER} that extends Comparator[T] PartialOrdering[T] with Serializable[T]
  //all these Implemented INFO ARE CONTEXTUAL ABSTRACTED for code easy of usability and readability
  //The Question is can we change the behaviour of sorted ??
  //YES!!!,Absolutely we can by providing the Ordering trait in the local scope of Sorted to be Priority fetched
  // than the provided In The Scala Library Lets see how ?
  //given /*equivalent to Implicit val in scala 3*/ descendingOrdering:Ordering[Int] = Ordering.fromLessThan(_>_)
  //we can even remove the descendingOrdering it will work
  given Ordering[Int] = Ordering.fromLessThan(_ > _) // equivalent to Ordering.fromLessThan((a,b)=> a > b)

  def main(args: Array[String]): Unit = {
    println(changedBehaviorSortedList) //List(10, 9, 7, 5, 3, 2, 1)
    //step 4 calling on the operation
    val foldableSummedList: Int = foldingLeft(changedBehaviorSortedList)
    println(foldableSummedList) //sum of the list + value of the head  == print 47
    //order of the compiler looking for given instances:
    /*
    * 1- Local Scope
    * 2- Imported Scope (yourPackage.given or object.given)
    * 3- the companion object of singleton instances of all types involved in the call
    *       - Like Companion of list Foldable[A] ==> Foldable[Int]
    *       - Like Companion of Sorted Ordering[B] ==> Ordering[Int] */

  }
  // the next question is can we create a new behaviour all together like folding  element of the list
  // and summing them to the value of the head
  // in {{Contextual Abstracted Way}} Also Yes!!! Absolutely we can
  //Follow me Step By Step Below

  //step One Create A Trait (This IS A Monoid)
  trait Foldable[A] {
    def folding(x: A, y: A): A
  }

  //step two create a method that curried the Foldable Trait as an {{{ABSTRACTED TRAIT OF IMPLICIT  Foldable}}}
  //which make a Foldable a contextual argument for better usability of code
  //by just calling foldingList on List of elements (step 4 Above) in main method
  def foldingLeft[A](list: List[A])(using foldable: Foldable[A]): A =
    list.foldLeft(list.head)((a, b) => foldable.folding(a, b))
  // the above step can be short handed must futher and this demonstrate the scala language power



  //Step 3 we must provide given instance for this operation to work by overriding the folding must as anonymous function
  given intFoldable: Foldable[Int] = (x: Int, y: Int) => x + y

}
