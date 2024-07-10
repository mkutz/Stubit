package org.stubit.random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.stubit.random.RandomChar.aLatinLetter;
import static org.stubit.random.RandomChar.anArabicDigit;
import static org.stubit.random.RandomString.aStringStartingWith;

import org.junit.jupiter.api.Test;

class RandomStringTest {

  @Test
  void followedBy_mixed() {
    var string =
        aStringStartingWith(aLatinLetter())
            .followedBy('-')
            .followedBy(anArabicDigit())
            .followedBy("üäößø")
            .build();

    System.out.println(string);

    assertThat(string).matches("[a-zA-Z]-[0-9]üäößø");
  }
}
