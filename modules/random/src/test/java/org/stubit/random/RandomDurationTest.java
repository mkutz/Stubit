package org.stubit.random;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import org.junit.jupiter.api.Test;

public class RandomDurationTest {

  @Test
  void aDurationBetween() {
    var min = Duration.ofMinutes(15);
    var max = Duration.ofDays(1);
    assertThat(RandomDuration.aDurationBetween(min, max)).isBetween(min, max);
  }

  @Test
  void aDurationBetween_min_equal_to_max() {
    var min = Duration.ofMinutes(15);
    assertThat(RandomDuration.aDurationBetween(min, min)).isEqualTo(min);
  }

  @Test
  void aDurationBetween_min_greater_than_max() {
    var min = Duration.ofMinutes(15);
    assertThat(RandomDuration.aDurationBetween(min, min)).isEqualTo(min);
  }
}
