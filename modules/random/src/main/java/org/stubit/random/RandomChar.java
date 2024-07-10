package org.stubit.random;

import static org.stubit.random.RandomChoice.anyOf;

import java.util.Collection;

public class RandomChar {

  private RandomChar() {}

  public static char aLatinLetter() {
    return from(Alphabets.LATIN.letters());
  }

  public static char anArabicDigit() {
    return from(Alphabets.LATIN.digits());
  }

  public static char from(Collection<Character> characters) {
    return anyOf(characters);
  }
}
