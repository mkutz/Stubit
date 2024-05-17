package org.stubit.random;

import java.security.SecureRandom;

public class RandomIntBuilder {

  private final SecureRandom secureRandom = new SecureRandom();
  private int min;
  private int max;

  private RandomIntBuilder(int min, int max) {
    this.min = min;
    this.max = max;
  }

  public static RandomIntBuilder aPositiveInt() {
    return new RandomIntBuilder(1, Integer.MAX_VALUE);
  }

  public static RandomIntBuilder aNegativeInt() {
    return new RandomIntBuilder(Integer.MIN_VALUE, -1);
  }

  public static RandomIntBuilder anInt() {
    return new RandomIntBuilder(Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  public RandomIntBuilder min(int min) {
    this.min = min;
    if (max <= min) {
      max = min + 1;
    }
    return this;
  }

  public RandomIntBuilder max(int max) {
    this.max = max;
    if (min >= max) {
      min = max - 1;
    }
    return this;
  }

  public int build() {
    return secureRandom.nextInt(min, max);
  }
}
