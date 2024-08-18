package org.stubit.random;

import java.util.List;
import org.jspecify.annotations.NullMarked;

/** Represents a digit system (a list of digits). */
@NullMarked
public class DigitSystem {

  /** The (western) Arabic digit system. */
  public static final DigitSystem ARABIC =
      new DigitSystem(List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));

  /** The Eastern Arabic digit system. */
  public static final DigitSystem EASTERN_ARABIC =
      new DigitSystem(List.of('٠', '١', '٢', '٣', '٤', '٥', '٦', '٧', '٨', '٩'));

  /** The Persian digit system. */
  public static final DigitSystem PERSIAN =
      new DigitSystem(List.of('۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹'));

  /** The Chinese digit system. */
  public static final DigitSystem CHINESE =
      new DigitSystem(List.of('〇', '一', '二', '三', '四', '五', '六', '七', '八', '九'));

  /** The Japanese digit system. */
  public static final DigitSystem JAPANESE =
      new DigitSystem(List.of('〇', '一', '二', '三', '四', '五', '六', '七', '八', '九'));

  /** The roman digit system. */
  public static final DigitSystem ROMAN =
      new DigitSystem(List.of('Ⅰ', 'Ⅴ', 'Ⅹ', 'Ⅼ', 'Ⅽ', 'Ⅾ', 'Ⅿ'));

  private final List<Character> digits;

  /**
   * Creates a digit system with the provided list of digits.
   *
   * @param digits the list of digits in this digit system
   */
  public DigitSystem(List<Character> digits) {
    this.digits = digits;
  }

  /**
   * @return the list of digit characters in this digit system
   */
  public List<Character> digits() {
    return digits;
  }
}
