package org.stubit.random;

import java.math.BigDecimal;
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
  public static RandomNumberBuilder<Integer> anInt() {
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
  public static RandomNumberBuilder<Long> aLong() {
    return new RandomLongBuilder(Long.MIN_VALUE, Long.MAX_VALUE - 1);
  }

  /**
   * @param minInclusive the minimum value (inclusive)
   * @param maxInclusive the maximum value (inclusive)
   * @return a random float between {@code minInclusive} and {@code maxInclusive}
   */
  public static float aFloatBetween(float minInclusive, float maxInclusive) {
    return aFloat().min(minInclusive).max(maxInclusive).build();
  }

  /**
   * @return a random float between -{@link Float#MAX_VALUE} and {@link Float#MIN_VALUE}
   */
  public static float aNegativeFloat() {
    return aFloat().negative().build();
  }

  /**
   * @return a random float between {@link Float#MIN_VALUE} and {@link Float#MAX_VALUE}
   */
  public static float aPositiveFloat() {
    return aFloat().positive().build();
  }

  /**
   * @return a {@link RandomFloatBuilder} with {@link RandomFloatBuilder#minInclusive min} -{@link
   *     Float#MAX_VALUE} and {@link RandomFloatBuilder#maxInclusive max} {@link Float#MAX_VALUE}.
   */
  public static RandomFloatBuilder aFloat() {
    return new RandomFloatBuilder(-Float.MAX_VALUE, Float.MAX_VALUE);
  }

  /**
   * Interface for any random number builder.
   *
   * @param <N> the type of the number to build
   */
  public interface RandomNumberBuilder<N> {

    /**
     * Sets {@link #min} to 1 and {@link #max} to the maximum of N.
     *
     * @return this
     */
    RandomNumberBuilder<N> positive();

    /**
     * Sets {@link #min} to the minimum of N and max to -1.
     *
     * @return this
     */
    RandomNumberBuilder<N> negative();

    /**
     * @param minInclusive the minimum value (inclusive)
     * @return this
     * @throws IllegalArgumentException if {@code minInclusive} is greater than or equal to {@link
     *     #max}
     */
    RandomNumberBuilder<N> min(N minInclusive);

    /**
     * @param maxInclusive the maximum value (inclusive)
     * @return this
     * @throws IllegalArgumentException if {@code maxInclusive} is less than or equal to {@link
     *     #min} or if {@code maxInclusive} is equal to the maximum of N
     */
    RandomNumberBuilder<N> max(N maxInclusive);

    /**
     * @return a random integer between {@link #min} and {@link #max}
     */
    N build();
  }

  /** {@link RandomNumberBuilder} implementation for {@link Integer}s */
  public static class RandomIntBuilder implements RandomNumberBuilder<Integer> {

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

    public RandomIntBuilder min(Integer minInclusive) {
      this.minInclusive = minInclusive;
      if (minInclusive > maxInclusive) {
        throw new IllegalArgumentException(
            "Can't set min to %d, as it must not be greater than max (%d)"
                .formatted(minInclusive, maxInclusive));
      }
      return this;
    }

    public RandomIntBuilder max(Integer maxInclusive) {
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

    public Integer build() {
      return secureRandom.nextInt(minInclusive, maxInclusive + 1);
    }
  }

  /** {@link RandomNumberBuilder} implementation for {@link Long}s */
  public static class RandomLongBuilder implements RandomNumberBuilder<Long> {

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
      return min(1L).max(Long.MAX_VALUE - 1);
    }

    /**
     * Sets {@link #minInclusive} to {@link Long#MIN_VALUE} and max to -1.
     *
     * @return this
     */
    public RandomLongBuilder negative() {
      return min(Long.MIN_VALUE).max(-1L);
    }

    public RandomLongBuilder min(Long minInclusive) {
      this.minInclusive = minInclusive;
      if (minInclusive > maxInclusive) {
        throw new IllegalArgumentException(
            "Can't set min to %d, as it must not be greater than max (%d)"
                .formatted(minInclusive, maxInclusive));
      }
      return this;
    }

    public RandomLongBuilder max(Long maxInclusive) {
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

    public Long build() {
      return secureRandom.nextLong(minInclusive, maxInclusive + 1);
    }
  }

  /** {@link RandomNumberBuilder} implementation for {@link BigDecimal}s */
  public static class RandomFloatBuilder implements RandomNumberBuilder<Float> {

    private final SecureRandom secureRandom = new SecureRandom();
    private BigDecimal minInclusive;
    private BigDecimal maxInclusive;

    private RandomFloatBuilder(float min, float max) {
      this.minInclusive = BigDecimal.valueOf(min);
      this.maxInclusive = BigDecimal.valueOf(max);
    }

    @Override
    public RandomFloatBuilder positive() {
      return min(Float.MIN_VALUE).max(Float.MAX_VALUE - Float.MIN_VALUE);
    }

    @Override
    public RandomFloatBuilder negative() {
      return min(-Float.MAX_VALUE).max(Float.MIN_VALUE);
    }

    @Override
    public RandomFloatBuilder min(Float minInclusiveFloat) {
      var minInclusive = BigDecimal.valueOf(minInclusiveFloat);
      if (minInclusive.compareTo(maxInclusive) > 0) {
        throw new IllegalArgumentException(
            "Can't set min to %f, as it must not be greater than max (%f)"
                .formatted(minInclusive, maxInclusive));
      }
      this.minInclusive = minInclusive;
      return this;
    }

    @Override
    public RandomFloatBuilder max(Float maxInclusiveFloat) {
      var maxInclusive = BigDecimal.valueOf(maxInclusiveFloat);
      if (maxInclusive.compareTo(minInclusive) < 0) {
        throw new IllegalArgumentException(
            "Can't set max to %f, as it must not be less than min (%f)"
                .formatted(maxInclusive, minInclusive));
      }
      this.maxInclusive = maxInclusive;
      return this;
    }

    @Override
    public Float build() {
      return secureRandom.nextFloat(
          minInclusive.floatValue(),
          maxInclusive.add(BigDecimal.valueOf(Float.MIN_NORMAL)).floatValue());
    }
  }
}
