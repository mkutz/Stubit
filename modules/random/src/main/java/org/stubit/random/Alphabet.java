package org.stubit.random;

import java.util.List;

public interface Alphabet {

  List<Character> letters();

  List<Character> digits();

  int size();
}
