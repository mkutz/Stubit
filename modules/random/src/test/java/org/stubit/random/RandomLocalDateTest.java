package org.stubit.random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class RandomLocalDateTest {

  @Test
  void aLocalDateBetween_min_equal_to_max() {
    LocalDate min = LocalDate.now();
    assertThat(RandomLocalDate.aLocalDateBetween(min, min)).isEqualTo(min);
  }

  @Test
  void aFutureLocalDate() {
    assertThat(RandomLocalDate.aFutureLocalDate()).isAfter(LocalDate.now());
  }

  @Test
  void aPastLocalDate() {
    assertThat(RandomLocalDate.aPastLocalDate()).isBefore(LocalDate.now());
  }
}
