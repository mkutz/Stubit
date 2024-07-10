package org.stubit.random;

/** Generates random strings. */
public class RandomString {

  private RandomString() {}

  /**
   * @param character the character the string should start with
   * @return a new {@link Builder}
   */
  public static Builder aStringStartingWith(char character) {
    return new Builder(character);
  }

  /**
   * @param string the character the string should start with
   * @return a new {@link Builder}
   */
  public static Builder aStringStartingWith(String string) {
    return new Builder(string);
  }

  /** Builds a random string. */
  public static class Builder {

    private final StringBuilder stringBuilder;

    private Builder(String startString) {
      stringBuilder = new StringBuilder(startString);
    }

    private Builder(char startCharacter) {
      stringBuilder = new StringBuilder().append(startCharacter);
    }

    /**
     * @param character the character to append
     * @return this
     */
    public Builder followedBy(Character character) {
      stringBuilder.append(character);
      return this;
    }

    /**
     * @param string the string to append
     * @return this
     */
    public Builder followedBy(String string) {
      stringBuilder.append(string);
      return this;
    }

    /**
     * @return a {@link String} with the appended characters
     */
    public String build() {
      return stringBuilder.toString();
    }
  }
}
