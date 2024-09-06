package org.stubit.random;

import java.security.SecureRandom;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import org.jspecify.annotations.NullMarked;

/** Generates random {@link LocalDate}s. */
@NullMarked
public class RandomLocalDate {

  private RandomLocalDate() {}

  /**
   * @param afterInclusive the minimum value (inclusive)
   * @param beforeInclusive the maximum value (inclusive)
   * @return a random {@link LocalDate} between {@code afterInclusive} and {@code beforeInclusive}
   */
  public static LocalDate aLocalDateBetween(LocalDate afterInclusive, LocalDate beforeInclusive) {
    return aLocalDateInRange().after(afterInclusive).before(beforeInclusive).build();
  }

  /**
   * @return a random {@link LocalDate} between {@link LocalDate#MIN} and yesterday
   */
  public static LocalDate aPastLocalDate() {
    return aLocalDateInRange().past().build();
  }

  /**
   * @return a random {@link LocalDate} between tomorrow and {@link LocalDate#MAX}
   */
  public static LocalDate aFutureLocalDate() {
    return aLocalDateInRange().future().build();
  }

  /**
   * @return a {@link LocalDateInRangeBuilder} to configure the random {@link LocalDate}
   */
  public static LocalDateInRangeBuilder aLocalDateInRange() {
    return new LocalDateInRangeBuilder(LocalDate.MIN, LocalDate.MAX);
  }

  /**
   * @return a {@link LocalDateBuilder} to configure the random {@link LocalDate}
   */
  public static LocalDateBuilder aLocalDate() {
    return new LocalDateBuilder();
  }

  /** Builds a random {@link LocalDate} within a specified range. */
  public static class LocalDateInRangeBuilder {

    private final SecureRandom secureRandom = new SecureRandom();
    private LocalDate after;
    private LocalDate before;

    private LocalDateInRangeBuilder(LocalDate min, LocalDate max) {
      this.after = min;
      this.before = max;
    }

    /**
     * Sets {@link #after} to {@link LocalDate#MIN} and {@link #before} to yesterday.
     *
     * @return this
     */
    public LocalDateInRangeBuilder past() {
      return after(LocalDate.MIN).before(LocalDate.now().minusDays(1));
    }

    /**
     * Sets {@link #after} to tomorrow and {@link #before} to {@link LocalDate#MAX}.
     *
     * @return this
     */
    public LocalDateInRangeBuilder future() {
      return after(LocalDate.now().plusDays(1)).before(LocalDate.MAX);
    }

    /**
     * @param afterIncluding the minimum value (inclusive)
     * @return this
     * @throws IllegalArgumentException if {@code afterIncluding} is after {@link #before}
     */
    public LocalDateInRangeBuilder after(LocalDate afterIncluding) {
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
    public LocalDateInRangeBuilder before(LocalDate beforeIncluding) {
      if (beforeIncluding.isBefore(after)) {
        throw new IllegalArgumentException(
            "Can't set before to %s, as it must not be less than after (%s)"
                .formatted(beforeIncluding, after));
      }
      this.before = beforeIncluding;
      return this;
    }

    /**
     * @return a random {@link LocalDate} between {@link #after} and {@link #before}
     */
    public LocalDate build() {
      long minEpochDay = after.toEpochDay();
      long maxEpochDay = before.toEpochDay() + 1;
      long randomEpochDay = minEpochDay + secureRandom.nextLong(0, maxEpochDay - minEpochDay);
      return LocalDate.ofEpochDay(randomEpochDay);
    }
  }

  public static class LocalDateBuilder {

    private LocalDate localDate = RandomLocalDate.aLocalDateInRange().build();

    public LocalDateBuilder year(int year) {
      return year(Year.of(year));
    }

    public LocalDateBuilder year(Year year) {
      localDate = localDate.with(year);
      return this;
    }

    public LocalDateBuilder month(int month) {
      return month(Month.of(month));
    }

    public LocalDateBuilder month(Month month) {
      localDate = localDate.with(month);
      return this;
    }

    public LocalDateBuilder dayOfMonth(int day) {
      localDate = localDate.withDayOfMonth(day);
      return this;
    }

    public LocalDateBuilder dayOfWeek(int dayOfWeek) {
      return dayOfWeek(DayOfWeek.of(dayOfWeek));
    }

    public LocalDateBuilder dayOfWeek(DayOfWeek dayOfWeek) {
      localDate = localDate.with(dayOfWeek);
      return this;
    }

    public LocalDate build() {
      return localDate;
    }
  }
}
