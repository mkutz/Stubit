package org.stubit.random;

import java.security.SecureRandom;
import java.time.Duration;
import org.jspecify.annotations.NullMarked;

/** Generates random {@link Duration}s. */
@NullMarked
public class RandomDuration {

  private RandomDuration() {}

  /**
   * @param minInclusive the minimum value (inclusive)
   * @param maxInclusive the maximum value (inclusive)
   * @return a random {@link Duration} between {@code minInclusive} and {@code maxInclusive}
   */
  public static Duration aDurationBetween(Duration minInclusive, Duration maxInclusive) {
    return aDuration().min(minInclusive).max(maxInclusive).build();
  }

  /**
   * @return a {@link Duration} between {@link Duration#ZERO} and {@link Long#MAX_VALUE} - 1 seconds
   */
  public static RandomDurationBuilder aDuration() {
    return new RandomDurationBuilder(Duration.ZERO, Duration.ofSeconds(Long.MAX_VALUE - 1));
  }

  /** Builds a random {@link Duration} within a specified range. */
  public static class RandomDurationBuilder {

    private final SecureRandom secureRandom = new SecureRandom();
    private Duration minInclusive;
    private Duration maxInclusive;

    private RandomDurationBuilder(Duration min, Duration max) {
      this.minInclusive = min;
      this.maxInclusive = max;
    }

    /**
     * @param minInclusive the minimum value (inclusive)
     * @return this
     * @throws IllegalArgumentException if {@code minInclusive} is greater than or equal to {@link
     *     #maxInclusive}
     */
    public RandomDurationBuilder min(Duration minInclusive) {
      this.minInclusive = minInclusive;
      if (minInclusive.compareTo(maxInclusive) > 0) {
        throw new IllegalArgumentException(
            "Can't set min to %s, as it must not be greater than max (%s)"
                .formatted(minInclusive, maxInclusive));
      }
      return this;
    }

    /**
     * @param maxInclusive the maximum value (inclusive)
     * @return this
     * @throws IllegalArgumentException if {@code maxInclusive} is less than or equal to {@link
     *     #minInclusive}
     */
    public RandomDurationBuilder max(Duration maxInclusive) {
      if (minInclusive.compareTo(maxInclusive) > 0) {
        throw new IllegalArgumentException(
            "Can't set max to %s, as it must not be less than min (%s)"
                .formatted(maxInclusive, minInclusive));
      }
      this.maxInclusive = maxInclusive;
      return this;
    }

    /**
     * @return a random {@link Duration} between {@link #minInclusive} and {@link #maxInclusive}
     */
    public Duration build() {
      return Duration.ofSeconds(
          secureRandom.nextLong(minInclusive.toSeconds(), maxInclusive.toSeconds() + 1));
    }
  }
}
