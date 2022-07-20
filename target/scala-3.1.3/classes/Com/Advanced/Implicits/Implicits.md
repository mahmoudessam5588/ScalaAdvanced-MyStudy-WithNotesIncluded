# Implicit Classes
**give us the ability to add additional methods to existing classes.**

Implicit Classes Limitation:
- They cannot be defined as top-level objects
- They cannot take multiple non-implicit arguments in their constructor
- We cannot use implicit classes with case classes
- There cannot be any member or object in scope with the same
name as the implicit class
# Implicit Conversion

**Implicit conversions give the Scala compiler the ability to convert one type into another.**

Implicit conversions have some limitations:

- They cannot take multiple non-implicit arguments
- They cannot chain multiple implicit conversions


Martin Odersky, the inventor of Scala, has indicated that implicits (and implicit conversions) are being deprecated in scala 3.1 and will eventually be removed from the language altogether.

the implicit functionality will be replaced with Extension Methods and Givens.
Extension Methods and Givens provide a tighter functional solution that doesn't introduce the unsuspecting and hidden side effects that implicits cause.
Odersky now views implicits as a “recipe for disaster" and are "too implicit" which was his motivation to replace their functionality in 3.x.

# Implicit Parameters

Implicit parameters are similar to regular method parameters, except they could be passed to a method silently without going through the regular parameters list.

A method can define a list of implicit parameters, that is placed after the list of regular parameters. These parameters are indicated using the implicit keyword, and all parameters after the implicit keyword are implicit:
```scala
def draw(text: String)(implicit color: Color, by: DrawingDevice)
```
When a method is defined with implicit parameters, Scala will look up implicit values in the scope by matching the type if they are not already passed in the implicit parameter list.

# Implicitly

implicitly works as a “compiler for implicits”. We can verify if there is an implicit value of type T. If no implicit value of type T is available in the scope, the compiler will warn us

# Type Class Implicit Resolution

Basically, a type class represents a behavior or a characteristic that a class of a generic type T could have. It’s a concept similar to interfaces, and in fact, it is represented in Scala using a trait:

```scala
trait Searchable[T] {
  def uri(obj: T): String
}
```
The example above defines a type class, allowing a type T to be searchable — that is, having an associated URI. Every class that wants to participate in the Searchable type class must implement its abstract method uri.
Type classes represent how Scala  implements the so-called ad-hoc polymorphism

# Scala 3 Implicit Redesign

The major drawbacks with implicit in Scala 2 are:

- Usage of the keyword implicit for unrelated functionalities
- Unintended conversions due to implicit conversions
- Unclear compiler error messages
- Due to these, even experienced developers sometimes find it quite difficult to debug some of the issues related to implicit. As a result, in Scala 3, one of the major focuses was to redesign the existing implicit functionality.
    ## New Keywords
    ### 1- given

    The given keyword is used to define an instance of implicit value
    ```scala
    given timeout: Int = 10 
    ```
    ### 2- Using
  The using keyword is used to pass an implicit parameter to a method
    ```scala
    def execute(url:String)(using timeout: Int):String = "{}"
    ```
  We can also explicitly provide the implicit value as
    ```scala
  execute("http://www.baeldung.com")(using 4)
    ```
  
  ### 3-  Summon an implicit Value From Scope
  In Scala 2, we can use the `implicitly` method to summon an available implicit value from the scope. For example, to get an execution context from the scope, we can write
  ```scala
  val ctx = implicitly[ExecutionContext]
  ```
  In Scala 3, this method is removed and is replaced with summon
  ```scala
  val ctx = summon[ExecutionContext]
  ```
  ### 4- Extension Methods
  In Scala 2, we implement the extension method using the implicit keyword. For instance, if we want to easily convert an Int value to the Second case class, we can use an implicit class
  ```scala
  object Extension {
    implicit class IntExtension(value: Int) {
      def toSecond() = Second(value)
     }
  }
  ```
  Now, we can import the implicit class and use the toSecond() method as if it is defined on the Int class
  ```scala
  import Extension._
  val second: Second = 100.toSecond()
  ```
  Scala 3 introduced the extension keyword to implement an extension method
  ```scala
  object Extension {
    extension(sec: Int) def toSecond() = Second(sec)
    }
  ```
  We can import and use the extension method in the same way as in Scala 2
  ```scala
  import Extension._
  val sec: Second = 10.toSecond()
  ```  

