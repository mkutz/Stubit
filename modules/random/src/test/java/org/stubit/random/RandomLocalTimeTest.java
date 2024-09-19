package org.stubit.random;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;
import org.junit.jupiter.api.Test;

class RandomLocalTimeTest {

  @Test
  void aLocalTimeBetween_min_equal_to_max() {
    LocalTime min = LocalTime.now();
    assertThat(RandomLocalTime.aLocalTimeBetween(min, min)).isEqualTo(min);
  }

  @Test
  void aFutureLocalTime() {
    assertThat(RandomLocalTime.aFutureLocalTime()).isAfter(LocalTime.now());
  }

  @Test
  void aPastLocalTime() {
    assertThat(RandomLocalTime.aPastLocalTime()).isBefore(LocalTime.now());
  }
}
