package org.stubit.random;

import java.security.SecureRandom;

/** Builds a random integer within a specified range. */
public class RandomIntBuilder {

  private final SecureRandom secureRandom = new SecureRandom();
  private int min;
  private int max;

  private RandomIntBuilder(int min, int max) {
    this.min = min;
    this.max = max;
  }

  /**
   * @return a random integer between 1 and {@link Integer#MAX_VALUE}
   */
  public static RandomIntBuilder aPositiveInt() {
    return new RandomIntBuilder(1, Integer.MAX_VALUE);
  }

  /**
   * @return a random integer between {@link Integer#MIN_VALUE} and -1
   */
  public static RandomIntBuilder aNegativeInt() {
    return new RandomIntBuilder(Integer.MIN_VALUE, -1);
  }

  /**
   * @return a random integer between {@link Integer#MIN_VALUE} and {@link Integer#MAX_VALUE}
   */
  public static RandomIntBuilder anInt() {
    return new RandomIntBuilder(Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  /**
   * @param min the minimum value (inclusive)
   * @return this
   */
  public RandomIntBuilder min(int min) {
    this.min = min;
    if (max <= min) {
      max = min + 1;
    }
    return this;
  }

  /**
   * @param max the maximum value (exclusive)
   * @return this
   */
  public RandomIntBuilder max(int max) {
    this.max = max;
    if (min >= max) {
      min = max - 1;
    }
    return this;
  }

  /**
   * @return a random integer between {@link #min} (inclusive) and {@link #max} (exclusive)
   */
  public int build() {
    return secureRandom.nextInt(min, max);
  }
}
