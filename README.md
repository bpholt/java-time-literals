# `java.time` Literals

Parse Scala string literals into `java.time` instances at compile time.

```scala
> import dev.holt.javatime.literals._
> duration"PT20.345S"
val res0: java.time.Duration = PT20.345S
```

See the [test suite for comprehensive examples](https://github.com/bpholt/java-time-literals/blob/main/core/shared/src/test/scala/dev/holt/javatime/JavaTimeSuite.scala)
of all supported literal types.

## Scala.js and Scala Native

Since Scala.js and Scala Native don't have a built-in timezone database, if you need to use
timezones other than UTC on those platforms, make sure to include

```scala
"io.github.cquiroz" %%% "scala-java-time-tzdb" % "2.5.0"
```

in your project dependencies.
