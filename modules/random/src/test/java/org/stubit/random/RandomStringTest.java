package org.stubit.random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.stubit.random.RandomInt.anIntBetween;

import java.util.stream.Stream;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

class RandomStringTest {

  @Test
  void aRandomCharacterFrom() {
    assertThat(RandomString.aRandomCharacterFrom(RandomString.UnicodeBlockRange.BASIC_LATIN))
        .matches(Character::isDefined)
        .matches(Character::isValidCodePoint);
  }

  @Test
  void aRandomCharacter() {
    assertThat(RandomString.aRandomCharacter())
        .matches(Character::isDefined)
        .matches(Character::isValidCodePoint)
        .isBetween(
            RandomString.UnicodeBlockRange.BASIC_LATIN.first,
            RandomString.UnicodeBlockRange.BASIC_LATIN.last);
  }

  @Test
  void aRandomString() {
    assertThat(RandomString.aRandomString(10))
        .hasSize(10)
        .matches(s -> s.chars().allMatch(Character::isValidCodePoint))
        .matches(
            s ->
                s.chars()
                    .allMatch(
                        c ->
                            c >= RandomString.UnicodeBlockRange.BASIC_LATIN.first
                                && c <= RandomString.UnicodeBlockRange.BASIC_LATIN.last));
  }

  @TestFactory
  Stream<DynamicTest> aString() {
    return Stream.of(RandomString.UnicodeBlockRange.values())
        .map(
            unicodeBlockRange -> {
              var length = anIntBetween(1, 20);
              var string =
                  RandomString.aString().length(length).unicodeBlock(unicodeBlockRange).build();
              return DynamicTest.dynamicTest(
                  "aString of unicodeBlock %s with length %d \"%s\""
                      .formatted(unicodeBlockRange.name(), length, string),
                  () ->
                      assertThat(string)
                          .hasSize(length)
                          .matches(s -> s.chars().allMatch(Character::isValidCodePoint)));
            });
  }
}
