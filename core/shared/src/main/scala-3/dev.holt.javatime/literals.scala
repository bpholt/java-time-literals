package dev.holt.javatime

import org.typelevel.literally.Literally

import java.time._
import java.time.format.DateTimeFormatter
import scala.util.{Failure, Success, Try}

//noinspection ScalaUnusedSymbol
object literals {
  extension (inline ctx: StringContext) {
    inline def duration(args: Any*): Duration = ${DurationLiteral('ctx, 'args)}
    inline def instant(args: Any*): Instant = ${InstantLiteral('ctx, 'args)}
    inline def localDate(args: Any*): LocalDate = ${LocalDateLiteral('ctx, 'args)}
    inline def localDateTime(args: Any*): LocalDateTime = ${LocalDateTimeLiteral('ctx, 'args)}
    inline def localTime(args: Any*): LocalTime = ${LocalTimeLiteral('ctx, 'args)}
    inline def monthDay(args: Any*): MonthDay = ${MonthDayLiteral('ctx, 'args)}
    inline def offsetDateTime(args: Any*): OffsetDateTime = ${OffsetDateTimeLiteral('ctx, 'args)}
    inline def offsetTime(args: Any*): OffsetTime = ${OffsetTimeLiteral('ctx, 'args)}
    inline def period(args: Any*): Period = ${PeriodLiteral('ctx, 'args)}
    inline def year(args: Any*): Year = ${YearLiteral('ctx, 'args)}
    inline def yearMonth(args: Any*): YearMonth = ${YearMonthLiteral('ctx, 'args)}
    inline def zonedDateTime(args: Any*): ZonedDateTime = ${ZonedDateTimeLiteral('ctx, 'args)}
    inline def zoneId(args: Any*): ZoneId = ${ZoneIdLiteral('ctx, 'args)}
    inline def zoneOffset(args: Any*): ZoneOffset = ${ZoneOffsetLiteral('ctx, 'args)}
    inline def dateTimeFormatter(args: Any*): DateTimeFormatter = ${DateTimeFormatterLiteral('ctx, 'args)}
  }

  object DurationLiteral extends Literally[Duration] {
    override def validate(s: String)(using Quotes) =
      Try(Duration.parse(s)) match {
        case Failure(ex) => Left(ex.getMessage)
        case Success(_) => Right('{java.time.Duration.parse(${Expr(s)})})
      }
  }

  object InstantLiteral extends Literally[Instant] {
    override def validate(s: String)(using Quotes) =
      Try(Instant.parse(s)) match {
        case Failure(ex) => Left(ex.getMessage)
        case Success(_) => Right('{java.time.Instant.parse(${Expr(s)})})
      }
  }

  object LocalDateLiteral extends Literally[LocalDate] {
    override def validate(s: String)(using Quotes) =
      Try(LocalDate.parse(s)) match {
        case Failure(ex) => Left(ex.getMessage)
        case Success(_) => Right('{java.time.LocalDate.parse(${Expr(s)})})
      }
  }

  object LocalDateTimeLiteral extends Literally[LocalDateTime] {
    override def validate(s: String)(using Quotes) =
      Try(LocalDateTime.parse(s)) match {
        case Failure(ex) => Left(ex.getMessage)
        case Success(_) => Right('{java.time.LocalDateTime.parse(${Expr(s)})})
      }
  }

  object LocalTimeLiteral extends Literally[LocalTime] {
    override def validate(s: String)(using Quotes) =
      Try(LocalTime.parse(s)) match {
        case Failure(ex) => Left(ex.getMessage)
        case Success(_) => Right('{java.time.LocalTime.parse(${Expr(s)})})
      }
  }

  object MonthDayLiteral extends Literally[MonthDay] {
    override def validate(s: String)(using Quotes) =
      Try(MonthDay.parse(s)) match {
        case Failure(ex) => Left(ex.getMessage)
        case Success(_) => Right('{java.time.MonthDay.parse(${Expr(s)})})
      }
  }

  object OffsetDateTimeLiteral extends Literally[OffsetDateTime] {
    override def validate(s: String)(using Quotes) =
      Try(OffsetDateTime.parse(s)) match {
        case Failure(ex) => Left(ex.getMessage)
        case Success(_) => Right('{java.time.OffsetDateTime.parse(${Expr(s)})})
      }
  }

  object OffsetTimeLiteral extends Literally[OffsetTime] {
    override def validate(s: String)(using Quotes) =
      Try(OffsetTime.parse(s)) match {
        case Failure(ex) => Left(ex.getMessage)
        case Success(_) => Right('{java.time.OffsetTime.parse(${Expr(s)})})
      }
  }

  object PeriodLiteral extends Literally[Period] {
    override def validate(s: String)(using Quotes) =
      Try(Period.parse(s)) match {
        case Failure(ex) => Left(ex.getMessage)
        case Success(_) => Right('{java.time.Period.parse(${Expr(s)})})
      }
  }

  object YearLiteral extends Literally[Year] {
    override def validate(s: String)(using Quotes) =
      Try(Year.parse(s)) match {
        case Failure(ex) => Left(ex.getMessage)
        case Success(_) => Right('{java.time.Year.parse(${Expr(s)})})
      }
  }

  object YearMonthLiteral extends Literally[YearMonth] {
    override def validate(s: String)(using Quotes) =
      Try(YearMonth.parse(s)) match {
        case Failure(ex) => Left(ex.getMessage)
        case Success(_) => Right('{java.time.YearMonth.parse(${Expr(s)})})
      }
  }

  object ZonedDateTimeLiteral extends Literally[ZonedDateTime] {
    override def validate(s: String)(using Quotes) =
    Try(ZonedDateTime.parse(s)) match {
      case Failure(ex) => Left(ex.getMessage)
      case Success(_) => Right('{java.time.ZonedDateTime.parse(${Expr(s)})})
    }
  }

  object ZoneIdLiteral extends Literally[ZoneId] {
    override def validate(s: String)(using Quotes) =
    Try(ZoneId.of(s)) match {
      case Failure(ex) => Left(ex.getMessage)
      case Success(_) => Right('{java.time.ZoneId.of(${Expr(s)})})
    }
  }

  object ZoneOffsetLiteral extends Literally[ZoneOffset] {
    override def validate(s: String)(using Quotes) =
    Try(ZoneOffset.of(s)) match {
      case Failure(ex) => Left(ex.getMessage)
      case Success(_) => Right('{java.time.ZoneOffset.of(${Expr(s)})})
    }
  }

  object DateTimeFormatterLiteral extends Literally[DateTimeFormatter] {
    override def validate(s: String)(using Quotes) =
    Try(DateTimeFormatter.ofPattern(s)) match {
      case Failure(ex) => Left(ex.getMessage)
      case Success(_) => Right('{java.time.format.DateTimeFormatter.ofPattern(${Expr(s)})})
    }
  }

}
