package org.stubit.random;

public class RandomString {

  private RandomString() {}

  public static Builder aString() {
    return new Builder();
  }

  public static class Builder {

    private final StringBuilder builder = new StringBuilder();

    public Builder startingWith(Character character) {
      return followedBy(character);
    }

    public Builder followedBy(Character character) {
      builder.append(character);
      return this;
    }

    public Builder followedBy(String string) {
      builder.append(string);
      return this;
    }

    public String build() {
      return builder.toString();
    }
  }
}
