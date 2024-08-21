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
    return aLocalDate().after(minInclusive).before(maxInclusive).build();
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
    private LocalDate after;
    private LocalDate before;

    private LocalDateBuilder(LocalDate min, LocalDate max) {
      this.after = min;
      this.before = max;
    }

    /**
     * Sets {@link #after} to {@link LocalDate#MIN} and {@link #before} to yesterday.
     *
     * @return this
     */
    public LocalDateBuilder past() {
      return after(LocalDate.MIN).before(LocalDate.now().minusDays(1));
    }

    /**
     * Sets {@link #after} to tomorrow and {@link #before} to {@link LocalDate#MAX}.
     *
     * @return this
     */
    public LocalDateBuilder future() {
      return after(LocalDate.now().plusDays(1)).before(LocalDate.MAX);
    }

    /**
     * @param afterIncluding the minimum value (inclusive)
     * @return this
     * @throws IllegalArgumentException if {@code afterIncluding} is after {@link #before}
     */
    public LocalDateBuilder after(LocalDate afterIncluding) {
      if (afterIncluding.isAfter(before)) {
        throw new IllegalArgumentException(
            "Can't set after to %s, as it must not be greater than before (%s)"
                .formatted(afterIncluding, before));
      }
      this.after = afterIncluding;
      return this;
    }

    /**
     * @param beforeIncluding the maximum value (inclusive)
     * @return this
     * @throws IllegalArgumentException if {@code beforeIncluding} is before {@link #after}
     */
    public LocalDateBuilder before(LocalDate beforeIncluding) {
      if (beforeIncluding.isBefore(after)) {
        throw new IllegalArgumentException(
            "Can't set before to %s, as it must not be less than after (%s)"
                .formatted(beforeIncluding, after));
      }
      this.before = beforeIncluding;
      return this;
    }

    /**
     * @return a random long between {@link #after} and {@link #before}
     */
    public LocalDate build() {
      long minEpochDay = after.toEpochDay();
      long maxEpochDay = before.toEpochDay() + 1;
      long randomEpochDay = minEpochDay + secureRandom.nextLong(0, maxEpochDay - minEpochDay);
      return LocalDate.ofEpochDay(randomEpochDay);
    }
  }
}
