package org.stubit.random;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalTime;
import org.junit.jupiter.api.Test;

class RandomLocalTimeInRangeBuilderTest {

  @Test
  void aLocalTimeInRange() {
    assertThat(RandomLocalTime.aLocalTimeInRange().build()).isBetween(LocalTime.MIN, LocalTime.MAX);
  }

  @Test
  void after() {
    LocalTime min = LocalTime.of(12, 23, 45);
    assertThat(RandomLocalTime.aLocalTimeInRange().after(min).build()).isAfterOrEqualTo(min);
  }

  @Test
  void after_before_VALUE() {
    LocalTime min = LocalTime.MAX;
    assertThat(RandomLocalTime.aLocalTimeInRange().after(min).build()).isEqualTo(min);
  }

  @Test
  void after_greater_than_before() {
    LocalTime max = LocalTime.of(12, 23, 45);
    LocalTime min = max.plusNanos(1);
    assertThatIllegalArgumentException()
        .isThrownBy(() -> RandomLocalTime.aLocalTimeInRange().before(max).after(min))
        .withMessage("Can't set after to %s, as it must not be greater than before (%s)", min, max);
  }

  @Test
  void before() {
    LocalTime max = LocalTime.of(12, 23, 45);
    assertThat(RandomLocalTime.aLocalTimeInRange().before(max).build()).isBeforeOrEqualTo(max);
  }

  @Test
  void before_after_VALUE() {
    LocalTime max = LocalTime.MIN;
    assertThat(RandomLocalTime.aLocalTimeInRange().before(max).build()).isEqualTo(max);
  }

  @Test
  void before_less_than_after() {
    LocalTime after = LocalTime.of(12, 23, 45);
    LocalTime before = after.minusNanos(1);
    assertThatIllegalArgumentException()
        .isThrownBy(() -> RandomLocalTime.aLocalTimeInRange().after(after).before(before))
        .withMessage(
            "Can't set before to %s, as it must not be less than after (%s)", before, after);
  }

  @Test
  void future() {
    assertThat(RandomLocalTime.aLocalTimeInRange().future().build()).isAfter(LocalTime.now());
  }

  @Test
  void past() {
    assertThat(RandomLocalTime.aLocalTimeInRange().past().build()).isBefore(LocalTime.now());
  }
}
