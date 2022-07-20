# What is Type Bound in Scala?
In Scala, Type Bounds are restrictions on Type Parameters 
or Type Variable. By using Type Bounds,
we can define the limits of a Type Variable

# Scala Type Bounds

Scala supports the following Type Bounds for Type Variables:
- Scala Upper Bounds
- Scala Lower Bounds
- Scala View Bounds

# Scala Upper Bound Syntax:

![Upper Bound](https://www.journaldev.com/wp-content/uploads/2015/11/scala_upper_bound.png)

Here T is a Type Parameter ans S is a type. By declaring Upper Bound like “[T <: S]” means this Type Parameter T 
must be either same as S or Sub-Type of S.

```scala
[T <: Ordered[T]]
``` 
# Scala Lower Bound Syntax:

![Lower Bound](https://www.journaldev.com/wp-content/uploads/2015/11/scala_lower_bound-450x324.png.webp)

Here T is a Type Parameter and S is a type. By declaring Lower Bound like “[T >: S]” means this Type Parameter T
must be either same as S or Super-Type of S.

```scala
[T >: Ordered[T]]
```

# Scala Context Bound :

In Scala, Context Bound is used when we want to use existing Implicit Conversions automatically.

