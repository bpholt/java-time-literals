package dev.holt.javatime

import dev.holt.javatime.literals._
import munit.FunSuite

import java.time._

class JavaTimeSuite extends FunSuite {
  test("Duration interpolation should work like runtime parsing") {
    val expected = Duration.parse("PT20.345S")
    val output = duration"PT20.345S"

    assertEquals(expected, output)
  }

  test("Instant interpolation should work like runtime parsing") {
    val expected = Instant.parse("2007-12-03T10:15:30.00Z")
    val output = instant"2007-12-03T10:15:30.00Z"

    assertEquals(expected, output)
  }

  test("LocalDate interpolation should work like runtime parsing") {
    val expected = LocalDate.parse("2007-12-03")
    val output = localDate"2007-12-03"

    assertEquals(expected, output)
  }

  test("LocalDateTime interpolation should work like runtime parsing") {
    val expected = LocalDateTime.parse("2007-12-03T10:15:30")
    val output = localDateTime"2007-12-03T10:15:30"

    assertEquals(expected, output)
  }

  test("LocalTime interpolation should work like runtime parsing") {
    val expected = LocalTime.parse("10:15")
    val output = localTime"10:15"

    assertEquals(expected, output)
  }

  test("MonthDay interpolation should work like runtime parsing") {
    val expected = MonthDay.parse("--12-03")
    val output = monthDay"--12-03"

    assertEquals(expected, output)
  }

  test("OffsetDateTime interpolation should work like runtime parsing") {
    val expected = OffsetDateTime.parse("2007-12-03T10:15:30+01:00")
    val output = offsetDateTime"2007-12-03T10:15:30+01:00"

    assertEquals(expected, output)
  }

  test("OffsetTime interpolation should work like runtime parsing") {
    val expected = OffsetTime.parse("10:15:30+01:00")
    val output = offsetTime"10:15:30+01:00"

    assertEquals(expected, output)
  }

  test("Period interpolation should work like runtime parsing") {
    val expected = Period.parse("P2Y")
    val output = period"P2Y"

    assertEquals(expected, output)
  }

  test("Year interpolation should work like runtime parsing") {
    val expected = Year.parse("2007")
    val output = year"2007"

    assertEquals(expected, output)
  }

  test("YearMonth interpolation should work like runtime parsing") {
    val expected = YearMonth.parse("2007-12")
    val output = yearMonth"2007-12"

    assertEquals(expected, output)
  }

  test("ZonedDateTime interpolation should work like runtime parsing") {
    val expected = ZonedDateTime.parse("2007-12-03T10:15:30+01:00[Europe/Paris]")
    val output = zonedDateTime"2007-12-03T10:15:30+01:00[Europe/Paris]"

    assertEquals(expected, output)
  }

  test("ZoneId interpolation should work like runtime parsing") {
    val expected = ZoneId.of("Z")
    val output = zoneId"Z"

    assertEquals(expected, output)
  }

  test("ZoneOffset interpolation should work like runtime parsing") {
    val expected = ZoneOffset.of("Z")
    val output = zoneOffset"Z"

    assertEquals(expected, output)
  }
}
