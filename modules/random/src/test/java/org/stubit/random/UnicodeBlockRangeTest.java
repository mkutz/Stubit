package org.stubit.random;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

class UnicodeBlockRangeTest {

  @TestFactory
  Stream<DynamicTest> randomChar() {
    return Stream.of(RandomString.UnicodeBlockRange.values())
        .map(
            range -> {
              char randomChar = range.randomChar();
              return DynamicTest.dynamicTest(
                  "%s char '%c'".formatted(range.name(), randomChar),
                  () -> assertThat(randomChar).matches(Character::isValidCodePoint));
            });
  }

  @TestFactory
  Stream<DynamicTest> first() {
    return Stream.of(RandomString.UnicodeBlockRange.values())
        .map(
            range -> {
              char randomChar = range.first;
              return DynamicTest.dynamicTest(
                  "%s char '%c'".formatted(range.name(), randomChar),
                  () -> assertThat(randomChar).matches(Character::isValidCodePoint));
            });
  }

  @TestFactory
  Stream<DynamicTest> last() {
    return Stream.of(RandomString.UnicodeBlockRange.values())
        .map(
            range -> {
              char randomChar = range.last;
              return DynamicTest.dynamicTest(
                  "%s char '%c'".formatted(range.name(), randomChar),
                  () -> assertThat(randomChar).matches(Character::isValidCodePoint));
            });
  }
}
