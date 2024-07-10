package org.stubit.random;

import static org.stubit.random.RandomChoice.anyOf;

/**
 * Randomly select a character from a given {@link Alphabet}
 *
 * @see Alphabet
 * @see DigitSystem
 */
public class RandomChar {

  private RandomChar() {}

  /**
   * @return a randomly selected letter from {@link Alphabet#BASIC_LATIN}.
   */
  public static char aLatinLetter() {
    return aLetterFrom(Alphabet.BASIC_LATIN);
  }

  /**
   * @return a randomly selected digit from {@link DigitSystem#ARABIC}.
   */
  public static char anArabicDigit() {
    return aDigitFrom(DigitSystem.ARABIC);
  }

  /**
   * @param alphabet the {@link Alphabet} to select a character from
   * @return a randomly selected character from the provided {@link Alphabet}.
   */
  public static char aLetterFrom(Alphabet alphabet) {
    return anyOf(alphabet.letters());
  }

  /**
   * @param digitSystem the {@link DigitSystem} to select a character from
   * @return a randomly selected character from the provided {@link DigitSystem}.
   */
  public static char aDigitFrom(DigitSystem digitSystem) {
    return anyOf(digitSystem.digits());
  }
}
