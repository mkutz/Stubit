package org.stubit.random;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

/** Randomly selects an element from a collection of choices. */
public class RandomChoice {

  private static final SecureRandom random = new SecureRandom();

  private RandomChoice() {}

  /**
   * Randomly selects an element from the provided choices.
   *
   * @param choices an array of choices
   * @return a randomly selected element from the provided choices
   * @param <T> the type of the choices
   */
  @SafeVarargs
  public static <T> T anyOf(T... choices) {
    int size = choices.length;
    if (size == 0) {
      throw new IllegalArgumentException("No choices provided");
    }
    return choices[random.nextInt(size)];
  }

  /**
   * Randomly selects an element from the provided choices {@link Collection}.
   *
   * @param choices a {@link Collection} of choices
   * @return a randomly selected element from the provided choices
   * @param <T> the type of the choices
   */
  public static <T> T anyOf(Collection<T> choices) {
    int size = choices.size();
    if (size == 0) {
      throw new IllegalArgumentException("No choices provided");
    }
    return choices.stream()
        .skip(random.nextInt(size))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Should not happen"));
  }

  /**
   * Randomly selects an element from the provided choices {@link Map}.
   *
   * @param choices a {@link Map} of choices
   * @return a randomly selected {@link Entry} from the provided choices
   * @param <K> the type of the choices keys
   * @param <V> the type of the choices values
   */
  public static <K, V> Entry<K, V> anyOf(Map<K, V> choices) {
    return anyOf(choices.entrySet());
  }
}
