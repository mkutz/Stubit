package org.stubit.random;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import org.junit.jupiter.api.Test;

class RandomLocalDateBuilderTest {

  @Test
  void aLocalDate() {
    assertThat(RandomLocalDate.aLocalDate().build()).isBetween(LocalDate.MIN, LocalDate.MAX);
  }

  @Test
  void year_enum() {
    int expectedYear = 2024;
    LocalDate radomLocalDate = RandomLocalDate.aLocalDate().year(Year.of(expectedYear)).build();
    assertThat(radomLocalDate.getYear()).isEqualTo(expectedYear);
  }

  @Test
  void year_int() {
    int expectedYear = 2024;
    LocalDate radomLocalDate = RandomLocalDate.aLocalDate().year(expectedYear).build();
    assertThat(radomLocalDate.getYear()).isEqualTo(expectedYear);
  }

  @Test
  void month_enum() {
    Month expectedMonth = Month.APRIL;
    LocalDate radomLocalDate = RandomLocalDate.aLocalDate().month(expectedMonth).build();
    assertThat(radomLocalDate.getMonth()).isEqualTo(expectedMonth);
  }

  @Test
  void month_int() {
    Month expectedMonth = Month.NOVEMBER;
    LocalDate radomLocalDate = RandomLocalDate.aLocalDate().month(expectedMonth.getValue()).build();
    assertThat(radomLocalDate.getMonth()).isEqualTo(expectedMonth);
  }

  @Test
  void dayOfMonth() {
    LocalDate radomLocalDate = RandomLocalDate.aLocalDate().dayOfMonth(11).build();
    assertThat(radomLocalDate.getDayOfMonth()).isEqualTo(11);
  }

  @Test
  void dayOfMonth_negative() {
    var localDateBuilder = RandomLocalDate.aLocalDate();
    assertThatExceptionOfType(DateTimeException.class)
        .isThrownBy(() -> localDateBuilder.dayOfMonth(-1))
        .withMessage("Invalid value for DayOfMonth (valid values 1 - 28/31): -1");
  }

  @Test
  void dayOfMonth_greater_maximum() {
    var localDateBuilder = RandomLocalDate.aLocalDate();
    assertThatExceptionOfType(DateTimeException.class)
        .isThrownBy(() -> localDateBuilder.dayOfMonth(32))
        .withMessage("Invalid value for DayOfMonth (valid values 1 - 28/31): 32");
  }

  @Test
  void dayOfMonth_greater_maximum_month_set() {
    var builderWithMonthSet = RandomLocalDate.aLocalDate().month(Month.FEBRUARY);
    assertThatExceptionOfType(DateTimeException.class)
        .isThrownBy(() -> builderWithMonthSet.dayOfMonth(30))
        .withMessage("Invalid date 'FEBRUARY 30'");
  }

  @Test
  void dayOfMonth_dayOfWeek_already_set() {
    var builderWithDayOfWeekSet = RandomLocalDate.aLocalDate().dayOfWeek(DayOfWeek.MONDAY);

    var localDate = builderWithDayOfWeekSet.dayOfMonth(11).build();

    assertThat(localDate.getDayOfMonth()).isEqualTo(11);
    assertThat(localDate.getDayOfWeek()).isInstanceOf(DayOfWeek.class);
  }

  @Test
  void dayOfWeek_enum() {
    assertThat(RandomLocalDate.aLocalDate().dayOfWeek(DayOfWeek.MONDAY).build().getDayOfWeek())
        .isEqualTo(DayOfWeek.MONDAY);
  }

  @Test
  void dayOfWeek_int() {
    assertThat(RandomLocalDate.aLocalDate().dayOfWeek(3).build().getDayOfWeek())
        .isEqualTo(DayOfWeek.WEDNESDAY);
  }

  @Test
  void dayOfWeek_day_already_set() {
    var builderWithDaySet = RandomLocalDate.aLocalDate().dayOfMonth(11);

    var localDate = builderWithDaySet.dayOfWeek(DayOfWeek.MONDAY).build();

    assertThat(localDate.getDayOfWeek()).isEqualTo(DayOfWeek.MONDAY);
    assertThat(localDate.getDayOfMonth()).isCloseTo(11, within(7));
  }
}
