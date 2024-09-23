package examples.kotlin

import org.assertj.core.api.Assertions.assertThat

import java.time.DayOfWeek
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import java.time.Month
import java.time.Year
import java.util.List
import java.util.Map

import org.junit.jupiter.api.Test
import org.stubit.random.Alphabet
import org.stubit.random.DigitSystem
import org.stubit.random.RandomChoice.aChoiceFrom
import org.stubit.random.RandomChoice.aChoiceFromValuesOf
import org.stubit.random.RandomChoice.any
import org.stubit.random.RandomChoice.anyOf
import org.stubit.random.RandomDuration.aDuration
import org.stubit.random.RandomDuration.aDurationBetween
import org.stubit.random.RandomLocalDate.aLocalDate
import org.stubit.random.RandomLocalDate.aLocalDateBetween
import org.stubit.random.RandomLocalDate.aLocalDateInRange
import org.stubit.random.RandomLocalTime.aLocalTime
import org.stubit.random.RandomLocalTime.aLocalTimeBetween
import org.stubit.random.RandomLocalTime.aLocalTimeInRange
import org.stubit.random.RandomNumber.aLong
import org.stubit.random.RandomNumber.aLongBetween
import org.stubit.random.RandomNumber.aNegativeInt
import org.stubit.random.RandomNumber.aNegativeLong
import org.stubit.random.RandomNumber.aPositiveInt
import org.stubit.random.RandomNumber.aPositiveLong
import org.stubit.random.RandomNumber.anInt
import org.stubit.random.RandomNumber.anIntBetween
import org.stubit.random.RandomString.aLetterFrom
import org.stubit.random.RandomString.aStringStartingWith
import org.stubit.random.RandomString.arabicDigits
import org.stubit.random.RandomString.digitsFrom
import org.stubit.random.RandomString.latinLetters
import org.stubit.random.RandomString.lettersFrom

class RandomDocTest {

  @Test
  fun randomInt_examples() {
    // tag::anIntBetween[]
    val someIntBetween42And4711 = anIntBetween(42, 4711)
    assertThat(someIntBetween42And4711).isBetween(42, 4711)
    // end::anIntBetween[]

    // tag::aPositiveInt[]
    val somePositiveInt = aPositiveInt()
    assertThat(somePositiveInt).isPositive().isNotZero().isLessThan(Integer.MAX_VALUE)
    // end::aPositiveInt[]

    // tag::aNegativeInt[]
    val someNegativeInt = aNegativeInt()
    assertThat(someNegativeInt).isNegative().isNotZero().isGreaterThanOrEqualTo(Integer.MIN_VALUE)
    // end::aNegativeInt[]
  }

  @Test
  fun randomIntBuilder_examples() {
    // tag::anInt[]
    val someInt = anInt().build()
    assertThat(someInt).isBetween(Integer.MIN_VALUE, Integer.MAX_VALUE - 1)

    val someIntBetween42And4711 = anInt().min(42).max(4711).build()
    assertThat(someIntBetween42And4711).isBetween(42, 4711)

    val someIntLessThan4711 = anInt().max(4711).build()
    assertThat(someIntLessThan4711).isLessThanOrEqualTo(4711)

    val someIntGreaterThanMinus10 = anInt().min(-42).build()
    assertThat(someIntGreaterThanMinus10).isGreaterThanOrEqualTo(-42)
    // end::anInt[]
  }

  @Test
  fun randomLong_examples() {
    // tag::aLongBetween[]
    val someLongBetween42And4711 = aLongBetween(42, 4711)
    assertThat(someLongBetween42And4711).isBetween(42L, 4711L)
    // end::aLongBetween[]

    // tag::aPositiveLong[]
    val somePositiveLong = aPositiveLong()
    assertThat(somePositiveLong).isPositive().isNotZero().isLessThan(Long.MAX_VALUE)
    // end::aPositiveLong[]

    // tag::aNegativeLong[]
    val someNegativeLong = aNegativeLong()
    assertThat(someNegativeLong).isNegative().isNotZero().isGreaterThanOrEqualTo(Long.MIN_VALUE)
    // end::aNegativeLong[]
  }

  @Test
  fun randomLongBuilder_examples() {
    // tag::aLong[]
    val someLong = aLong().build()
    assertThat(someLong).isBetween(Long.MIN_VALUE, Long.MAX_VALUE - 1)

    val someLongBetween42And4711 = aLong().min(42L).max(4711L).build()
    assertThat(someLongBetween42And4711).isBetween(42L, 4711L)

    val someLongLessThan4711 = aLong().max(4711L).build()
    assertThat(someLongLessThan4711).isLessThanOrEqualTo(4711L)

    val someLongGreaterThanMinus10 = aLong().min(-42L).build()
    assertThat(someLongGreaterThanMinus10).isGreaterThanOrEqualTo(-42L)
    // end::aLong[]
  }

  // tag::enum_class[]
  enum class Color {
    RED,
    GREEN,
    BLUE
  }
  // end::enum_class[]

  @Test
  fun randomChoice_examples() {
    // tag::anyOf_ellipsis[]
    val choiceFromEllipsis = anyOf("a", "b", "c")
    assertThat(choiceFromEllipsis).isIn("a", "b", "c")
    // end::anyOf_ellipsis[]

    // tag::anyOf_array[]
    val choiceArray = arrayOf("a", "b", "c")
    val choiceFromArray = anyOf(choiceArray)
    assertThat(choiceFromArray).isIn(choiceArray)
    // end::anyOf_array[]

    // tag::anyOf_list[]
    val choicesList = List.of("a", "b", "c")
    val choiceFromList = anyOf(choicesList)
    assertThat(choiceFromList).isIn(choicesList)
    // end::anyOf_list[]

    // tag::anyOf_map[]
    val choicesMap = mapOf("a" to 1, "b" to 2, "c" to 3)
    val choiceFromMap = anyOf(choicesMap)
    assertThat(choiceFromMap).isIn(choicesMap.entries)
    // end::anyOf_map[]

    // tag::any_enum[]
    val choiceFromEnum = any(Color::class.java)
    assertThat(choiceFromEnum).isIn(Color.RED, Color.GREEN, Color.BLUE)
    // end::any_enum[]
  }

  @Test
  fun randomChoiceBuilder_examples() {
    // tag::aChoiceFrom_ellipsis[]
    val choiceFromEllipsis = aChoiceFrom("a", "b", "c").build()
    assertThat(choiceFromEllipsis).isIn("a", "b", "c")
    // end::aChoiceFrom_ellipsis[]

    // tag::aChoiceFrom_array[]
    val choicesArray = arrayOf("a", "b", "c")
    val choiceFromArray = aChoiceFrom(choicesArray).build()
    assertThat(choiceFromArray).isIn(choicesArray)
    // end::aChoiceFrom_array[]

    // tag::aChoiceFrom_list[]
    val choicesList = listOf("a", "b", "c")
    val choiceFromList = aChoiceFrom(choicesList).build()
    assertThat(choiceFromList).isIn(choicesList)
    // end::aChoiceFrom_list[]

    // tag::aChoiceFrom_map[]
    val choicesMap = mapOf("a" to 1, "b" to 2, "c" to 3)
    val choiceFromMap = aChoiceFrom(choicesMap).build()
    assertThat(choiceFromMap).isIn(choicesMap.entries)
    // end::aChoiceFrom_map[]

    // tag::aChoiceFromValuesOf_enum[]
    val choiceFromEnum = aChoiceFromValuesOf(Color::class.java).build()
    assertThat(choiceFromEnum).isIn(Color.RED, Color.GREEN, Color.BLUE)
    // end::aChoiceFromValuesOf_enum[]

    // tag::and[]
    val choiceWithAdditions = aChoiceFrom("a", "b").and("c", "d").build()
    assertThat(choiceWithAdditions).isIn("a", "b", "c", "d")
    // end::and[]

    // tag::butNot[]
    val choiceWithExclusions = aChoiceFrom("a", "b", "c").butNot("a").build()
    assertThat(choiceWithExclusions).isNotEqualTo("a")
    // end::butNot[]
  }

  @Test
  fun randomString_examples() {
    // tag::germanLicensePlate[]
    val germanLicensePlate =
        aStringStartingWith(lettersFrom(anIntBetween(1, 3), Alphabet.GERMAN).uppercase())
            .followedBy("-")
            .followedBy(latinLetters(2).uppercase())
            .followedBy(arabicDigits(anIntBetween(1, 4)))
            .build()

    assertThat(germanLicensePlate).matches("[A-ZÄÖÜẞ]{1,3}-[A-Z]{2}\\d{1,4}")
    // end::germanLicensePlate[]

    // tag::iranianLicensePlate[]
    val iranianLicensePlate =
        aStringStartingWith(digitsFrom(2, DigitSystem.PERSIAN))
            .followedBy(aLetterFrom(Alphabet.BASIC_ARABIC))
            .followedBy(digitsFrom(3, DigitSystem.PERSIAN))
            .build()

    assertThat(iranianLicensePlate).matches("[۰-۹]{2}[\\u0600-\\u06FF][۰-۹]{3}")
    // end::iranianLicensePlate[]
  }

  @Test
  fun randomDuration_examples() {
    // tag::aDurationBetween[]
    val someDuration = aDurationBetween(Duration.ZERO, Duration.ofDays(7))
    assertThat(someDuration).isBetween(Duration.ZERO, Duration.ofDays(7))
    // end::aDurationBetween[]
  }

  @Test
  fun randomDurationBuilder_examples() {
    // tag::aDuration[]
    val someDuration = aDuration().build()
    assertThat(someDuration).isBetween(Duration.ZERO, Duration.ofSeconds(Long.MAX_VALUE - 1))

    val someDurationBetween1And2Hours =
        aDuration().min(Duration.ofHours(1)).max(Duration.ofHours(2)).build()
    assertThat(someDurationBetween1And2Hours).isBetween(Duration.ofHours(1), Duration.ofHours(2))

    val someDurationLessThan2Hours = aDuration().max(Duration.ofHours(2)).build()
    assertThat(someDurationLessThan2Hours).isLessThanOrEqualTo(Duration.ofHours(2))

    val someDurationGreaterThan1Hour = aDuration().min(Duration.ofHours(1)).build()
    assertThat(someDurationGreaterThan1Hour).isGreaterThanOrEqualTo(Duration.ofHours(1))
    // end::aDuration[]
  }

  @Test
  fun randomLocalDate_examples() {
    // tag::aLocalDateBetween[]
    val someLockdownDay =
        aLocalDateBetween(LocalDate.of(2020, 3, 8), LocalDate.of(2020, 5, 4))
    assertThat(someLockdownDay).isBetween(LocalDate.of(2020, 3, 8), LocalDate.of(2020, 5, 4))
    // end::aLocalDateBetween[]
  }

  @Test
  fun randomLocalDateInRangeBuilder_examples() {
    // tag::aLocalDateInRange[]
    val someLocalDate = aLocalDateInRange().build()
    assertThat(someLocalDate).isBetween(LocalDate.MIN, LocalDate.MAX)

    val someFutureDay = aLocalDateInRange().future().build()
    assertThat(someFutureDay).isAfterOrEqualTo(LocalDate.now())

    val somePastDay = aLocalDateInRange().past().build()
    assertThat(somePastDay).isBeforeOrEqualTo(LocalDate.now())

    val somePastAfterChristDay =
        aLocalDateInRange().past().after(LocalDate.of(1, 1, 1)).build()
    assertThat(somePastAfterChristDay)
        .isBeforeOrEqualTo(LocalDate.now())
        .isAfterOrEqualTo(LocalDate.of(1, 1, 1))

    val someFutureDayInTheNext5Years =
        aLocalDateInRange().future().before(Year.now().plusYears(5).atDay(1)).build()
    assertThat(someFutureDayInTheNext5Years)
        .isAfterOrEqualTo(LocalDate.now())
        .isBeforeOrEqualTo(Year.now().plusYears(5).atDay(1))

    val someLockdownDay =
        aLocalDateInRange()
            .after(LocalDate.of(2020, 3, 8))
            .before(LocalDate.of(2020, 5, 4))
            .build()
    assertThat(someLockdownDay).isBetween(LocalDate.of(2020, 3, 8), LocalDate.of(2020, 5, 4))
    // end::aLocalDateInRange[]
  }

  @Test
  fun randomLocalDateBuilder_examples() {
    // tag::aLocalDate[]
    val someLocalDate = aLocalDate().build()
    assertThat(someLocalDate).isBetween(LocalDate.MIN, LocalDate.MAX)

    val someDateIn2025 = aLocalDate().year(2025).build()
    assertThat(someDateIn2025.year).isEqualTo(2025)

    val someDateInMarch = aLocalDate().month(Month.MARCH).build()
    assertThat(someDateInMarch.month).isEqualTo(Month.MARCH)

    val someDateInMarch2025 = aLocalDate().year(2025).month(Month.MARCH).build()
    assertThat(someDateInMarch2025.year).isEqualTo(2025)
    assertThat(someDateInMarch2025.month).isEqualTo(Month.MARCH)

    val some3rd = aLocalDate().dayOfMonth(3).build()
    assertThat(some3rd.dayOfMonth).isEqualTo(3)

    val someSunday = aLocalDate().dayOfWeek(DayOfWeek.SUNDAY).build()
    assertThat(someSunday.dayOfWeek).isEqualTo(DayOfWeek.SUNDAY)

    val someTuesday1999 = aLocalDate().year(1999).dayOfWeek(DayOfWeek.TUESDAY).build()
    assertThat(someTuesday1999.dayOfWeek).isEqualTo(DayOfWeek.TUESDAY)
    assertThat(someTuesday1999.year).isEqualTo(1999)
    // end::aLocalDate[]
  }

  @Test
  fun randomLocalTime_examples() {
    // tag::aLocalTimeBetween[]
    val someTimeBusinessHours = aLocalTimeBetween(LocalTime.of(9, 0), LocalTime.of(17, 0, 0))
    assertThat(someTimeBusinessHours).isBetween(LocalTime.of(9, 0), LocalTime.of(17, 0))
    // end::aLocalTimeBetween[]
  }

  @Test
  fun randomLocalTimeInRangeBuilder_examples() {
    // tag::aLocalTimeInRange[]
    val someLocalTime = aLocalTimeInRange().build()
    assertThat(someLocalTime).isBetween(LocalTime.MIN, LocalTime.MAX)

    val someFutureTime = aLocalTimeInRange().future().build()
    assertThat(someFutureTime).isAfterOrEqualTo(LocalTime.now())

    val somePastTime = aLocalTimeInRange().past().build()
    assertThat(somePastTime).isBeforeOrEqualTo(LocalTime.now())

    val someTimeAfterNoon = aLocalTimeInRange().after(LocalTime.NOON).build()
    assertThat(someTimeAfterNoon).isAfterOrEqualTo(LocalTime.NOON)
    // end::aLocalTimeInRange[]
  }

  @Test
  fun randomLocalTimeBuilder_examples() {
    // tag::aLocalTime[]
    val someLocalTime = aLocalTime().build()
    assertThat(someLocalTime).isBetween(LocalTime.MIN, LocalTime.MAX)

    val someTimeAt12 = aLocalTime().hour(12).build()
    assertThat(someTimeAt12.hour).isEqualTo(12)

    val someHalfTime = aLocalTime().minute(30).build()
    assertThat(someHalfTime.minute).isEqualTo(30)

    val someTimeAlmostFull = aLocalTime().minute(59).second(59).nano(999_999_999).build()
    assertThat(someTimeAlmostFull.minute).isEqualTo(59)
    assertThat(someTimeAlmostFull.second).isEqualTo(59)
    assertThat(someTimeAlmostFull.nano).isEqualTo(999_999_999)
    // end::aLocalTime[]
  }
}
