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
   * @param after the minimum value (inclusive)
   * @param before the maximum value (inclusive)
   * @return a random {@link LocalDate} between {@code after} and {@code before}
   */
  public static LocalDate aLocalDateBetween(LocalDate after, LocalDate before) {
    return aLocalDateInRange().after(after).before(before).build();
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

    private LocalDateInRangeBuilder(LocalDate after, LocalDate before) {
      this.after = after;
      this.before = before;
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
     * @param after the minimum value (inclusive)
     * @return this
     * @throws IllegalArgumentException if {@code after} is after {@link #before}
     */
    public LocalDateInRangeBuilder after(LocalDate after) {
      if (after.isAfter(before)) {
        throw new IllegalArgumentException(
            "Can't set after to %s, as it must not be greater than before (%s)"
                .formatted(after, before));
      }
      this.after = after;
      return this;
    }

    /**
     * @param before the maximum value (inclusive)
     * @return this
     * @throws IllegalArgumentException if {@code before} is before {@link #after}
     */
    public LocalDateInRangeBuilder before(LocalDate before) {
      if (before.isBefore(after)) {
        throw new IllegalArgumentException(
            "Can't set before to %s, as it must not be less than after (%s)"
                .formatted(before, after));
      }
      this.before = before;
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

  /** Builds a random {@link LocalDate} with specified field values (e.g. year, month). */
  public static class LocalDateBuilder {

    private LocalDate localDate = RandomLocalDate.aLocalDateInRange().build();

    /**
     * @param year the year for the random {@link LocalDate}
     * @return this
     */
    public LocalDateBuilder year(int year) {
      return year(Year.of(year));
    }

    /**
     * @param year the {@link Year} for the random {@link LocalDate}
     * @return this
     */
    public LocalDateBuilder year(Year year) {
      localDate = localDate.with(year);
      return this;
    }

    /**
     * @param month the month for the random {@link LocalDate}
     * @return this
     */
    public LocalDateBuilder month(int month) {
      return month(Month.of(month));
    }

    /**
     * @param month the {@link Month} for the random {@link LocalDate}
     * @return this
     */
    public LocalDateBuilder month(Month month) {
      localDate = localDate.with(month);
      return this;
    }

    /**
     * @param day the day of the month for the random {@link LocalDate}
     * @return this
     * @throws java.time.DateTimeException if {@code day} is not a valid day of the month
     * @see LocalDate#withDayOfMonth(int)
     */
    public LocalDateBuilder dayOfMonth(int day) {
      localDate = localDate.withDayOfMonth(day);
      return this;
    }

    /**
     * @param dayOfWeek the day of the week for the random {@link LocalDate}
     * @return this
     */
    public LocalDateBuilder dayOfWeek(int dayOfWeek) {
      return dayOfWeek(DayOfWeek.of(dayOfWeek));
    }

    /**
     * @param dayOfWeek the {@link DayOfWeek} for the random {@link LocalDate}
     * @return this
     */
    public LocalDateBuilder dayOfWeek(DayOfWeek dayOfWeek) {
      localDate = localDate.with(dayOfWeek);
      return this;
    }

    /**
     * @return the random {@link LocalDate}
     */
    public LocalDate build() {
      return localDate;
    }
  }
}
