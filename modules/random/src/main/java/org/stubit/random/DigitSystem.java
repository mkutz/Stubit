package org.stubit.random;

import java.util.List;

/** Represents a digit system (a list of digits). */
public class DigitSystem {

  public static final DigitSystem ARABIC =
      new DigitSystem(List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
  public static final DigitSystem EASTERN_ARABIC =
      new DigitSystem(List.of('٠', '١', '٢', '٣', '٤', '٥', '٦', '٧', '٨', '٩'));
  public static final DigitSystem PERSIAN =
      new DigitSystem(List.of('۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹'));
  public static final DigitSystem CHINESE =
      new DigitSystem(List.of('〇', '一', '二', '三', '四', '五', '六', '七', '八', '九'));
  public static final DigitSystem JAPANESE =
      new DigitSystem(List.of('〇', '一', '二', '三', '四', '五', '六', '七', '八', '九'));
  public static final DigitSystem ROMAN =
      new DigitSystem(List.of('Ⅰ', 'Ⅴ', 'Ⅹ', 'Ⅼ', 'Ⅽ', 'Ⅾ', 'Ⅿ'));

  private final List<Character> digits;

  public DigitSystem(List<Character> digits) {
    this.digits = digits;
  }

  public List<Character> digits() {
    return digits;
  }
}
