package org.stubit.random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.time.Duration;
import org.junit.jupiter.api.Test;

class RandomDurationTest {

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
    Duration max = Duration.ofMinutes(15);
    Duration min = max.plusSeconds(1);
    assertThatIllegalArgumentException()
        .isThrownBy(() -> RandomDuration.aDurationBetween(min, max))
        .withMessage("Can't set max to %s, as it must not be less than min (%s)", max, min);
  }
}
