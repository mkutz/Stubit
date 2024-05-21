package org.stubit.random;

import java.security.SecureRandom;
import java.util.*;

/** Randomly selects an element from a collection of choices. */
public class RandomChoiceBuilder<T> {

  private static final SecureRandom random = new SecureRandom();

  private final List<T> choices;

  private RandomChoiceBuilder(Collection<T> choices) {
    if (choices.isEmpty()) {
      throw new IllegalArgumentException("No choices provided");
    }
    this.choices = new ArrayList<>(choices);
  }

  /**
   * @param choices a {@link Collection} of choices
   * @return a new {@link RandomChoiceBuilder} with the provided choices
   * @param <T> the type of the choices
   */
  public static <T> RandomChoiceBuilder<T> from(Collection<T> choices) {
    return new RandomChoiceBuilder<>(choices);
  }

  /**
   * @param enumType the {@link Enum} type
   * @return a new {@link RandomChoiceBuilder} with the provided enumType's values as choices
   * @param <T> the type of the choices
   */
  public static <T extends Enum<?>> RandomChoiceBuilder<T> fromValuesOf(
      Class<? extends T> enumType) {
    return from(Arrays.asList(enumType.getEnumConstants()));
  }

  /**
   * @param choices an array of choices
   * @return a new {@link RandomChoiceBuilder} with the provided choices
   * @param <T> the type of the choices
   */
  @SafeVarargs
  public static <T> RandomChoiceBuilder<T> from(T... choices) {
    return from(Arrays.asList(choices));
  }

  /**
   * Excludes the provided choices from the selection
   *
   * @param excluded the excluded choices
   * @return this
   * @throws IllegalArgumentException if no choices remain
   */
  @SafeVarargs
  public final RandomChoiceBuilder<T> save(T... excluded) {
    return save(Arrays.asList(excluded));
  }

  /**
   * Excludes the provided choices from the selection
   *
   * @param excluded the excluded choices
   * @return this
   * @throws IllegalArgumentException if no choices remain
   */
  public RandomChoiceBuilder<T> save(Collection<T> excluded) {
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
