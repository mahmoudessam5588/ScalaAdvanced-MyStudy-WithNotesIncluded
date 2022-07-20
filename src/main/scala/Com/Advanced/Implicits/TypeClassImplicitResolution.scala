package Com.Advanced.Implicits

object TypeClassImplicitResolution extends App {
  //Basically, a type class represents a behavior or a characteristic that a class of a generic type T
  // could have. It’s a concept similar to interfaces,
  // it is represented in Scala using a trait
  trait Searchable[T] {
    def extract(obj: T): String
  }
  //The example above defines a type class, allowing a type T to be searchable — that is, having an associated Extract.
  // Every class that wants to participate in the Searchable type class must implement its abstract method extract.
  // Type classes represent how Scala implements the so-called ad-hoc polymorphism

  def searchWithImplicits[T](obj: T)(implicit searchable: Searchable[T]): String = searchable.extract(obj)

  //We have two types, Library and LibraryPolicy, that we want to make searchable
  case class Library(bookName: String, authorName: String, surname: String)

  case class LibraryPolicy(bookPolicy: String, description: String)

  //The first thing we need to do is to implement the extract method for the above two types.
  // So, let’s implement the Searchable trait using anonymous classes
  implicit val searchableLibrary: Searchable[Library] = (library: Library) => s"BookName = ${library.bookName}"
  implicit val searchablePolicy: Searchable[LibraryPolicy] = (libraryPolicy: LibraryPolicy) => s"BookPolicy = ${libraryPolicy.bookPolicy}"

  //Due to the compiler’s implicit resolution,
  // the searchWithImplicit method’s behavior will be polymorphic
  // because it will change according to the resolved instance of the type class.
  val bookByName = Library("The Mysterious Island", "Jules Verne", "Jules")
  println(searchWithImplicits(bookByName))

}
