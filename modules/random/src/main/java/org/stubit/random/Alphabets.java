package org.stubit.random;

import static java.util.stream.IntStream.rangeClosed;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public enum Alphabets implements Alphabet {
  LATIN(
      IntStream.concat(rangeClosed('a', 'z'), rangeClosed('A', 'Z'))
          .mapToObj(i -> (char) i)
          .toList(),
      IntStream.rangeClosed('0', '9').mapToObj(i -> (char) i).toList()),
  GERMAN(LATIN, List.of('ä', 'ö', 'ü', 'ß', 'Ä', 'Ö', 'Ü', 'ẞ')),
  FRENCH(
      LATIN,
      List.of(
          'à', 'â', 'æ', 'ç', 'è', 'é', 'ê', 'ë', 'î', 'ï', 'ô', 'œ', 'ù', 'û', 'ü', 'ÿ', 'À', 'Â',
          'Æ', 'Ç', 'È', 'É', 'Ê', 'Ë', 'Î', 'Ï', 'Ô', 'Œ', 'Ù', 'Û', 'Ü', 'Ÿ')),
  SPANISH(
      LATIN,
      IntStream.concat(rangeClosed('á', 'ñ'), rangeClosed('Á', 'Ñ'))
          .mapToObj(i -> (char) i)
          .toList());

  private final List<Character> letters;
  private final List<Character> digits;

  Alphabets(List<Character> letters, List<Character> digits) {
    this.letters = letters;
    this.digits = digits;
  }

  Alphabets(Alphabet base, List<Character> additionalLetters) {
    this(
        Stream.concat(base.letters().stream(), additionalLetters.stream()).toList(), base.digits());
  }

  @Override
  public List<Character> letters() {
    return letters;
  }

  @Override
  public List<Character> digits() {
    return digits;
  }

  @Override
  public int size() {
    return letters.size();
  }
}
