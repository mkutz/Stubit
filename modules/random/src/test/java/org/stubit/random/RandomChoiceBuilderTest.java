package org.stubit.random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.stubit.random.RandomChoice.from;
import static org.stubit.random.RandomChoice.fromValuesOf;

import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;

class RandomChoiceBuilderTest {

  @Test
  void from_ellipsis() {
    assertThat(from("a", "b", "c").chooseAny()).isIn("a", "b", "c");
  }

  @Test
  void from_ellipsis_single() {
    assertThat(from("a").chooseAny()).isEqualTo("a");
  }

  @Test
  void from_ellipsis_empty() {
    assertThatIllegalArgumentException()
        .isThrownBy(RandomChoice::from)
        .withMessage("No choices provided");
  }

  @Test
  void from_array() {
    String[] choicesArray = {"a", "b", "c"};
    assertThat(from(choicesArray).chooseAny()).isIn((Object[]) choicesArray);
  }

  @Test
  void from_array_single() {
    String singleChoice = "a";
    String[] choicesArray = {singleChoice};
    assertThat(from(choicesArray).chooseAny()).isEqualTo(singleChoice);
  }

  @Test
  void from_array_empty() {
    String[] emptyChoicesArray = {};
    assertThatIllegalArgumentException()
        .isThrownBy(() -> from(emptyChoicesArray))
        .withMessage("No choices provided");
  }

  @Test
  void from_collection() {
    Collection<String> choicesList = List.of("a", "b", "c");
    assertThat(from(choicesList).chooseAny()).isIn(choicesList);
  }

  @Test
  void from_collection_single() {
    String singleChoice = "a";
    Collection<String> choicesCollection = List.of(singleChoice);
    assertThat(from(choicesCollection).chooseAny()).isEqualTo(singleChoice);
  }

  @Test
  void from_collection_Empty() {
    Collection<String> emptyChoicesCollection = List.of();
    assertThatIllegalArgumentException()
        .isThrownBy(() -> from(emptyChoicesCollection))
        .withMessage("No choices provided");
  }

  @Test
  void from_enum() {
    enum ChoiceEnum {
      A,
      B,
      C
    }
    assertThat(fromValuesOf(ChoiceEnum.class).chooseAny()).isIn((Object[]) ChoiceEnum.values());
  }

  @Test
  void from_enum_single() {
    enum SingleChoiceEnum {
      SINGLE_CHOICE
    }
    assertThat(fromValuesOf(SingleChoiceEnum.class).chooseAny())
        .isEqualTo(SingleChoiceEnum.SINGLE_CHOICE);
  }

  @Test
  void from_enum_empty() {
    enum EmptyChoiceEnum {}
    assertThatIllegalArgumentException()
        .isThrownBy(() -> fromValuesOf(EmptyChoiceEnum.class))
        .withMessage("No choices provided");
  }

  @Test
  void save_one() {
    String excludedChoice = "a";
    Collection<String> remainingChoices = List.of("b", "c");
    Collection<String> allChoices = List.of(excludedChoice, "b", "c");
    assertThat(from(allChoices).save(excludedChoice).chooseAny())
        .isNotEqualTo(excludedChoice)
        .isIn(remainingChoices);
  }

  @Test
  void save_all_but_one() {
    String remainingChoice = "c";
    Collection<String> excludedChoices = List.of("a", "b");
    Collection<String> allChoices = List.of("a", "b", remainingChoice);
    assertThat(from(allChoices).save(excludedChoices).chooseAny()).isEqualTo(remainingChoice);
  }

  @Test
  void save_all() {
    Collection<String> allChoices = List.of("a", "b", "c");
    assertThatIllegalArgumentException()
        .isThrownBy(() -> from(allChoices).save(allChoices))
        .withMessage("No choices left");
  }

  @Test
  void save_unknown() {
    Collection<String> excludedChoices = List.of("c", "x");
    Collection<String> remainingChoices = List.of("a", "b");
    Collection<String> allChoices = List.of("a", "b", "c");
    assertThat(from(allChoices).save(excludedChoices).chooseAny()).isIn(remainingChoices);
  }
}
