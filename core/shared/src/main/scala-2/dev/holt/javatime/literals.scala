package dev.holt.javatime

import org.typelevel.literally.Literally

import java.time._
import scala.reflect.macros.blackbox
import scala.util.{Failure, Success, Try}

//noinspection ScalaUnusedSymbol
object literals {
  implicit class duration(val sc: StringContext) extends AnyVal {
    def duration(args: Any*): Duration = macro DurationLiteral.make
  }

  object DurationLiteral extends Literally[Duration] {
    def make(c: blackbox.Context)
            (args: c.Expr[Any]*): c.Expr[Duration] = apply(c)(args: _*)

    override def validate(c: DurationLiteral.Context)
                         (s: String): Either[String, c.Expr[Duration]] = {
      import c.universe.{Try => _, _}

      Try(Duration.parse(s)) match {
        case Failure(ex) => Left(ex.getMessage)
        case Success(_) => Right(c.Expr(q"java.time.Duration.parse($s)"))
      }
    }
  }

  implicit class instant(val sc: StringContext) extends AnyVal {
    def instant(args: Any*): Instant = macro InstantLiteral.make
  }

  object InstantLiteral extends Literally[Instant] {
    def make(c: blackbox.Context)
            (args: c.Expr[Any]*): c.Expr[Instant] = apply(c)(args: _*)

    override def validate(c: InstantLiteral.Context)
                         (s: String): Either[String, c.Expr[Instant]] = {
      import c.universe.{Try => _, _}

      Try(Instant.parse(s)) match {
        case Failure(ex) => Left(ex.getMessage)
        case Success(_) => Right(c.Expr(q"java.time.Instant.parse($s)"))
      }
    }
  }

  implicit class localDate(val sc: StringContext) extends AnyVal {
    def localDate(args: Any*): LocalDate = macro LocalDateLiteral.make
  }

  object LocalDateLiteral extends Literally[LocalDate] {
    def make(c: blackbox.Context)
            (args: c.Expr[Any]*): c.Expr[LocalDate] = apply(c)(args: _*)

    override def validate(c: LocalDateLiteral.Context)
                         (s: String): Either[String, c.Expr[LocalDate]] = {
      import c.universe.{Try => _, _}

      Try(LocalDate.parse(s)) match {
        case Failure(ex) => Left(ex.getMessage)
        case Success(_) => Right(c.Expr(q"java.time.LocalDate.parse($s)"))
      }
    }
  }

  implicit class localDateTime(val sc: StringContext) extends AnyVal {
    def localDateTime(args: Any*): LocalDateTime = macro LocalDateTimeLiteral.make
  }

  object LocalDateTimeLiteral extends Literally[LocalDateTime] {
    def make(c: blackbox.Context)
            (args: c.Expr[Any]*): c.Expr[LocalDateTime] = apply(c)(args: _*)

    override def validate(c: LocalDateTimeLiteral.Context)
                         (s: String): Either[String, c.Expr[LocalDateTime]] = {
      import c.universe.{Try => _, _}

      Try(LocalDateTime.parse(s)) match {
        case Failure(ex) => Left(ex.getMessage)
        case Success(_) => Right(c.Expr(q"java.time.LocalDateTime.parse($s)"))
      }
    }
  }

  implicit class localTime(val sc: StringContext) extends AnyVal {
    def localTime(args: Any*): LocalTime = macro LocalTimeLiteral.make
  }

  object LocalTimeLiteral extends Literally[LocalTime] {
    def make(c: blackbox.Context)
            (args: c.Expr[Any]*): c.Expr[LocalTime] = apply(c)(args: _*)

    override def validate(c: LocalTimeLiteral.Context)
                         (s: String): Either[String, c.Expr[LocalTime]] = {
      import c.universe.{Try => _, _}

      Try(LocalTime.parse(s)) match {
        case Failure(ex) => Left(ex.getMessage)
        case Success(_) => Right(c.Expr(q"java.time.LocalTime.parse($s)"))
      }
    }
  }

  implicit class monthDay(val sc: StringContext) extends AnyVal {
    def monthDay(args: Any*): MonthDay = macro MonthDayLiteral.make
  }

  object MonthDayLiteral extends Literally[MonthDay] {
    def make(c: blackbox.Context)
            (args: c.Expr[Any]*): c.Expr[MonthDay] = apply(c)(args: _*)

    override def validate(c: MonthDayLiteral.Context)
                         (s: String): Either[String, c.Expr[MonthDay]] = {
      import c.universe.{Try => _, _}

      Try(MonthDay.parse(s)) match {
        case Failure(ex) => Left(ex.getMessage)
        case Success(_) => Right(c.Expr(q"java.time.MonthDay.parse($s)"))
      }
    }
  }

  implicit class offsetDateTime(val sc: StringContext) extends AnyVal {
    def offsetDateTime(args: Any*): OffsetDateTime = macro OffsetDateTimeLiteral.make
  }

  object OffsetDateTimeLiteral extends Literally[OffsetDateTime] {
    def make(c: blackbox.Context)
            (args: c.Expr[Any]*): c.Expr[OffsetDateTime] = apply(c)(args: _*)

    override def validate(c: OffsetDateTimeLiteral.Context)
                         (s: String): Either[String, c.Expr[OffsetDateTime]] = {
      import c.universe.{Try => _, _}

      Try(OffsetDateTime.parse(s)) match {
        case Failure(ex) => Left(ex.getMessage)
        case Success(_) => Right(c.Expr(q"java.time.OffsetDateTime.parse($s)"))
      }
    }
  }

  implicit class offsetTime(val sc: StringContext) extends AnyVal {
    def offsetTime(args: Any*): OffsetTime = macro OffsetTimeLiteral.make
  }

  object OffsetTimeLiteral extends Literally[OffsetTime] {
    def make(c: blackbox.Context)
            (args: c.Expr[Any]*): c.Expr[OffsetTime] = apply(c)(args: _*)

    override def validate(c: OffsetTimeLiteral.Context)
                         (s: String): Either[String, c.Expr[OffsetTime]] = {
      import c.universe.{Try => _, _}

      Try(OffsetTime.parse(s)) match {
        case Failure(ex) => Left(ex.getMessage)
        case Success(_) => Right(c.Expr(q"java.time.OffsetTime.parse($s)"))
      }
    }
  }

  implicit class period(val sc: StringContext) extends AnyVal {
    def period(args: Any*): Period = macro PeriodLiteral.make
  }

  object PeriodLiteral extends Literally[Period] {
    def make(c: blackbox.Context)
            (args: c.Expr[Any]*): c.Expr[Period] = apply(c)(args: _*)

    override def validate(c: PeriodLiteral.Context)
                         (s: String): Either[String, c.Expr[Period]] = {
      import c.universe.{Try => _, _}

      Try(Period.parse(s)) match {
        case Failure(ex) => Left(ex.getMessage)
        case Success(_) => Right(c.Expr(q"java.time.Period.parse($s)"))
      }
    }
  }

  implicit class year(val sc: StringContext) extends AnyVal {
    def year(args: Any*): Year = macro YearLiteral.make
  }

  object YearLiteral extends Literally[Year] {
    def make(c: blackbox.Context)
            (args: c.Expr[Any]*): c.Expr[Year] = apply(c)(args: _*)

    override def validate(c: YearLiteral.Context)
                         (s: String): Either[String, c.Expr[Year]] = {
      import c.universe.{Try => _, _}

      Try(Year.parse(s)) match {
        case Failure(ex) => Left(ex.getMessage)
        case Success(_) => Right(c.Expr(q"java.time.Year.parse($s)"))
      }
    }
  }

  implicit class yearMonth(val sc: StringContext) extends AnyVal {
    def yearMonth(args: Any*): YearMonth = macro YearMonthLiteral.make
  }

  object YearMonthLiteral extends Literally[YearMonth] {
    def make(c: blackbox.Context)
            (args: c.Expr[Any]*): c.Expr[YearMonth] = apply(c)(args: _*)

    override def validate(c: YearMonthLiteral.Context)
                         (s: String): Either[String, c.Expr[YearMonth]] = {
      import c.universe.{Try => _, _}

      Try(YearMonth.parse(s)) match {
        case Failure(ex) => Left(ex.getMessage)
        case Success(_) => Right(c.Expr(q"java.time.YearMonth.parse($s)"))
      }
    }
  }

  implicit class zonedDateTime(val sc: StringContext) extends AnyVal {
    def zonedDateTime(args: Any*): ZonedDateTime = macro ZonedDateTimeLiteral.make
  }

  object ZonedDateTimeLiteral extends Literally[ZonedDateTime] {
    def make(c: blackbox.Context)
            (args: c.Expr[Any]*): c.Expr[ZonedDateTime] = apply(c)(args: _*)

    override def validate(c: ZonedDateTimeLiteral.Context)
                         (s: String): Either[String, c.Expr[ZonedDateTime]] = {
      import c.universe.{Try => _, _}

      Try(ZonedDateTime.parse(s)) match {
        case Failure(ex) => Left(ex.getMessage)
        case Success(_) => Right(c.Expr(q"java.time.ZonedDateTime.parse($s)"))
      }
    }
  }

  implicit class zoneId(val sc: StringContext) extends AnyVal {
    def zoneId(args: Any*): ZoneId = macro ZoneIdLiteral.make
  }

  object ZoneIdLiteral extends Literally[ZoneId] {
    def make(c: blackbox.Context)
            (args: c.Expr[Any]*): c.Expr[ZoneId] = apply(c)(args: _*)

    override def validate(c: ZoneIdLiteral.Context)
                         (s: String): Either[String, c.Expr[ZoneId]] = {
      import c.universe.{Try => _, _}

      Try(ZoneId.of(s)) match {
        case Failure(ex) => Left(ex.getMessage)
        case Success(_) => Right(c.Expr(q"java.time.ZoneId.of($s)"))
      }
    }
  }

  implicit class zoneOffset(val sc: StringContext) extends AnyVal {
    def zoneOffset(args: Any*): ZoneOffset = macro ZoneOffsetLiteral.make
  }

  object ZoneOffsetLiteral extends Literally[ZoneOffset] {
    def make(c: blackbox.Context)
            (args: c.Expr[Any]*): c.Expr[ZoneOffset] = apply(c)(args: _*)

    override def validate(c: ZoneOffsetLiteral.Context)
                         (s: String): Either[String, c.Expr[ZoneOffset]] = {
      import c.universe.{Try => _, _}

      Try(ZoneOffset.of(s)) match {
        case Failure(ex) => Left(ex.getMessage)
        case Success(_) => Right(c.Expr(q"java.time.ZoneOffset.of($s)"))
      }
    }
  }

}
