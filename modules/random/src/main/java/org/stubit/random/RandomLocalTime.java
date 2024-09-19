package org.stubit.random;

import java.security.SecureRandom;
import java.time.LocalTime;
import org.jspecify.annotations.NullMarked;

/** Generates random {@link LocalTime}s. */
@NullMarked
public class RandomLocalTime {

  private RandomLocalTime() {}

  /**
   * @param after the minimum value (inclusive)
   * @param before the maximum value (inclusive)
   * @return a random {@link LocalTime} between {@code after} and {@code before}
   */
  public static LocalTime aLocalTimeBetween(LocalTime after, LocalTime before) {
    return aLocalTimeInRange().after(after).before(before).build();
  }

  /**
   * @return a random {@link LocalTime} between {@link LocalTime#MIN} and now
   */
  public static LocalTime aPastLocalTime() {
    return aLocalTimeInRange().before(LocalTime.now()).build();
  }

  /**
   * @return a random {@link LocalTime} between now and {@link LocalTime#MAX}
   */
  public static LocalTime aFutureLocalTime() {
    return aLocalTimeInRange().after(LocalTime.now()).build();
  }

  /**
   * @return a {@link RandomLocalTime.LocalTimeInRangeBuilder} to configure the random {@link
   *     LocalTime}
   */
  public static LocalTimeInRangeBuilder aLocalTimeInRange() {
    return new LocalTimeInRangeBuilder(LocalTime.MIN, LocalTime.MAX);
  }

  /**
   * @return a {@link LocalTimeBuilder} to configure the random {@link LocalTime}
   */
  public static LocalTimeBuilder aLocalTime() {
    return new LocalTimeBuilder();
  }

  /** Builds a random {@link LocalTime} within a specified range. */
  public static class LocalTimeInRangeBuilder {

    private final SecureRandom secureRandom = new SecureRandom();
    private LocalTime after;
    private LocalTime before;

    private LocalTimeInRangeBuilder(LocalTime after, LocalTime before) {
      this.after = after;
      this.before = before;
    }

    /**
     * Sets {@link #after} to {@link LocalTime#MIN} and {@link #before} to now.
     *
     * @return this
     */
    public LocalTimeInRangeBuilder past() {
      return after(LocalTime.MIN).before(LocalTime.now().minusSeconds(1));
    }

    /**
     * Sets {@link #after} to now and {@link #before} to {@link LocalTime#MAX}.
     *
     * @return this
     */
    public LocalTimeInRangeBuilder future() {
      return after(LocalTime.now().plusSeconds(1)).before(LocalTime.MAX);
    }

    /**
     * @param after the minimum value (inclusive).
     * @return this
     */
    public LocalTimeInRangeBuilder after(LocalTime after) {
      if (after.isAfter(before)) {
        throw new IllegalArgumentException(
            "Can't set after to %s, as it must not be greater than before (%s)"
                .formatted(after, before));
      }
      this.after = after;
      return this;
    }

    /**
     * @param before the minimum value (inclusive)
     * @return this builder
     */
    public LocalTimeInRangeBuilder before(LocalTime before) {
      if (before.isBefore(after)) {
        throw new IllegalArgumentException(
            "Can't set before to %s, as it must not be less than after (%s)"
                .formatted(before, after));
      }
      this.before = before;
      return this;
    }

    /**
     * @return a random {@link LocalTime} between {@link #after} and {@link #before}
     */
    public LocalTime build() {
      long minDayNano = after.toNanoOfDay();
      long maxDayNano = before.toNanoOfDay() + 1;
      long randomDayNano = minDayNano + secureRandom.nextLong(0, maxDayNano - minDayNano);
      return LocalTime.ofNanoOfDay(randomDayNano);
    }
  }

  /** Builds a random {@link LocalTime} with specified field values (e.g. hour, minute). */
  public static class LocalTimeBuilder {

    private LocalTime localTime = RandomLocalTime.aLocalTimeInRange().build();

    /**
     * @param hour the hour for the random {@link LocalTime}
     * @return this
     */
    public LocalTimeBuilder hour(int hour) {
      localTime = localTime.withHour(hour);
      return this;
    }

    /**
     * @param minute the minute for the random {@link LocalTime}
     * @return this
     */
    public LocalTimeBuilder minute(int minute) {
      localTime = localTime.withMinute(minute);
      return this;
    }

    /**
     * @param second the second for the random {@link LocalTime}
     * @return this
     */
    public LocalTimeBuilder second(int second) {
      localTime = localTime.withSecond(second);
      return this;
    }

    /**
     * @param nano the nanosecond for the random {@link LocalTime}
     * @return this
     */
    public LocalTimeBuilder nano(int nano) {
      localTime = localTime.withNano(nano);
      return this;
    }

    /**
     * @return the random {@link LocalTime}
     */
    public LocalTime build() {
      return localTime;
    }
  }
}
