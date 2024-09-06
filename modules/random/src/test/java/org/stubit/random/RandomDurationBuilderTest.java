package org.stubit.random;

import static java.lang.Long.MAX_VALUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.stubit.random.RandomNumber.aPositiveInt;

import java.time.Duration;
import org.junit.jupiter.api.Test;

class RandomDurationBuilderTest {

  @Test
  void aDuration() {
    assertThat(RandomDuration.aDuration().build()).isPositive();
  }

  @Test
  void min() {
    Duration min = Duration.ofDays(aPositiveInt());
    assertThat(RandomDuration.aDuration().min(min).build()).isGreaterThanOrEqualTo(min);
  }

  @Test
  void max() {
    Duration max = Duration.ofDays(aPositiveInt());
    assertThat(RandomDuration.aDuration().max(max).build()).isLessThanOrEqualTo(max);
  }

  @Test
  void max_less_than_min() {
    Duration min = Duration.ofDays(aPositiveInt());
    Duration max = min.minus(Duration.ofSeconds(1));
    assertThatIllegalArgumentException()
        .isThrownBy(() -> RandomDuration.aDuration().min(min).max(max))
        .withMessage("Can't set max to %s, as it must not be less than min (%s)", max, min);
  }

  @Test
  void min_greater_than_max() {
    Duration max = Duration.ofDays(aPositiveInt());
    Duration min = max.plusSeconds(1);
    assertThatIllegalArgumentException()
        .isThrownBy(() -> RandomDuration.aDuration().max(max).min(min))
        .withMessage("Can't set min to %s, as it must not be greater than max (%s)", min, max);
  }

  @Test
  void maximum() {
    Duration max = Duration.ofSeconds(MAX_VALUE);
    assertThat(max).isPositive();
  }
}
