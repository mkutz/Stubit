package org.stubit.random;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.Map;

public class RandomChoice {

  private static final SecureRandom random = new SecureRandom();

  @SafeVarargs
  public static <T> T anyOf(T... choices) {
    int size = choices.length;
    if (size == 0) {
      throw new IllegalArgumentException("No choices provided");
    }
    return choices[random.nextInt(size)];
  }

  public static <T> T anyOf(Collection<T> collection) {
    int size = collection.size();
    if (size == 0) {
      throw new IllegalArgumentException("No choices provided");
    }
    return collection.stream()
        .skip(random.nextInt(size))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Should not happen"));
  }

  public static <K, V> Map.Entry<K, V> anyOf(Map<K, V> map) {
    return anyOf(map.entrySet());
  }
}
