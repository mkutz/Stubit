package org.stubit.random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.stubit.random.RandomChoice.aChoiceFrom;
import static org.stubit.random.RandomChoice.aChoiceFromValuesOf;
import static org.stubit.random.RandomChoice.chooseAnyFrom;
import static org.stubit.random.RandomChoice.chooseAnyFromValuesOf;
import static org.stubit.random.RandomInt.aNegativeInt;
import static org.stubit.random.RandomInt.aPositiveInt;
import static org.stubit.random.RandomInt.anInt;
import static org.stubit.random.RandomInt.anIntBetween;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class RandomDocTest {

  @Test
  void randomInt_examples() {
    // tag::anIntBetween[]
    int anotherIntBetween42And4711 = anIntBetween(42, 4711);
    assertThat(anotherIntBetween42And4711).isBetween(42, 4711);
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
    // tag::chooseAnyFrom_ellipsis[]
    assertThat(chooseAnyFrom("a", "b", "c")).isIn("a", "b", "c");
    // end::chooseAnyFrom_ellipsis[]

    // tag::chooseAnyFrom_array[]
    String[] choiceArray = {"a", "b", "c"};
    assertThat(chooseAnyFrom(choiceArray)).isIn((Object[]) choiceArray);
    // end::chooseAnyFrom_array[]

    // tag::chooseAnyFrom_list[]
    var choicesList = List.of("a", "b", "c");
    assertThat(chooseAnyFrom(choicesList)).isIn(choicesList);
    // end::chooseAnyFrom_list[]

    // tag::chooseAnyFrom_map[]
    var choicesMap = Map.of("a", 1, "b", 2, "c", 3);
    assertThat(chooseAnyFrom(choicesMap)).isIn(choicesMap.entrySet());
    // end::chooseAnyFrom_map[]

    // tag::chooseAnyFromValuesOf_enum[]
    enum Color {
      RED,
      GREEN,
      BLUE
    }
    assertThat(chooseAnyFromValuesOf(Color.class)).isIn(Color.RED, Color.GREEN, Color.BLUE);
    // end::chooseAnyFromValuesOf_enum[]
  }

  @Test
  void randomChoiceBuilder_examples() {
    // tag::from_ellipsis[]
    assertThat(aChoiceFrom("a", "b", "c").build()).isIn("a", "b", "c");
    // end::from_ellipsis[]

    // tag::from_array[]
    String[] choiceArray = {"a", "b", "c"};
    assertThat(aChoiceFrom(choiceArray).build()).isIn((Object[]) choiceArray);
    // end::from_array[]

    // tag::from_list[]
    var choicesList = List.of("a", "b", "c");
    assertThat(aChoiceFrom(choicesList).build()).isIn(choicesList);
    // end::from_list[]

    // tag::from_map[]
    var choicesMap = Map.of("a", 1, "b", 2, "c", 3);
    assertThat(aChoiceFrom(choicesMap).build()).isIn(choicesMap);
    // end::from_map[]

    // tag::from_enum_values[]
    enum Color {
      RED,
      GREEN,
      BLUE
    }
    assertThat(aChoiceFromValuesOf(Color.class).build()).isIn(Color.RED, Color.GREEN, Color.BLUE);
    // end::from_enum_values[]

    // tag::save[]
    var allChoices = List.of("a", "b", "c");
    var excludedChoice = "a";
    assertThat(aChoiceFrom(allChoices).butNot(excludedChoice).build()).isNotEqualTo(excludedChoice);
    // end::save[]
  }
}
