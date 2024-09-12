package org.stubit.random;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.DateTimeException;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;

class RandomLocalTimeBuilderTest {

  @Test
  void aLocalTime() {
    assertThat(RandomLocalTime.aLocalTime().build()).isBetween(LocalTime.MIN, LocalTime.MAX);
  }

  @Test
  void hour() {
    int expectedHour = 23;
    LocalTime radomLocalTime = RandomLocalTime.aLocalTime().hour(expectedHour).build();
    assertThat(radomLocalTime.getHour()).isEqualTo(expectedHour);
  }

  @Test
  void hour_negative() {
    var localTimeBuilder = RandomLocalTime.aLocalTime();
    assertThatExceptionOfType(DateTimeException.class)
        .isThrownBy(() -> localTimeBuilder.hour(-1))
        .withMessage("Invalid value for HourOfDay (valid values 0 - 23): -1");
  }

  @Test
  void hour_too_big() {
    var localTimeBuilder = RandomLocalTime.aLocalTime();
    assertThatExceptionOfType(DateTimeException.class)
        .isThrownBy(() -> localTimeBuilder.hour(24))
        .withMessage("Invalid value for HourOfDay (valid values 0 - 23): 24");
  }

  @Test
  void minute() {
    int expectedMinute = 59;
    LocalTime radomLocalTime = RandomLocalTime.aLocalTime().minute(expectedMinute).build();
    assertThat(radomLocalTime.getMinute()).isEqualTo(expectedMinute);
  }

  @Test
  void minute_negative() {
    var localTimeBuilder = RandomLocalTime.aLocalTime();
    assertThatExceptionOfType(DateTimeException.class)
        .isThrownBy(() -> localTimeBuilder.minute(-1))
        .withMessage("Invalid value for MinuteOfHour (valid values 0 - 59): -1");
  }

  @Test
  void minute_too_big() {
    var localTimeBuilder = RandomLocalTime.aLocalTime();
    assertThatExceptionOfType(DateTimeException.class)
        .isThrownBy(() -> localTimeBuilder.minute(60))
        .withMessage("Invalid value for MinuteOfHour (valid values 0 - 59): 60");
  }

  @Test
  void second() {
    int expectedSecond = 59;
    LocalTime radomLocalTime = RandomLocalTime.aLocalTime().second(expectedSecond).build();
    assertThat(radomLocalTime.getSecond()).isEqualTo(expectedSecond);
  }

  @Test
  void second_negative() {
    var localTimeBuilder = RandomLocalTime.aLocalTime();
    assertThatExceptionOfType(DateTimeException.class)
        .isThrownBy(() -> localTimeBuilder.second(-1))
        .withMessage("Invalid value for SecondOfMinute (valid values 0 - 59): -1");
  }

  @Test
  void second_too_big() {
    var localTimeBuilder = RandomLocalTime.aLocalTime();
    assertThatExceptionOfType(DateTimeException.class)
        .isThrownBy(() -> localTimeBuilder.second(60))
        .withMessage("Invalid value for SecondOfMinute (valid values 0 - 59): 60");
  }

  @Test
  void nano() {
    int expectedNano = 999999999;
    LocalTime radomLocalTime = RandomLocalTime.aLocalTime().nano(expectedNano).build();
    assertThat(radomLocalTime.getNano()).isEqualTo(expectedNano);
  }

  @Test
  void nano_negative() {
    var localTimeBuilder = RandomLocalTime.aLocalTime();
    assertThatExceptionOfType(DateTimeException.class)
        .isThrownBy(() -> localTimeBuilder.nano(-1))
        .withMessage("Invalid value for NanoOfSecond (valid values 0 - 999999999): -1");
  }

  @Test
  void nano_too_big() {
    var localTimeBuilder = RandomLocalTime.aLocalTime();
    assertThatExceptionOfType(DateTimeException.class)
        .isThrownBy(() -> localTimeBuilder.nano(1000000000))
        .withMessage("Invalid value for NanoOfSecond (valid values 0 - 999999999): 1000000000");
  }
}
