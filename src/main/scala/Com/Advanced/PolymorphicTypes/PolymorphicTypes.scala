package Com.Advanced.PolymorphicTypes

object PolymorphicTypes extends App{
  //A ==> Parametric Polymorphism
  trait  SumList[T]{
    //specify a function with a generic parameter
    def summed(x:List[T]):T
  }
  // we instantiate that function or whatever,
  // we specify our current type, which then gets applied to the function.
  val total: SumList[Int] = (x: List[Int]) => x.sum
  println(total.summed(List(1,2,3))) // prints 6

  //B==> Ad-Hoc Polymorphism
  //With ad hoc polymorphism,
  // we can actually change the behavior of the generic code based on the type we polymorph
  trait Foldable[A] {
    def folding(x: A, y: A): A
  }
  //adding new behaviour to normal foldLeft method  to sum the List with the value of head
  def foldingLeft[A](list: List[A])(using foldable: Foldable[A]): A =
    list.foldLeft(list.head)((a, b) => foldable.folding(a, b))

  //providing given instance for above operation to work
  /*given intFoldable : Foldable[Int] = new  Foldable[Int]{
    override def folding(x: Int, y: Int): Int = x + y
  }*/
  //short handed to anonymous  function
  given intFoldable: Foldable[Int] = (x: Int, y: Int) => x + y

  val foldableSummedList: Int = foldingLeft(List(1,2,3))
  println(foldableSummedList) //prints 7
}
