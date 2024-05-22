package org.stubit.random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.stubit.random.RandomChoice.*;
import static org.stubit.random.RandomIntBuilder.*;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class RandomDocTest {

  @Test
  void randomInt_examples() {
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

    // tag::aPositiveInt[]
    int somePositiveInt = aPositiveInt().build();
    assertThat(somePositiveInt).isPositive().isNotZero().isLessThan(Integer.MAX_VALUE);
    // end::aPositiveInt[]

    // tag::aNegativeInt[]
    int someNegativeInt = aNegativeInt().build();
    assertThat(someNegativeInt).isNegative().isNotZero().isGreaterThanOrEqualTo(Integer.MIN_VALUE);
    // end::aNegativeInt[]
  }

  @Test
  void randomChoice_examples() {
    // tag::from_ellipsis[]
    assertThat(chooseAnyFrom("a", "b", "c")).isIn("a", "b", "c");
    assertThat(from("a", "b", "c").chooseAny()).isIn("a", "b", "c");
    // end::from_ellipsis[]

    // tag::from_array[]
    String[] choiceArray = {"a", "b", "c"};
    assertThat(chooseAnyFrom(choiceArray)).isIn((Object[]) choiceArray);
    assertThat(from(choiceArray).chooseAny()).isIn((Object[]) choiceArray);
    // end::from_array[]

    // tag::from_list[]
    var choicesList = List.of("a", "b", "c");
    assertThat(chooseAnyFrom(choicesList)).isIn(choicesList);
    assertThat(from(choicesList).chooseAny()).isIn(choicesList);
    // end::from_list[]

    // tag::from_map[]
    var choicesMap = Map.of("a", 1, "b", 2, "c", 3);
    assertThat(chooseAnyFrom(choicesMap)).isIn(choicesMap);
    assertThat(from(choicesMap).chooseAny()).isIn(choicesMap);
    // end::from_map[]

    // tag::from_enum_values[]
    enum Color {
      RED,
      GREEN,
      BLUE
    }
    assertThat(chooseAnyFromValuesOf(Color.class)).isIn(Color.RED, Color.GREEN, Color.BLUE);
    assertThat(fromValuesOf(Color.class).chooseAny()).isIn(Color.RED, Color.GREEN, Color.BLUE);
    // end::from_enum_values[]

    // tag::save[]
    var allChoices = List.of("a", "b", "c");
    var excludedChoice = "a";
    assertThat(from(allChoices).save(excludedChoice).chooseAny()).isNotEqualTo(excludedChoice);
    // end::save[]
  }
}
