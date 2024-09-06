package org.stubit.random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.stubit.random.RandomChoice.aChoiceFrom;
import static org.stubit.random.RandomChoice.aChoiceFromValuesOf;
import static org.stubit.random.RandomChoice.any;
import static org.stubit.random.RandomChoice.anyOf;
import static org.stubit.random.RandomDuration.aDuration;
import static org.stubit.random.RandomDuration.aDurationBetween;
import static org.stubit.random.RandomInt.aNegativeInt;
import static org.stubit.random.RandomInt.aPositiveInt;
import static org.stubit.random.RandomInt.anInt;
import static org.stubit.random.RandomInt.anIntBetween;
import static org.stubit.random.RandomLocalDate.aLocalDate;
import static org.stubit.random.RandomLocalDate.aLocalDateBetween;
import static org.stubit.random.RandomLocalDate.aLocalDateInRange;
import static org.stubit.random.RandomLong.aLong;
import static org.stubit.random.RandomLong.aLongBetween;
import static org.stubit.random.RandomLong.aNegativeLong;
import static org.stubit.random.RandomLong.aPositiveLong;
import static org.stubit.random.RandomString.aLetterFrom;
import static org.stubit.random.RandomString.aStringStartingWith;
import static org.stubit.random.RandomString.arabicDigits;
import static org.stubit.random.RandomString.digitsFrom;
import static org.stubit.random.RandomString.latinLetters;
import static org.stubit.random.RandomString.lettersFrom;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class RandomDocTest {

  @Test
  void randomInt_examples() {
    // tag::anIntBetween[]
    int someIntBetween42And4711 = anIntBetween(42, 4711);
    assertThat(someIntBetween42And4711).isBetween(42, 4711);
    // end::anIntBetween[]

    // tag::aPositiveInt[]
    int somePositiveInt = aPositiveInt();
    assertThat(somePositiveInt).isPositive().isNotZero().isLessThan(Integer.MAX_VALUE);
    // end::aPositiveInt[]

    // tag::aNegativeInt[]
    int someNegativeInt = aNegativeInt();
    assertThat(someNegativeInt).isNegative().isNotZero().isGreaterThanOrEqualTo(Integer.MIN_VALUE);
    // end::aNegativeInt[]
  }

  @Test
  void randomIntBuilder_examples() {
    // tag::anInt[]
    int someInt = anInt().build();
    assertThat(someInt).isBetween(Integer.MIN_VALUE, Integer.MAX_VALUE - 1);

    int someIntBetween42And4711 = anInt().min(42).max(4711).build();
    assertThat(someIntBetween42And4711).isBetween(42, 4711);

    int someIntLessThan4711 = anInt().max(4711).build();
    assertThat(someIntLessThan4711).isLessThanOrEqualTo(4711);

    int someIntGreaterThanMinus10 = anInt().min(-42).build();
    assertThat(someIntGreaterThanMinus10).isGreaterThanOrEqualTo(-42);
    // end::anInt[]
  }

  @Test
  void randomLong_examples() {
    // tag::aLongBetween[]
    long someLongBetween42And4711 = aLongBetween(42, 4711);
    assertThat(someLongBetween42And4711).isBetween(42L, 4711L);
    // end::aLongBetween[]

    // tag::aPositiveLong[]
    long somePositiveLong = aPositiveLong();
    assertThat(somePositiveLong).isPositive().isNotZero().isLessThan(Long.MAX_VALUE);
    // end::aPositiveLong[]

    // tag::aNegativeLong[]
    long someNegativeLong = aNegativeLong();
    assertThat(someNegativeLong).isNegative().isNotZero().isGreaterThanOrEqualTo(Long.MIN_VALUE);
    // end::aNegativeLong[]
  }

  @Test
  void randomLongBuilder_examples() {
    // tag::aLong[]
    long someLong = aLong().build();
    assertThat(someLong).isBetween(Long.MIN_VALUE, Long.MAX_VALUE - 1);

    long someLongBetween42And4711 = aLong().min(42L).max(4711L).build();
    assertThat(someLongBetween42And4711).isBetween(42L, 4711L);

    long someLongLessThan4711 = aLong().max(4711L).build();
    assertThat(someLongLessThan4711).isLessThanOrEqualTo(4711L);

    long someLongGreaterThanMinus10 = aLong().min(-42L).build();
    assertThat(someLongGreaterThanMinus10).isGreaterThanOrEqualTo(-42L);
    // end::aLong[]
  }

  @Test
  void randomChoice_examples() {
    // tag::anyOf_ellipsis[]
    String choiceFromEllipsis = anyOf("a", "b", "c");
    assertThat(choiceFromEllipsis).isIn("a", "b", "c");
    // end::anyOf_ellipsis[]

    // tag::anyOf_array[]
    String[] choiceArray = {"a", "b", "c"};
    String choiceFromArray = anyOf(choiceArray[0], choiceArray[1], choiceArray[2]);
    assertThat(choiceFromArray).isIn((Object[]) choiceArray);
    // end::anyOf_array[]

    // tag::anyOf_list[]
    List<String> choicesList = List.of("a", "b", "c");
    String choiceFromList = anyOf(choicesList);
    assertThat(choiceFromList).isIn(choicesList);
    // end::anyOf_list[]

    // tag::anyOf_map[]
    Map<String, Integer> choicesMap = Map.of("a", 1, "b", 2, "c", 3);
    Map.Entry<String, Integer> choiceFromMap = anyOf(choicesMap);
    assertThat(choiceFromMap).isIn(choicesMap.entrySet());
    // end::anyOf_map[]

    // tag::any_enum[]
    enum Color {
      RED,
      GREEN,
      BLUE
    }
    Color choiceFromEnum = any(Color.class);
    assertThat(choiceFromEnum).isIn(Color.RED, Color.GREEN, Color.BLUE);
    // end::any_enum[]
  }

  @Test
  void randomChoiceBuilder_examples() {
    // tag::aChoiceFrom_ellipsis[]
    String choiceFromEllipsis = aChoiceFrom("a", "b", "c").build();
    assertThat(choiceFromEllipsis).isIn("a", "b", "c");
    // end::aChoiceFrom_ellipsis[]

    // tag::aChoiceFrom_array[]
    String[] choicesArray = {"a", "b", "c"};
    String choiceFromArray = aChoiceFrom(choicesArray).build();
    assertThat(choiceFromArray).isIn((Object[]) choicesArray);
    // end::aChoiceFrom_array[]

    // tag::aChoiceFrom_list[]
    var choicesList = List.of("a", "b", "c");
    String choiceFromList = aChoiceFrom(choicesList).build();
    assertThat(choiceFromList).isIn(choicesList);
    // end::aChoiceFrom_list[]

    // tag::aChoiceFrom_map[]
    Map<String, Integer> choicesMap = Map.of("a", 1, "b", 2, "c", 3);
    Map.Entry<String, Integer> choiceFromMap = aChoiceFrom(choicesMap).build();
    assertThat(choiceFromMap).isIn(choicesMap.entrySet());
    // end::aChoiceFrom_map[]

    // tag::aChoiceFromValuesOf_enum[]
    enum Color {
      RED,
      GREEN,
      BLUE
    }
    Color choiceFromEnum = aChoiceFromValuesOf(Color.class).build();
    assertThat(choiceFromEnum).isIn(Color.RED, Color.GREEN, Color.BLUE);
    // end::aChoiceFromValuesOf_enum[]

    // tag::and[]
    String choiceWithAdditions = aChoiceFrom(List.of("a", "b")).and("c", "d").build();
    assertThat(choiceWithAdditions).isIn("a", "b", "c", "d");
    // end::and[]

    // tag::butNot[]
    String choiceWithExclusions = aChoiceFrom(List.of("a", "b", "c")).butNot("a").build();
    assertThat(choiceWithExclusions).isNotEqualTo("a");
    // end::butNot[]
  }

  @Test
  void randomString_examples() {
    // tag::germanLicensePlate[]
    String germanLicensePlate =
        aStringStartingWith(lettersFrom(anIntBetween(1, 3), Alphabet.GERMAN).toUpperCase())
            .followedBy("-")
            .followedBy(latinLetters(2).toUpperCase())
            .followedBy(arabicDigits(anIntBetween(1, 4)))
            .build();

    assertThat(germanLicensePlate).matches("[A-ZÄÖÜẞ]{1,3}-[A-Z]{2}\\d{1,4}");
    // end::germanLicensePlate[]

    // tag::iranianLicensePlate[]
    String iranianLicensePlate =
        aStringStartingWith(digitsFrom(2, DigitSystem.PERSIAN))
            .followedBy(aLetterFrom(Alphabet.BASIC_ARABIC))
            .followedBy(digitsFrom(3, DigitSystem.PERSIAN))
            .build();

    assertThat(iranianLicensePlate).matches("[۰-۹]{2}[\\u0600-\\u06FF][۰-۹]{3}");
    // end::iranianLicensePlate[]
  }

  @Test
  void randomDuration_examples() {
    // tag::aDurationBetween[]
    Duration someDuration = aDurationBetween(Duration.ZERO, Duration.ofDays(7));
    assertThat(someDuration).isBetween(Duration.ZERO, Duration.ofDays(7));
    // end::aDurationBetween[]
  }

  @Test
  void randomDurationBuilder_examples() {
    // tag::aDuration[]
    Duration someDuration = aDuration().build();
    assertThat(someDuration).isBetween(Duration.ZERO, Duration.ofSeconds(Long.MAX_VALUE - 1));

    Duration someDurationBetween1And2Hours =
        aDuration().min(Duration.ofHours(1)).max(Duration.ofHours(2)).build();
    assertThat(someDurationBetween1And2Hours).isBetween(Duration.ofHours(1), Duration.ofHours(2));

    Duration someDurationLessThan2Hours = aDuration().max(Duration.ofHours(2)).build();
    assertThat(someDurationLessThan2Hours).isLessThanOrEqualTo(Duration.ofHours(2));

    Duration someDurationGreaterThan1Hour = aDuration().min(Duration.ofHours(1)).build();
    assertThat(someDurationGreaterThan1Hour).isGreaterThanOrEqualTo(Duration.ofHours(1));
    // end::aDuration[]
  }

  @Test
  void randomLocalDate_examples() {
    // tag::aLocalDateBetween[]
    LocalDate someLockdownDay =
        aLocalDateBetween(LocalDate.of(2020, 3, 8), LocalDate.of(2020, 5, 4));
    assertThat(someLockdownDay).isBetween(LocalDate.of(2020, 3, 8), LocalDate.of(2020, 5, 4));
    // end::aLocalDateBetween[]
  }

  @Test
  void randomLocalDateInRangeBuilder_examples() {
    // tag::aLocalDateInRange[]
    LocalDate someLocalDate = aLocalDateInRange().build();
    assertThat(someLocalDate).isBetween(LocalDate.MIN, LocalDate.MAX);

    LocalDate someFutureDay = aLocalDateInRange().future().build();
    assertThat(someFutureDay).isAfterOrEqualTo(LocalDate.now());

    LocalDate somePastDay = aLocalDateInRange().past().build();
    assertThat(somePastDay).isBeforeOrEqualTo(LocalDate.now());

    LocalDate somePastAfterChristDay =
        aLocalDateInRange().past().after(LocalDate.of(1, 1, 1)).build();
    assertThat(somePastAfterChristDay)
        .isBeforeOrEqualTo(LocalDate.now())
        .isAfterOrEqualTo(LocalDate.of(1, 1, 1));

    LocalDate someFutureDayInTheNext5Years =
        aLocalDateInRange().future().before(Year.now().plusYears(5).atDay(1)).build();
    assertThat(someFutureDayInTheNext5Years)
        .isAfterOrEqualTo(LocalDate.now())
        .isBeforeOrEqualTo(Year.now().plusYears(5).atDay(1));

    LocalDate someLockdownDay =
        aLocalDateInRange()
            .after(LocalDate.of(2020, 3, 8))
            .before(LocalDate.of(2020, 5, 4))
            .build();
    assertThat(someLockdownDay).isBetween(LocalDate.of(2020, 3, 8), LocalDate.of(2020, 5, 4));
    // end::aLocalDateInRange[]
  }

  @Test
  void randomLocalDateBuilder_examples() {
    // tag::aLocalDate[]
    LocalDate someLocalDate = aLocalDate().build();
    assertThat(someLocalDate).isBetween(LocalDate.MIN, LocalDate.MAX);

    LocalDate someDateIn2025 = aLocalDate().year(2025).build();
    assertThat(someDateIn2025.getYear()).isEqualTo(2025);

    LocalDate someDateInMarch = aLocalDate().month(Month.MARCH).build();
    assertThat(someDateInMarch.getMonth()).isEqualTo(Month.MARCH);

    LocalDate someDateInMarch2025 = aLocalDate().year(2025).month(Month.MARCH).build();
    assertThat(someDateInMarch2025.getYear()).isEqualTo(2025);
    assertThat(someDateInMarch2025.getMonth()).isEqualTo(Month.MARCH);

    LocalDate some3rd = aLocalDate().dayOfMonth(3).build();
    assertThat(some3rd.getDayOfMonth()).isEqualTo(3);

    LocalDate someSunday = aLocalDate().dayOfWeek(DayOfWeek.SUNDAY).build();
    assertThat(someSunday.getDayOfWeek()).isEqualTo(DayOfWeek.SUNDAY);

    LocalDate someTuesday1999 = aLocalDate().year(1999).dayOfWeek(DayOfWeek.TUESDAY).build();
    assertThat(someTuesday1999.getDayOfWeek()).isEqualTo(DayOfWeek.TUESDAY);
    assertThat(someTuesday1999.getYear()).isEqualTo(1999);
    // end::aLocalDate[]
  }
}
