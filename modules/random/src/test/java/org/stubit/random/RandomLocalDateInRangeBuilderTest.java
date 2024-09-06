package org.stubit.random;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RandomLocalDateInRangeBuilderTest {

  @Test
  void aLocalDateInRange() {
    assertThat(RandomLocalDate.aLocalDateInRange().build()).isBetween(LocalDate.MIN, LocalDate.MAX);
  }

  @Test
  void after() {
    LocalDate min = LocalDate.of(2012, 12, 20);
    assertThat(RandomLocalDate.aLocalDateInRange().after(min).build()).isAfterOrEqualTo(min);
  }

  @Test
  void after_before_VALUE() {
    LocalDate min = LocalDate.MAX;
    assertThat(RandomLocalDate.aLocalDateInRange().after(min).build()).isEqualTo(min);
  }

  @Test
  void after_greater_than_before() {
    LocalDate max = LocalDate.of(2012, 12, 20);
    LocalDate min = max.plusDays(1);
    assertThatIllegalArgumentException()
        .isThrownBy(() -> RandomLocalDate.aLocalDateInRange().before(max).after(min))
        .withMessage("Can't set after to %s, as it must not be greater than before (%s)", min, max);
  }

  @Test
  void before() {
    LocalDate max = LocalDate.of(2012, 12, 20);
    assertThat(RandomLocalDate.aLocalDateInRange().before(max).build()).isBeforeOrEqualTo(max);
  }

  @Test
  void before_after_VALUE() {
    LocalDate max = LocalDate.MIN;
    Assertions.assertThat(RandomLocalDate.aLocalDateInRange().before(max).build()).isEqualTo(max);
  }

  @Test
  void before_less_than_after() {
    LocalDate min = LocalDate.of(2012, 12, 20);
    LocalDate max = min.minusDays(1);
    assertThatIllegalArgumentException()
        .isThrownBy(() -> RandomLocalDate.aLocalDateInRange().after(min).before(max))
        .withMessage("Can't set before to %s, as it must not be less than after (%s)", max, min);
  }

  @Test
  void future() {
    assertThat(RandomLocalDate.aLocalDateInRange().future().build()).isAfter(LocalDate.now());
  }

  @Test
  void past() {
    assertThat(RandomLocalDate.aLocalDateInRange().past().build()).isBefore(LocalDate.now());
  }
}
