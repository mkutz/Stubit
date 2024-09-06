package org.stubit.random;

import java.security.SecureRandom;
import org.jspecify.annotations.NullMarked;

/** Generates random integers. */
@NullMarked
public class RandomNumber {

  private RandomNumber() {}

  /**
   * @param minInclusive the minimum value (inclusive)
   * @param maxInclusive the maximum value (inclusive)
   * @return a random integer between {@code minInclusive} and {@code maxInclusive}
   */
  public static int anIntBetween(int minInclusive, int maxInclusive) {
    return anInt().min(minInclusive).max(maxInclusive).build();
  }

  /**
   * @return a random integer between {@link Integer#MIN_VALUE} and -1
   */
  public static int aNegativeInt() {
    return anInt().negative().build();
  }

  /**
   * @return a random integer between 1 and {@link Integer#MAX_VALUE} - 1
   */
  public static int aPositiveInt() {
    return anInt().positive().build();
  }

  /**
   * @return a {@link RandomIntBuilder} with {@link RandomIntBuilder#minInclusive min} {@link
   *     Integer#MIN_VALUE} and {@link RandomIntBuilder#maxInclusive max} {@link Integer#MAX_VALUE}
   *     - 1.
   */
  public static RandomIntBuilder anInt() {
    return new RandomIntBuilder(Integer.MIN_VALUE, Integer.MAX_VALUE - 1);
  }

  /**
   * @param minInclusive the minimum value (inclusive)
   * @param maxInclusive the maximum value (inclusive)
   * @return a random long between {@code minInclusive} and {@code maxInclusive}
   */
  public static long aLongBetween(long minInclusive, long maxInclusive) {
    return aLong().min(minInclusive).max(maxInclusive).build();
  }

  /**
   * @return a random long between {@link Long#MIN_VALUE} and -1
   */
  public static long aNegativeLong() {
    return aLong().negative().build();
  }

  /**
   * @return a random long between 1 and {@link Long#MAX_VALUE} - 1
   */
  public static long aPositiveLong() {
    return aLong().positive().build();
  }

  /**
   * @return a {@link RandomLongBuilder} with {@link RandomLongBuilder#minInclusive min} {@link
   *     Long#MIN_VALUE} and {@link RandomLongBuilder#maxInclusive max} {@link Long#MAX_VALUE} - 1.
   */
  public static RandomLongBuilder aLong() {
    return new RandomLongBuilder(Long.MIN_VALUE, Long.MAX_VALUE - 1);
  }

  /** Builds a random integer within a specified range. */
  public static class RandomIntBuilder {

    private final SecureRandom secureRandom = new SecureRandom();
    private int minInclusive;
    private int maxInclusive;

    private RandomIntBuilder(int min, int max) {
      this.minInclusive = min;
      this.maxInclusive = max;
    }

    /**
     * Sets {@link #minInclusive} to 1 and {@link #maxInclusive} to {@link Integer#MAX_VALUE} - 1.
     *
     * @return this
     */
    public RandomIntBuilder positive() {
      return min(1).max(Integer.MAX_VALUE - 1);
    }

    /**
     * Sets {@link #minInclusive} to {@link Integer#MIN_VALUE} and max to -1.
     *
     * @return this
     */
    public RandomIntBuilder negative() {
      return min(Integer.MIN_VALUE).max(-1);
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
     * @param maxInclusive the maximum value (inclusive)
     * @return this
     * @throws IllegalArgumentException if {@code maxInclusive} is less than or equal to {@link
     *     #minInclusive} or if {@code maxInclusive} is equal to {@link Integer#MAX_VALUE}
     */
    public RandomIntBuilder max(int maxInclusive) {
      if (maxInclusive < minInclusive) {
        throw new IllegalArgumentException(
            "Can't set max to %d, as it must not be less than min (%d)"
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

  /** Builds a random long within a specified range. */
  public static class RandomLongBuilder {

    private final SecureRandom secureRandom = new SecureRandom();
    private long minInclusive;
    private long maxInclusive;

    private RandomLongBuilder(long min, long max) {
      this.minInclusive = min;
      this.maxInclusive = max;
    }

    /**
     * Sets {@link #minInclusive} to 1 and {@link #maxInclusive} to {@link Long#MAX_VALUE} - 1.
     *
     * @return this
     */
    public RandomLongBuilder positive() {
      return min(1).max(Long.MAX_VALUE - 1);
    }

    /**
     * Sets {@link #minInclusive} to {@link Long#MIN_VALUE} and max to -1.
     *
     * @return this
     */
    public RandomLongBuilder negative() {
      return min(Long.MIN_VALUE).max(-1);
    }

    /**
     * @param minInclusive the minimum value (inclusive)
     * @return this
     * @throws IllegalArgumentException if {@code minInclusive} is greater than or equal to {@link
     *     #maxInclusive}
     */
    public RandomLongBuilder min(long minInclusive) {
      this.minInclusive = minInclusive;
      if (minInclusive > maxInclusive) {
        throw new IllegalArgumentException(
            "Can't set min to %d, as it must not be greater than max (%d)"
                .formatted(minInclusive, maxInclusive));
      }
      return this;
    }

    /**
     * @param maxInclusive the maximum value (inclusive)
     * @return this
     * @throws IllegalArgumentException if {@code maxInclusive} is less than or equal to {@link
     *     #minInclusive} or if {@code maxInclusive} is equal to {@link Long#MAX_VALUE}
     */
    public RandomLongBuilder max(long maxInclusive) {
      if (maxInclusive < minInclusive) {
        throw new IllegalArgumentException(
            "Can't set max to %d, as it must not be less than min (%d)"
                .formatted(maxInclusive, minInclusive));
      }
      if (maxInclusive == Long.MAX_VALUE) {
        throw new IllegalArgumentException(
            "Can't set max to %d (Long.MAX_VALUE)".formatted(maxInclusive));
      }
      this.maxInclusive = maxInclusive;
      return this;
    }

    /**
     * @return a random long between {@link #minInclusive} and {@link #maxInclusive}
     */
    public long build() {
      return secureRandom.nextLong(minInclusive, maxInclusive + 1);
    }
  }
}
