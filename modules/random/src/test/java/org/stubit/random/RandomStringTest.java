package org.stubit.random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.stubit.random.RandomNumber.anIntBetween;

import org.junit.jupiter.api.Test;

class RandomStringTest {

  @Test
  void aLetterFrom() {
    assertThat(RandomString.aLetterFrom(Alphabet.GERMAN)).matches("[a-zA-ZöäüßÄÖÜẞ]");
  }

  @Test
  void lettersFrom() {
    int number = anIntBetween(1, 10);
    assertThat(RandomString.lettersFrom(number, Alphabet.GERMAN))
        .matches("[a-zA-ZöäüßÄÖÜẞ]+")
        .hasSize(number);
  }

  @Test
  void lettersFrom_zero() {
    assertThat(RandomString.lettersFrom(0, Alphabet.FRENCH)).isEmpty();
  }

  @Test
  void lettersFrom_negative() {
    assertThatThrownBy(() -> RandomString.lettersFrom(-1, Alphabet.FRENCH))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void aLatinLetter() {
    assertThat(RandomString.aLatinLetter()).matches("[a-zA-Z]");
  }

  @Test
  void latinLetters() {
    int number = anIntBetween(1, 10);
    assertThat(RandomString.latinLetters(number)).matches("[a-zA-Z]+").hasSize(number);
  }

  @Test
  void latinLetters_zero() {
    assertThat(RandomString.latinLetters(0)).isEmpty();
  }

  @Test
  void latinLetters_negative() {
    assertThatThrownBy(() -> RandomString.latinLetters(-1))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void anArabicDigit() {
    assertThat(RandomString.anArabicDigit()).matches("[0-9]");
  }

  @Test
  void arabicDigits() {
    int number = anIntBetween(1, 10);
    assertThat(RandomString.arabicDigits(number)).matches("[0-9]+").hasSize(number);
  }

  @Test
  void arabicDigits_zero() {
    assertThat(RandomString.arabicDigits(0)).isEmpty();
  }

  @Test
  void arabicDigits_negative() {
    assertThatThrownBy(() -> RandomString.arabicDigits(-1))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void aStringStartingWith() {
    var string = RandomString.aStringStartingWith(RandomString.aLatinLetter()).build();

    assertThat(string).matches("[a-zA-Z]");
  }

  @Test
  void followedBy() {
    var string =
        RandomString.aStringStartingWith(RandomString.aLatinLetter())
            .followedBy(RandomString.anArabicDigit())
            .build();

    assertThat(string).matches("[a-zA-Z][0-9]");
  }
}
