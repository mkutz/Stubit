package org.stubit.random;

import java.time.Duration;

public class RandomDuration {

  private RandomDuration() {}

  public static Duration aDurationBetween(Duration minInclusive, Duration maxInclusive) {
    return Duration.ofSeconds(
        RandomLong.aLongBetween(minInclusive.toSeconds(), maxInclusive.toSeconds()));
  }
}
