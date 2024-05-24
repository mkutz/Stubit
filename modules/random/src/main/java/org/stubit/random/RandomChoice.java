package org.stubit.random;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/** Randomly select an element from a collection of choices. */
public class RandomChoice {

  private RandomChoice() {}

  /**
   * Randomly selects an element from the provided choices {@link Collection}.
   *
   * @param choices a {@link Collection} of choices
   * @param <T> the type of the choices
   * @return a randomly selected element from the provided choices
   */
  public static <T> T anyOf(Collection<T> choices) {
    return aChoiceFrom(choices).build();
  }

  /**
   * Randomly selects an element from the provided choices.
   *
   * @param choices an array of choices
   * @param <T> the type of the choices
   * @return a randomly selected element from the provided choices
   */
  @SafeVarargs
  public static <T> T anyOf(T... choices) {
    return aChoiceFrom(choices).build();
  }

  /**
   * Randomly selects an element from the values of the provided choices {@link Enum} class.
   *
   * @param enumType the {@link Enum} type
   * @param <T> the type of the choices
   * @return a randomly selected {@link Enum} constant from the provided choices enumType
   */
  public static <T extends Enum<?>> T any(Class<? extends T> enumType) {
    return anyOf(enumType.getEnumConstants());
  }

  /**
   * @param choices a {@link Collection} of choices
   * @param <T> the type of the choices
   * @return a new {@link Builder} with the provided choices
   */
  public static <T> Builder<T> aChoiceFrom(Collection<T> choices) {
    return new Builder<>(choices);
  }

  /**
   * @param choices an array of choices
   * @param <T> the type of the choices
   * @return a new {@link Builder} with the provided choices
   */
  @SafeVarargs
  public static <T> Builder<T> aChoiceFrom(T... choices) {
    return aChoiceFrom(Arrays.asList(choices));
  }

  /**
   * @param enumType the {@link Enum} type
   * @param <T> the type of the choices
   * @return a new {@link Builder} with the provided enumType's values as choices
   */
  public static <T extends Enum<?>> Builder<T> aChoiceFromValuesOf(Class<? extends T> enumType) {
    return aChoiceFrom(Arrays.asList(enumType.getEnumConstants()));
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
     * Add the provided additional choices to the selection
     *
     * @param additionalChoices the additional choices
     */
    public Builder<T> and(Collection<T> additionalChoices) {
      this.choices.addAll(additionalChoices);
      return this;
    }

    /**
     * Add the provided additional choices to the selection
     *
     * @param additionalChoices the additional choices
     */
    @SafeVarargs
    public final Builder<T> and(T... additionalChoices) {
      return and(List.of(additionalChoices));
    }

    /**
     * Excludes the provided choices from the selection
     *
     * @param excluded the excluded choices
     * @return this
     * @throws IllegalArgumentException if no choices remain
     */
    public Builder<T> butNot(Collection<T> excluded) {
      this.choices.removeAll(excluded);
      if (choices.isEmpty()) {
        throw new IllegalArgumentException("No choices left");
      }
      return this;
    }

    /**
     * Excludes the provided choices from the selection
     *
     * @param excluded the excluded choices
     * @return this
     * @throws IllegalArgumentException if no choices remain
     */
    @SafeVarargs
    public final Builder<T> butNot(T... excluded) {
      return butNot(Arrays.asList(excluded));
    }

    /**
     * Returns a randomly selected element from the {@link #choices}.
     *
     * @return a randomly selected element form the {@link #choices}
     */
    public T build() {
      return choices.get(random.nextInt(choices.size()));
    }
  }
}
