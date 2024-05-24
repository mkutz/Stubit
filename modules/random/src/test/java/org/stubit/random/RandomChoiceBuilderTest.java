package org.stubit.random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.stubit.random.RandomChoice.aChoiceFrom;
import static org.stubit.random.RandomChoice.aChoiceFromValuesOf;

import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;

class RandomChoiceBuilderTest {

  @Test
  void from_ellipsis() {
    assertThat(aChoiceFrom("a", "b", "c").build()).isIn("a", "b", "c");
  }

  @Test
  void aChoiceFrom_ellipsis_single() {
    assertThat(aChoiceFrom("a").build()).isEqualTo("a");
  }

  @Test
  void aChoiceFrom_ellipsis_empty() {
    assertThatIllegalArgumentException()
        .isThrownBy(RandomChoice::aChoiceFrom)
        .withMessage("No choices provided");
  }

  @Test
  void aChoiceFrom_array() {
    String[] choicesArray = {"a", "b", "c"};
    assertThat(aChoiceFrom(choicesArray).build()).isIn((Object[]) choicesArray);
  }

  @Test
  void aChoiceFrom_array_single() {
    String singleChoice = "a";
    String[] choicesArray = {singleChoice};
    assertThat(aChoiceFrom(choicesArray).build()).isEqualTo(singleChoice);
  }

  @Test
  void aChoiceFrom_array_empty() {
    String[] emptyChoicesArray = {};
    assertThatIllegalArgumentException()
        .isThrownBy(() -> aChoiceFrom(emptyChoicesArray))
        .withMessage("No choices provided");
  }

  @Test
  void aChoiceFrom_collection() {
    Collection<String> choicesList = List.of("a", "b", "c");
    assertThat(RandomChoice.aChoiceFrom(choicesList).build()).isIn(choicesList);
  }

  @Test
  void aChoiceFrom_collection_single() {
    String singleChoice = "a";
    Collection<String> choicesCollection = List.of(singleChoice);
    assertThat(RandomChoice.aChoiceFrom(choicesCollection).build()).isEqualTo(singleChoice);
  }

  @Test
  void aChoiceFrom_collection_empty() {
    Collection<String> emptyChoicesCollection = List.of();
    assertThatIllegalArgumentException()
        .isThrownBy(() -> RandomChoice.aChoiceFrom(emptyChoicesCollection))
        .withMessage("No choices provided");
  }

  @Test
  void aChoiceFromValuesOf_enum() {
    enum ChoiceEnum {
      A,
      B,
      C
    }
    assertThat(aChoiceFromValuesOf(ChoiceEnum.class).build()).isIn((Object[]) ChoiceEnum.values());
  }

  @Test
  void aChoiceFromValuesOf_enum_single() {
    enum SingleChoiceEnum {
      SINGLE_CHOICE
    }
    assertThat(aChoiceFromValuesOf(SingleChoiceEnum.class).build())
        .isEqualTo(SingleChoiceEnum.SINGLE_CHOICE);
  }

  @Test
  void aChoiceFromValuesOf_enum_empty() {
    enum EmptyChoiceEnum {}
    assertThatIllegalArgumentException()
        .isThrownBy(() -> aChoiceFromValuesOf(EmptyChoiceEnum.class))
        .withMessage("No choices provided");
  }

  @Test
  void and_collection() {
    Collection<String> initialChoices = List.of("a", "b");
    Collection<String> additionalChoices = List.of("c", "d");
    Collection<String> allChoices = List.of("a", "b", "c", "d");
    assertThat(RandomChoice.aChoiceFrom(initialChoices).and(additionalChoices).build())
        .isIn(allChoices);
  }

  @Test
  void and_ellipsis() {
    Collection<String> initialChoices = List.of("a", "b");
    Collection<String> allChoices = List.of("a", "b", "c", "d");
    assertThat(RandomChoice.aChoiceFrom(initialChoices).and("c", "d").build()).isIn(allChoices);
  }

  @Test
  void butNot_one() {
    String excludedChoice = "a";
    Collection<String> remainingChoices = List.of("b", "c");
    Collection<String> allChoices = List.of(excludedChoice, "b", "c");
    assertThat(RandomChoice.aChoiceFrom(allChoices).butNot(excludedChoice).build())
        .isNotEqualTo(excludedChoice)
        .isIn(remainingChoices);
  }

  @Test
  void butNot_all_but_one() {
    String remainingChoice = "c";
    Collection<String> excludedChoices = List.of("a", "b");
    Collection<String> allChoices = List.of("a", "b", remainingChoice);
    assertThat(RandomChoice.aChoiceFrom(allChoices).butNot(excludedChoices).build())
        .isEqualTo(remainingChoice);
  }

  @Test
  void butNot_all() {
    Collection<String> allChoices = List.of("a", "b", "c");
    assertThatIllegalArgumentException()
        .isThrownBy(() -> RandomChoice.aChoiceFrom(allChoices).butNot(allChoices))
        .withMessage("No choices left");
  }

  @Test
  void butNot_unknown() {
    Collection<String> excludedChoices = List.of("c", "x");
    Collection<String> remainingChoices = List.of("a", "b");
    Collection<String> allChoices = List.of("a", "b", "c");
    assertThat(RandomChoice.aChoiceFrom(allChoices).butNot(excludedChoices).build())
        .isIn(remainingChoices);
  }
}
