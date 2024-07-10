package org.stubit.random;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AlphabetsTest {

  @Test
  void characterStream() {
    assertEquals(Alphabets.LATIN.letters().size(), 52);
    assertEquals(Alphabets.GERMAN.letters().size(), 60);
    assertEquals(Alphabets.FRENCH.letters().size(), 78);
    assertEquals(Alphabets.SPANISH.letters().size(), 68);
  }
}
