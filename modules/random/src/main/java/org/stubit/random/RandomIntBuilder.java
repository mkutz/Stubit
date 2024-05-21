package org.stubit.random;

import java.security.SecureRandom;

/** Builds a random integer within a specified range. */
public class RandomIntBuilder {

  private final SecureRandom secureRandom = new SecureRandom();
  private int minInclusive;
  private int maxInclusive;

  private RandomIntBuilder(int min, int max) {
    this.minInclusive = min;
    this.maxInclusive = max;
  }

  /**
   * @return a {@link RandomIntBuilder} with min {@link Integer#MIN_VALUE} and max {@link
   *     Integer#MAX_VALUE} - 1.
   */
  public static RandomIntBuilder anInt() {
    return new RandomIntBuilder(Integer.MIN_VALUE, Integer.MAX_VALUE - 1);
  }

  /**
   * @return a {@link RandomIntBuilder} with min 1 and max {@link Integer#MAX_VALUE} - 1.
   */
  public static RandomIntBuilder aPositiveInt() {
    return anInt().min(1);
  }

  /**
   * @return a {@link RandomIntBuilder} with min {@link Integer#MIN_VALUE} and max -1
   */
  public static RandomIntBuilder aNegativeInt() {
    return anInt().max(-1);
  }

  /**
   * @param minInclusive the minimum value (inclusive)
   * @return this
   * @throws IllegalArgumentException if {@code minInclusive} is greater than or equal to {@link
   *     #maxInclusive}
   */
  public RandomIntBuilder min(int minInclusive) {
    this.minInclusive = minInclusive;
    if (minInclusive > maxInclusive) {
      throw new IllegalArgumentException(
          "Can't set min to %d, as it must not be greater than max (%d)"
              .formatted(minInclusive, maxInclusive));
    }
    return this;
  }

  /**
   * @param maxInclusive the maximum value (exclusive)
   * @return this
   * @throws IllegalArgumentException if {@code maxInclusive} is less than or equal to {@link
   *     #minInclusive} or if {@code maxInclusive} is equal to {@link Integer#MAX_VALUE}
   */
  public RandomIntBuilder max(int maxInclusive) {
    if (maxInclusive < minInclusive) {
      throw new IllegalArgumentException(
          "Can't set max to %d, as it must not less than min (%d)"
              .formatted(maxInclusive, minInclusive));
    }
    if (maxInclusive == Integer.MAX_VALUE) {
      throw new IllegalArgumentException(
          "Can't set max to %d (Integer.MAX_VALUE)".formatted(maxInclusive));
    }
    this.maxInclusive = maxInclusive;
    return this;
  }

  /**
   * @return a random integer between {@link #minInclusive} and {@link #maxInclusive}
   */
  public int build() {
    return secureRandom.nextInt(minInclusive, maxInclusive + 1);
  }
}
