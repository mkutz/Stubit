package org.stubit.random;

import static org.stubit.random.RandomChoice.anyOf;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;

/** Generates random strings. */
@NullMarked
public class RandomString {

  private RandomString() {}

  /**
   * @param string the character the string should start with
   * @return a new {@link Builder}
   */
  public static Builder aStringStartingWith(String string) {
    return new Builder(string);
  }

  /**
   * @return a randomly selected letter from {@link Alphabet#BASIC_LATIN}.
   */
  public static String aLatinLetter() {
    return aLetterFrom(Alphabet.BASIC_LATIN);
  }

  /**
   * @param number the number of letters to generate
   * @return a number of letters from {@link Alphabet#BASIC_LATIN}.
   */
  public static String latinLetters(int number) {
    return lettersFrom(number, Alphabet.BASIC_LATIN);
  }

  /**
   * @return a randomly selected digit from {@link DigitSystem#ARABIC}.
   */
  public static String anArabicDigit() {
    return aDigitFrom(DigitSystem.ARABIC);
  }

  /**
   * @param number the number of digits to generate
   * @return a number of digits from {@link DigitSystem#ARABIC}.
   */
  public static @NonNull String arabicDigits(int number) {
    return digitsFrom(number, DigitSystem.ARABIC);
  }

  /**
   * @param alphabet the {@link Alphabet} to select a character from
   * @return a randomly selected character from the provided {@link Alphabet}.
   */
  public static String aLetterFrom(Alphabet alphabet) {
    return anyOf(alphabet.letters()).toString();
  }

  /**
   * @param number the number of letters to generate
   * @param alphabet the {@link Alphabet} to select a character from
   * @return a randomly selected character from the provided {@link Alphabet}.
   */
  public static String lettersFrom(int number, Alphabet alphabet) {
    return Stream.generate(() -> aLetterFrom(alphabet)).limit(number).collect(Collectors.joining());
  }

  /**
   * @param digitSystem the {@link DigitSystem} to select a character from
   * @return a randomly selected character from the provided {@link DigitSystem}.
   */
  public static String aDigitFrom(DigitSystem digitSystem) {
    return anyOf(digitSystem.digits()).toString();
  }

  /**
   * @param number the number of digits to generate
   * @param digitSystem the {@link DigitSystem} to select the digits from
   * @return a number of randomly selected digits from the provided {@link DigitSystem}.
   */
  public static String digitsFrom(int number, DigitSystem digitSystem) {
    return Stream.generate(() -> aDigitFrom(digitSystem))
        .limit(number)
        .collect(Collectors.joining());
  }

  /** Builds a random string. */
  public static class Builder {

    private final StringBuilder stringBuilder;

    private Builder(String startString) {
      stringBuilder = new StringBuilder(startString);
    }

    /**
     * @param string the string to append
     * @return this
     */
    public Builder followedBy(String string) {
      stringBuilder.append(string);
      return this;
    }

    /**
     * @return a {@link String} with the appended characters
     */
    public String build() {
      return stringBuilder.toString();
    }
  }
}
