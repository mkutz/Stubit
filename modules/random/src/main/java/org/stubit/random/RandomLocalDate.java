package org.stubit.random;

import java.security.SecureRandom;
import java.time.LocalDate;
import org.jspecify.annotations.NullMarked;

/** Generates random {@link LocalDate}s. */
@NullMarked
public class RandomLocalDate {

  private RandomLocalDate() {}

  /**
   * @param minInclusive the minimum value (inclusive)
   * @param maxInclusive the maximum value (inclusive)
   * @return a random {@link LocalDate} between {@code minInclusive} and {@code maxInclusive}
   */
  public static LocalDate aLocalDateBetween(LocalDate minInclusive, LocalDate maxInclusive) {
    return aLocalDate().min(minInclusive).max(maxInclusive).build();
  }

  /**
   * @return a random {@link LocalDate} between {@link LocalDate#MIN} and yesterday
   */
  public static LocalDate aPastLocalDate() {
    return aLocalDate().past().build();
  }

  /**
   * @return a random {@link LocalDate} between tomorrow and {@link LocalDate#MAX}
   */
  public static LocalDate aFutureLocalDate() {
    return aLocalDate().future().build();
  }

  /**
   * @return a {@link LocalDateBuilder} to configure the random {@link LocalDate}
   */
  public static LocalDateBuilder aLocalDate() {
    return new LocalDateBuilder(LocalDate.MIN, LocalDate.MAX);
  }

  /** Builds a random {@link LocalDate} within a specified range. */
  public static class LocalDateBuilder {

    private final SecureRandom secureRandom = new SecureRandom();
    private LocalDate minInclusive;
    private LocalDate maxInclusive;

    private LocalDateBuilder(LocalDate min, LocalDate max) {
      this.minInclusive = min;
      this.maxInclusive = max;
    }

    /**
     * Sets {@link #minInclusive} to {@link LocalDate#MIN} and {@link #maxInclusive} to yesterday.
     *
     * @return this
     */
    public LocalDateBuilder past() {
      return min(LocalDate.MIN).max(LocalDate.now().minusDays(1));
    }

    /**
     * Sets {@link #minInclusive} to tomorrow and {@link #maxInclusive} to {@link LocalDate#MAX}.
     *
     * @return this
     */
    public LocalDateBuilder future() {
      return min(LocalDate.now().plusDays(1)).max(LocalDate.MAX);
    }

    /**
     * @param minInclusive the minimum value (inclusive)
     * @return this
     * @throws IllegalArgumentException if {@code minInclusive} is after {@link #maxInclusive}
     */
    public LocalDateBuilder min(LocalDate minInclusive) {
      if (minInclusive.isAfter(maxInclusive)) {
        throw new IllegalArgumentException(
            "Can't set min to %s, as it must not be greater than max (%s)"
                .formatted(minInclusive, maxInclusive));
      }
      this.minInclusive = minInclusive;
      return this;
    }

    /**
     * @param maxInclusive the maximum value (inclusive)
     * @return this
     * @throws IllegalArgumentException if {@code maxInclusive} is before {@link #minInclusive}
     */
    public LocalDateBuilder max(LocalDate maxInclusive) {
      if (maxInclusive.isBefore(minInclusive)) {
        throw new IllegalArgumentException(
            "Can't set max to %s, as it must not be less than min (%s)"
                .formatted(maxInclusive, minInclusive));
      }
      this.maxInclusive = maxInclusive;
      return this;
    }

    // TODO in year
    // TODO weekday (Monday, Tuesday, etc.)
    // TODO weekend (Saturday or Sunday)
    // TODO summer, winter, spring, fall

    /**
     * @return a random long between {@link #minInclusive} and {@link #maxInclusive}
     */
    public LocalDate build() {
      long minEpochDay = minInclusive.toEpochDay();
      long maxEpochDay = maxInclusive.toEpochDay() + 1;
      long randomEpochDay = minEpochDay + secureRandom.nextLong(0, maxEpochDay - minEpochDay);
      return LocalDate.ofEpochDay(randomEpochDay);
    }
  }
}
