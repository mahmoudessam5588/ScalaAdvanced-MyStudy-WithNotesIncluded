package Com.Advanced.Implicits

object TypeClassImplicitlyContextBound extends App {

  trait Searchable[T] {
    def extract(obj: T): String
  }

  def searchWithContextBound[T: Searchable](obj: T): String = {
    // implicitly go fetch type implicit searchable type t
    val searchable = implicitly[Searchable[T]]
    searchable.extract(obj)
  }

  case class Library(bookName: String, authorName: String, surname: String)
  //fetched type here
  implicit val searchableLibrary: Searchable[Library] =
    (library: Library) => s"BookName = ${library.bookName}"



  val bookName: Library = Library("The First Men In The Moon", "H.G.Wells", "H.G.Wells")
  val extract: String = searchWithContextBound(bookName) //fetched result attached to val extract

  println(extract) //printed fetched result
  println(extract equals "BookName = The First Men In The Moon")  //ensure equality of result



}
