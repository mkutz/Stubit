package org.stubit.random;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RandomLocalDateBuilderTest {

  @Test
  void aLocalDate() {
    assertThat(RandomLocalDate.aLocalDate().build()).isBetween(LocalDate.MIN, LocalDate.MAX);
  }

  @Test
  void min() {
    LocalDate min = LocalDate.of(2012, 12, 20);
    assertThat(RandomLocalDate.aLocalDate().min(min).build()).isAfterOrEqualTo(min);
  }

  @Test
  void min_MAX_VALUE() {
    LocalDate min = LocalDate.MAX;
    assertThat(RandomLocalDate.aLocalDate().min(min).build()).isEqualTo(min);
  }

  @Test
  void min_greater_than_max() {
    LocalDate max = LocalDate.of(2012, 12, 20);
    LocalDate min = max.plusDays(1);
    assertThatIllegalArgumentException()
        .isThrownBy(() -> RandomLocalDate.aLocalDate().max(max).min(min))
        .withMessage("Can't set min to %s, as it must not be greater than max (%s)", min, max);
  }

  @Test
  void max() {
    LocalDate max = LocalDate.of(2012, 12, 20);
    assertThat(RandomLocalDate.aLocalDate().max(max).build()).isBeforeOrEqualTo(max);
  }

  @Test
  void max_MIN_VALUE() {
    LocalDate max = LocalDate.MIN;
    Assertions.assertThat(RandomLocalDate.aLocalDate().max(max).build()).isEqualTo(max);
  }

  @Test
  void max_less_than_min() {
    LocalDate min = LocalDate.of(2012, 12, 20);
    LocalDate max = min.minusDays(1);
    assertThatIllegalArgumentException()
        .isThrownBy(() -> RandomLocalDate.aLocalDate().min(min).max(max))
        .withMessage("Can't set max to %s, as it must not be less than min (%s)", max, min);
  }

  @Test
  void future() {
    assertThat(RandomLocalDate.aLocalDate().future().build()).isAfter(LocalDate.now());
  }

  @Test
  void past() {
    assertThat(RandomLocalDate.aLocalDate().past().build()).isBefore(LocalDate.now());
  }
}
