# Variances
**Variances are equivalent to generics In Java where variance tells us if a type of constructor
is a subtype of another type of constructor**

**Three main types of variance – invariance, covariance, and contravariance**

![generic](https://www.journaldev.com/wp-content/uploads/2015/11/parameterized_type.png)

## Advantage of Variance in Scala
- Variance makes Scala collections more Type-Safe.
- Variance gives more flexible development.
- Scala Variance gives us a technique to develop Reliable Applications.

## Covariant

![covariance](https://www.journaldev.com/wp-content/uploads/2015/11/scala-covariant.png)

**If “S” is subtype of “T” then List[S]  is a subtype of List[T].
This kind of Inheritance Relationship between two Parameterized Types is known as “Covariant”
As If Moving Up Widening The Type**
### Covariant Syntax
**Scala Covariance Syntax:-
To represent Covariance relationship between two Parameterized Types, Scala uses the following syntax:
Prefixing Type Parameter with “+” symbol defines Covariance in Scala.**

![covariance Syntax](https://www.journaldev.com/wp-content/uploads/2015/11/scala-covariance-syntax.png)

## Contravariant
**Inverted Covariant Moving Down The Hierarchy**

![contravariance](https://mermaid.ink/img/pako:eNo1jjEOwyAMRa-CPCcXYKhEmm7dkq1ksILboBaIiBmqKHcviNST__OT9XeYgyGQ8Iq4LmLstRd51ONuNxbjJNr20tUwTPXWFSauFaoTqgpLgAYcRYfW5Ld7IRp4IUcaZF4NxrcG7Y_spdUg081YDhHkEz8bNYCJw_D1M0iOif5SbzFXdKd1_ABzeTks)

### Contravariance Syntax
![Contravariance Syntax](https://www.journaldev.com/wp-content/uploads/2015/11/scala-contravariance-syntax.png)

## Invariant 
In Scala
If “S” is subtype of “T” then List[S] and List[T] don’t have Inheritance Relationship or Sub-Typing. That means both are unrelated.

This kind of Relationship between two Parameterized Types is known as “Invariant or Non-Variant”

In Scala, by default Generic Types have Non-Variant relationship. If we define Parameterized Types without using “+’ or “-” symbols, then they are known as Invariants.
