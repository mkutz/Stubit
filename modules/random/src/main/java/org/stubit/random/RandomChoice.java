package org.stubit.random;

import java.security.SecureRandom;
import java.util.*;

/** Randomly select an element from a collection of choices. */
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
  public static <T> T chooseAnyFrom(T... choices) {
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
  public static <T> T chooseAnyFrom(Collection<T> choices) {
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
   * @param choices a {@link Collection} of choices
   * @return a new {@link Builder} with the provided choices
   * @param <T> the type of the choices
   */
  public static <T> Builder<T> from(Collection<T> choices) {
    return new Builder<>(choices);
  }

  /**
   * @param enumType the {@link Enum} type
   * @return a new {@link Builder} with the provided enumType's values as choices
   * @param <T> the type of the choices
   */
  public static <T extends Enum<?>> Builder<T> fromValuesOf(Class<? extends T> enumType) {
    return from(Arrays.asList(enumType.getEnumConstants()));
  }

  /**
   * @param choices an array of choices
   * @return a new {@link Builder} with the provided choices
   * @param <T> the type of the choices
   */
  @SafeVarargs
  public static <T> Builder<T> from(T... choices) {
    return from(Arrays.asList(choices));
  }

  /**
   * Randomly selects an element from the values of the provided choices {@link Enum} class.
   *
   * @param enumType the {@link Enum} type
   * @return a randomly selected {@link Enum} constant from the provided choices enumType
   * @param <T> the type of the choices
   */
  public static <T extends Enum<?>> T chooseAnyFromValuesOf(Class<? extends T> enumType) {
    return chooseAnyFrom(enumType.getEnumConstants());
  }

  /** Randomly selects an element from a collection of choices. */
  public static class Builder<T> {

    private static final SecureRandom random = new SecureRandom();

    private final List<T> choices;

    private Builder(Collection<T> choices) {
      if (choices.isEmpty()) {
        throw new IllegalArgumentException("No choices provided");
      }
      this.choices = new ArrayList<>(choices);
    }

    /**
     * Excludes the provided choices from the selection
     *
     * @param excluded the excluded choices
     * @return this
     * @throws IllegalArgumentException if no choices remain
     */
    @SafeVarargs
    public final Builder<T> save(T... excluded) {
      return save(Arrays.asList(excluded));
    }

    /**
     * Excludes the provided choices from the selection
     *
     * @param excluded the excluded choices
     * @return this
     * @throws IllegalArgumentException if no choices remain
     */
    public Builder<T> save(Collection<T> excluded) {
      this.choices.removeAll(excluded);
      if (choices.isEmpty()) {
        throw new IllegalArgumentException("No choices left");
      }
      return this;
    }

    /**
     * Returns and removes a randomly selected element from the {@link #choices}.
     *
     * @return a randomly selected element form the {@link #choices}
     */
    public T chooseAny() {
      return choices.get(random.nextInt(choices.size()));
    }
  }
}
