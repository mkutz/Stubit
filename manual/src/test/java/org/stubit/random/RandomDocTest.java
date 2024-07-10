package org.stubit.random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.stubit.random.RandomChar.aLatinLetter;
import static org.stubit.random.RandomChar.anArabicDigit;
import static org.stubit.random.RandomChoice.aChoiceFrom;
import static org.stubit.random.RandomChoice.aChoiceFromValuesOf;
import static org.stubit.random.RandomChoice.any;
import static org.stubit.random.RandomChoice.anyOf;
import static org.stubit.random.RandomInt.aNegativeInt;
import static org.stubit.random.RandomInt.aPositiveInt;
import static org.stubit.random.RandomInt.anInt;
import static org.stubit.random.RandomInt.anIntBetween;
import static org.stubit.random.RandomString.aStringStartingWith;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class RandomDocTest {

  @Test
  void randomInt_examples() {
    // tag::anIntBetween[]
    int someIntBetween42And4711 = anIntBetween(42, 4711);
    assertThat(someIntBetween42And4711).isBetween(42, 4711);
    // end::anIntBetween[]

    // tag::aPositiveInt[]
    int somePositiveInt = aPositiveInt();
    assertThat(somePositiveInt).isPositive().isNotZero().isLessThan(Integer.MAX_VALUE);
    // end::aPositiveInt[]

    // tag::aNegativeInt[]
    int someNegativeInt = aNegativeInt();
    assertThat(someNegativeInt).isNegative().isNotZero().isGreaterThanOrEqualTo(Integer.MIN_VALUE);
    // end::aNegativeInt[]
  }

  @Test
  void randomIntBuilder_examples() {
    // tag::anInt[]
    int someInt = anInt().build();
    assertThat(someInt).isBetween(Integer.MIN_VALUE, Integer.MAX_VALUE - 1);

    int someIntBetween42And4711 = anInt().min(42).max(4711).build();
    assertThat(someIntBetween42And4711).isBetween(42, 4711);

    int someIntLessThan4711 = anInt().max(4711).build();
    assertThat(someIntLessThan4711).isLessThanOrEqualTo(4711);

    int someIntGreaterThanMinus10 = anInt().min(-42).build();
    assertThat(someIntGreaterThanMinus10).isGreaterThanOrEqualTo(-42);
    // end::anInt[]
  }

  @Test
  void randomChoice_examples() {
    // tag::anyOf_ellipsis[]
    String choiceFromEllipsis = anyOf("a", "b", "c");
    assertThat(choiceFromEllipsis).isIn("a", "b", "c");
    // end::anyOf_ellipsis[]

    // tag::anyOf_array[]
    String[] choiceArray = {"a", "b", "c"};
    String choiceFromArray = anyOf(choiceArray[0], choiceArray[1], choiceArray[2]);
    assertThat(choiceFromArray).isIn((Object[]) choiceArray);
    // end::anyOf_array[]

    // tag::anyOf_list[]
    List<String> choicesList = List.of("a", "b", "c");
    String choiceFromList = anyOf(choicesList);
    assertThat(choiceFromList).isIn(choicesList);
    // end::anyOf_list[]

    // tag::anyOf_map[]
    Map<String, Integer> choicesMap = Map.of("a", 1, "b", 2, "c", 3);
    Map.Entry<String, Integer> choiceFromMap = anyOf(choicesMap);
    assertThat(choiceFromMap).isIn(choicesMap.entrySet());
    // end::anyOf_map[]

    // tag::any_enum[]
    enum Color {
      RED,
      GREEN,
      BLUE
    }
    Color choiceFromEnum = any(Color.class);
    assertThat(choiceFromEnum).isIn(Color.RED, Color.GREEN, Color.BLUE);
    // end::any_enum[]
  }

  @Test
  void randomChoiceBuilder_examples() {
    // tag::aChoiceFrom_ellipsis[]
    String choiceFromEllipsis = aChoiceFrom("a", "b", "c").build();
    assertThat(choiceFromEllipsis).isIn("a", "b", "c");
    // end::aChoiceFrom_ellipsis[]

    // tag::aChoiceFrom_array[]
    String[] choicesArray = {"a", "b", "c"};
    String choiceFromArray = aChoiceFrom(choicesArray).build();
    assertThat(choiceFromArray).isIn((Object[]) choicesArray);
    // end::aChoiceFrom_array[]

    // tag::aChoiceFrom_list[]
    var choicesList = List.of("a", "b", "c");
    String choiceFromList = aChoiceFrom(choicesList).build();
    assertThat(choiceFromList).isIn(choicesList);
    // end::aChoiceFrom_list[]

    // tag::aChoiceFrom_map[]
    Map<String, Integer> choicesMap = Map.of("a", 1, "b", 2, "c", 3);
    Map.Entry<String, Integer> choiceFromMap = aChoiceFrom(choicesMap).build();
    assertThat(choiceFromMap).isIn(choicesMap.entrySet());
    // end::aChoiceFrom_map[]

    // tag::aChoiceFromValuesOf_enum[]
    enum Color {
      RED,
      GREEN,
      BLUE
    }
    Color choiceFromEnum = aChoiceFromValuesOf(Color.class).build();
    assertThat(choiceFromEnum).isIn(Color.RED, Color.GREEN, Color.BLUE);
    // end::aChoiceFromValuesOf_enum[]

    // tag::and[]
    String choiceWithAdditions = aChoiceFrom(List.of("a", "b")).and("c", "d").build();
    assertThat(choiceWithAdditions).isIn("a", "b", "c", "d");
    // end::and[]

    // tag::butNot[]
    String choiceWithExclusions = aChoiceFrom(List.of("a", "b", "c")).butNot("a").build();
    assertThat(choiceWithExclusions).isNotEqualTo("a");
    // end::butNot[]
  }

  @Test
  void randomStringStartingWith_examples() {
    // tag::aStringStartingWith[]
    assertThat(
            aStringStartingWith(anArabicDigit())
                .followedBy(aLatinLetter())
                .followedBy('a')
                .followedBy("-test")
                .build())
        .matches("\\d\\wa-test");
    // end::aStringStartingWith[]
  }
}
