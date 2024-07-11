package org.stubit.random;

import static java.util.stream.IntStream.rangeClosed;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/** Represents an alphabet (a list of letters). */
public class Alphabet {

  /** The basic Latin alphabet as used in English. */
  public static final Alphabet BASIC_LATIN =
      new Alphabet(
          IntStream.concat(rangeClosed('a', 'z'), rangeClosed('A', 'Z'))
              .mapToObj(i -> (char) i)
              .toList());

  /** The German alphabet. */
  public static final Alphabet GERMAN =
      new Alphabet(BASIC_LATIN, List.of('ä', 'ö', 'ü', 'ß', 'Ä', 'Ö', 'Ü', 'ẞ'));

  /** The French alphabet. */
  public static final Alphabet FRENCH =
      new Alphabet(
          BASIC_LATIN,
          List.of(
              'à', 'â', 'æ', 'ç', 'è', 'é', 'ê', 'ë', 'î', 'ï', 'ô', 'œ', 'ù', 'û', 'ü', 'ÿ', 'À',
              'Â', 'Æ', 'Ç', 'È', 'É', 'Ê', 'Ë', 'Î', 'Ï', 'Ô', 'Œ', 'Ù', 'Û', 'Ü', 'Ÿ'));

  /** The basic Arabic alphabet. */
  public static final Alphabet BASIC_ARABIC =
      new Alphabet(
          List.of(
              'ا', 'ب', 'ج', 'د', 'ه', 'و', 'ز', 'ح', 'ط', 'ي', 'ك', 'ل', 'م', 'ن', 'س', 'ع', 'ف',
              'ص', 'ق', 'ر', 'ش', 'ت', 'ث', 'خ', 'ذ', 'ض', 'ظ', 'غ'));

  /** The Persian alphabet. */
  public static final Alphabet PERSIAN = new Alphabet(BASIC_ARABIC, List.of('پ', 'چ', 'ژ', 'گ'));

  private final List<Character> letters;

  /**
   * Creates a new alphabet from a list of characters.
   *
   * @param letters the characters in the alphabet
   */
  public Alphabet(List<Character> letters) {
    this.letters = letters;
  }

  /**
   * Creates a new alphabet by adding additional characters to an existing alphabet.
   *
   * @param base the base alphabet
   * @param additionalCharacters the additional characters
   */
  public Alphabet(Alphabet base, List<Character> additionalCharacters) {
    this(Stream.concat(base.letters.stream(), additionalCharacters.stream()).toList());
  }

  /**
   * @return the {@link List} of {@link Character}s in this alphabet
   */
  List<Character> letters() {
    return letters;
  }
}
