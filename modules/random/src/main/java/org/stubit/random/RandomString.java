package org.stubit.random;

import java.security.SecureRandom;

public class RandomString {

  private static final UnicodeBlockRange defaultUnicodeBlockRange = UnicodeBlockRange.BASIC_LATIN;

  private RandomString() {}

  public static char aRandomCharacterFrom(UnicodeBlockRange unicodeBlockRange) {
    return unicodeBlockRange.randomChar();
  }

  public static char aRandomCharacter() {
    return aRandomCharacterFrom(defaultUnicodeBlockRange);
  }

  public static String aRandomString(int length) {
    return aString().length(length).build();
  }

  public static Builder aString() {
    return new Builder();
  }

  public static class Builder {

    private UnicodeBlockRange unicodeBlockRange = defaultUnicodeBlockRange;
    private int length = 10;

    public Builder unicodeBlock(UnicodeBlockRange unicodeBlockRange) {
      this.unicodeBlockRange = unicodeBlockRange;
      return this;
    }

    public Builder length(int length) {
      this.length = length;
      return this;
    }

    public String build() {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < length; i++) {
        sb.append(unicodeBlockRange.randomChar());
      }
      return sb.toString();
    }
  }

  public enum UnicodeBlockRange {
    ADLAM(Character.UnicodeBlock.ADLAM, 0x1e900, 0x1e95f),
    AEGEAN_NUMBERS(Character.UnicodeBlock.AEGEAN_NUMBERS, 0x10100, 0x1013f),
    AHOM(Character.UnicodeBlock.AHOM, 0x11700, 0x1174f),
    ALCHEMICAL_SYMBOLS(Character.UnicodeBlock.ALCHEMICAL_SYMBOLS, 0x1f700, 0x1f77f),
    ALPHABETIC_PRESENTATION_FORMS(
        Character.UnicodeBlock.ALPHABETIC_PRESENTATION_FORMS, 0xfb00, 0xfb4f),
    ANATOLIAN_HIEROGLYPHS(Character.UnicodeBlock.ANATOLIAN_HIEROGLYPHS, 0x14400, 0x1467f),
    ANCIENT_GREEK_MUSICAL_NOTATION(
        Character.UnicodeBlock.ANCIENT_GREEK_MUSICAL_NOTATION, 0x1d200, 0x1d24f),
    ANCIENT_GREEK_NUMBERS(Character.UnicodeBlock.ANCIENT_GREEK_NUMBERS, 0x10140, 0x1018f),
    ANCIENT_SYMBOLS(Character.UnicodeBlock.ANCIENT_SYMBOLS, 0x10190, 0x101cf),
    ARABIC(Character.UnicodeBlock.ARABIC, 0x0600, 0x06ff),
    ARABIC_EXTENDED_A(Character.UnicodeBlock.ARABIC_EXTENDED_A, 0x08a0, 0x08ff),
    ARABIC_MATHEMATICAL_ALPHABETIC_SYMBOLS(
        Character.UnicodeBlock.ARABIC_MATHEMATICAL_ALPHABETIC_SYMBOLS, 0x1ee00, 0x1eeff),
    ARABIC_PRESENTATION_FORMS_A(Character.UnicodeBlock.ARABIC_PRESENTATION_FORMS_A, 0xfb50, 0xfdff),
    ARABIC_PRESENTATION_FORMS_B(Character.UnicodeBlock.ARABIC_PRESENTATION_FORMS_B, 0xfe70, 0xfeff),
    ARABIC_SUPPLEMENT(Character.UnicodeBlock.ARABIC_SUPPLEMENT, 0x0750, 0x077f),
    ARMENIAN(Character.UnicodeBlock.ARMENIAN, 0x0530, 0x058f),
    ARROWS(Character.UnicodeBlock.ARROWS, 0x2190, 0x21ff),
    AVESTAN(Character.UnicodeBlock.AVESTAN, 0x10b00, 0x10b3f),
    BALINESE(Character.UnicodeBlock.BALINESE, 0x1b00, 0x1b7f),
    BAMUM(Character.UnicodeBlock.BAMUM, 0xa6a0, 0xa6ff),
    BAMUM_SUPPLEMENT(Character.UnicodeBlock.BAMUM_SUPPLEMENT, 0x16800, 0x16a3f),
    BASIC_LATIN(Character.UnicodeBlock.BASIC_LATIN, 0x0000, 0x007f),
    BASSA_VAH(Character.UnicodeBlock.BASSA_VAH, 0x16ad0, 0x16aff),
    BATAK(Character.UnicodeBlock.BATAK, 0x1bc0, 0x1bff),
    BENGALI(Character.UnicodeBlock.BENGALI, 0x0980, 0x09ff),
    BHAIKSUKI(Character.UnicodeBlock.BHAIKSUKI, 0x11c00, 0x11c6f),
    BLOCK_ELEMENTS(Character.UnicodeBlock.BLOCK_ELEMENTS, 0x2580, 0x259f),
    BOPOMOFO(Character.UnicodeBlock.BOPOMOFO, 0x3100, 0x312f),
    BOPOMOFO_EXTENDED(Character.UnicodeBlock.BOPOMOFO_EXTENDED, 0x31a0, 0x31bf),
    BOX_DRAWING(Character.UnicodeBlock.BOX_DRAWING, 0x2500, 0x257f),
    BRAHMI(Character.UnicodeBlock.BRAHMI, 0x11000, 0x1107f),
    BRAILLE_PATTERNS(Character.UnicodeBlock.BRAILLE_PATTERNS, 0x2800, 0x28ff),
    BUGINESE(Character.UnicodeBlock.BUGINESE, 0x1a00, 0x1a1f),
    BUHID(Character.UnicodeBlock.BUHID, 0x1740, 0x175f),
    BYZANTINE_MUSICAL_SYMBOLS(Character.UnicodeBlock.BYZANTINE_MUSICAL_SYMBOLS, 0x1d000, 0x1d0ff),
    CARIAN(Character.UnicodeBlock.CARIAN, 0x102a0, 0x102df),
    CAUCASIAN_ALBANIAN(Character.UnicodeBlock.CAUCASIAN_ALBANIAN, 0x10530, 0x1056f),
    CHAKMA(Character.UnicodeBlock.CHAKMA, 0x11100, 0x1114f),
    CHAM(Character.UnicodeBlock.CHAM, 0xaa00, 0xaa5f),
    CHEROKEE(Character.UnicodeBlock.CHEROKEE, 0x13a0, 0x13ff),
    CHEROKEE_SUPPLEMENT(Character.UnicodeBlock.CHEROKEE_SUPPLEMENT, 0xab70, 0xabbf),
    CHESS_SYMBOLS(Character.UnicodeBlock.CHESS_SYMBOLS, 0x1fa00, 0x1fa6f),
    CHORASMIAN(Character.UnicodeBlock.CHORASMIAN, 0x10fb0, 0x10fdf),
    CJK_COMPATIBILITY(Character.UnicodeBlock.CJK_COMPATIBILITY, 0x3300, 0x33ff),
    CJK_COMPATIBILITY_FORMS(Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS, 0xfe30, 0xfe4f),
    CJK_COMPATIBILITY_IDEOGRAPHS(
        Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS, 0xf900, 0xfaff),
    CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT(
        Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT, 0x2f800, 0x2fa1f),
    CJK_RADICALS_SUPPLEMENT(Character.UnicodeBlock.CJK_RADICALS_SUPPLEMENT, 0x2e80, 0x2eff),
    CJK_STROKES(Character.UnicodeBlock.CJK_STROKES, 0x31c0, 0x31ef),
    CJK_SYMBOLS_AND_PUNCTUATION(Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION, 0x3000, 0x303f),
    CJK_UNIFIED_IDEOGRAPHS(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS, 0x4e00, 0x9fff),
    CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A(
        Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A, 0x3400, 0x4dbf),
    CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B(
        Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B, 0x20000, 0x2a6df),
    CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C(
        Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C, 0x2a700, 0x2b73f),
    CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D(
        Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D, 0x2b740, 0x2b81f),
    CJK_UNIFIED_IDEOGRAPHS_EXTENSION_E(
        Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_E, 0x2b820, 0x2ceaf),
    CJK_UNIFIED_IDEOGRAPHS_EXTENSION_F(
        Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_F, 0x2ceb0, 0x2ebef),
    CJK_UNIFIED_IDEOGRAPHS_EXTENSION_G(
        Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_G, 0x30000, 0x3134f),
    COMBINING_DIACRITICAL_MARKS(Character.UnicodeBlock.COMBINING_DIACRITICAL_MARKS, 0x0300, 0x036f),
    COMBINING_DIACRITICAL_MARKS_EXTENDED(
        Character.UnicodeBlock.COMBINING_DIACRITICAL_MARKS_EXTENDED, 0x1ab0, 0x1aff),
    COMBINING_DIACRITICAL_MARKS_SUPPLEMENT(
        Character.UnicodeBlock.COMBINING_DIACRITICAL_MARKS_SUPPLEMENT, 0x1dc0, 0x1dff),
    COMBINING_HALF_MARKS(Character.UnicodeBlock.COMBINING_HALF_MARKS, 0xfe20, 0xfe2f),
    COMMON_INDIC_NUMBER_FORMS(Character.UnicodeBlock.COMMON_INDIC_NUMBER_FORMS, 0xa830, 0xa83f),
    CONTROL_PICTURES(Character.UnicodeBlock.CONTROL_PICTURES, 0x2400, 0x243f),
    COPTIC(Character.UnicodeBlock.COPTIC, 0x2c80, 0x2cff),
    COPTIC_EPACT_NUMBERS(Character.UnicodeBlock.COPTIC_EPACT_NUMBERS, 0x102e0, 0x102ff),
    COUNTING_ROD_NUMERALS(Character.UnicodeBlock.COUNTING_ROD_NUMERALS, 0x1d360, 0x1d37f),
    CUNEIFORM(Character.UnicodeBlock.CUNEIFORM, 0x12000, 0x123ff),
    CUNEIFORM_NUMBERS_AND_PUNCTUATION(
        Character.UnicodeBlock.CUNEIFORM_NUMBERS_AND_PUNCTUATION, 0x12400, 0x1247f),
    CURRENCY_SYMBOLS(Character.UnicodeBlock.CURRENCY_SYMBOLS, 0x20a0, 0x20cf),
    CYPRIOT_SYLLABARY(Character.UnicodeBlock.CYPRIOT_SYLLABARY, 0x10800, 0x1083f),
    CYRILLIC(Character.UnicodeBlock.CYRILLIC, 0x0400, 0x04ff),
    CYRILLIC_EXTENDED_A(Character.UnicodeBlock.CYRILLIC_EXTENDED_A, 0x2de0, 0x2dff),
    CYRILLIC_EXTENDED_B(Character.UnicodeBlock.CYRILLIC_EXTENDED_B, 0xa640, 0xa69f),
    CYRILLIC_EXTENDED_C(Character.UnicodeBlock.CYRILLIC_EXTENDED_C, 0x1c80, 0x1c8f),
    CYRILLIC_SUPPLEMENTARY(Character.UnicodeBlock.CYRILLIC_SUPPLEMENTARY, 0x0500, 0x052f),
    DESERET(Character.UnicodeBlock.DESERET, 0x10400, 0x1044f),
    DEVANAGARI(Character.UnicodeBlock.DEVANAGARI, 0x0900, 0x097f),
    DEVANAGARI_EXTENDED(Character.UnicodeBlock.DEVANAGARI_EXTENDED, 0xa8e0, 0xa8ff),
    DINGBATS(Character.UnicodeBlock.DINGBATS, 0x2700, 0x27bf),
    DIVES_AKURU(Character.UnicodeBlock.DIVES_AKURU, 0x11900, 0x1195f),
    DOGRA(Character.UnicodeBlock.DOGRA, 0x11800, 0x1184f),
    DOMINO_TILES(Character.UnicodeBlock.DOMINO_TILES, 0x1f030, 0x1f09f),
    DUPLOYAN(Character.UnicodeBlock.DUPLOYAN, 0x1bc00, 0x1bc9f),
    EARLY_DYNASTIC_CUNEIFORM(Character.UnicodeBlock.EARLY_DYNASTIC_CUNEIFORM, 0x12480, 0x1254f),
    EGYPTIAN_HIEROGLYPHS(Character.UnicodeBlock.EGYPTIAN_HIEROGLYPHS, 0x13000, 0x1342f),
    EGYPTIAN_HIEROGLYPH_FORMAT_CONTROLS(
        Character.UnicodeBlock.EGYPTIAN_HIEROGLYPH_FORMAT_CONTROLS, 0x13430, 0x1345f),
    ELBASAN(Character.UnicodeBlock.ELBASAN, 0x10500, 0x1052f),
    ELYMAIC(Character.UnicodeBlock.ELYMAIC, 0x10fe0, 0x10fff),
    EMOTICONS(Character.UnicodeBlock.EMOTICONS, 0x1f600, 0x1f64f),
    ENCLOSED_ALPHANUMERICS(Character.UnicodeBlock.ENCLOSED_ALPHANUMERICS, 0x2460, 0x24ff),
    ENCLOSED_ALPHANUMERIC_SUPPLEMENT(
        Character.UnicodeBlock.ENCLOSED_ALPHANUMERIC_SUPPLEMENT, 0x1f100, 0x1f1ff),
    ENCLOSED_CJK_LETTERS_AND_MONTHS(
        Character.UnicodeBlock.ENCLOSED_CJK_LETTERS_AND_MONTHS, 0x3200, 0x32ff),
    ENCLOSED_IDEOGRAPHIC_SUPPLEMENT(
        Character.UnicodeBlock.ENCLOSED_IDEOGRAPHIC_SUPPLEMENT, 0x1f200, 0x1f2ff),
    ETHIOPIC(Character.UnicodeBlock.ETHIOPIC, 0x1200, 0x137f),
    ETHIOPIC_EXTENDED(Character.UnicodeBlock.ETHIOPIC_EXTENDED, 0x2d80, 0x2ddf),
    ETHIOPIC_EXTENDED_A(Character.UnicodeBlock.ETHIOPIC_EXTENDED_A, 0xab00, 0xab2f),
    ETHIOPIC_SUPPLEMENT(Character.UnicodeBlock.ETHIOPIC_SUPPLEMENT, 0x1380, 0x139f),
    GENERAL_PUNCTUATION(Character.UnicodeBlock.GENERAL_PUNCTUATION, 0x2000, 0x206f),
    GEOMETRIC_SHAPES(Character.UnicodeBlock.GEOMETRIC_SHAPES, 0x25a0, 0x25ff),
    GEOMETRIC_SHAPES_EXTENDED(Character.UnicodeBlock.GEOMETRIC_SHAPES_EXTENDED, 0x1f780, 0x1f7ff),
    GEORGIAN(Character.UnicodeBlock.GEORGIAN, 0x10a0, 0x10ff),
    GEORGIAN_EXTENDED(Character.UnicodeBlock.GEORGIAN_EXTENDED, 0x1c90, 0x1cbf),
    GEORGIAN_SUPPLEMENT(Character.UnicodeBlock.GEORGIAN_SUPPLEMENT, 0x2d00, 0x2d2f),
    GLAGOLITIC(Character.UnicodeBlock.GLAGOLITIC, 0x2c00, 0x2c5f),
    GLAGOLITIC_SUPPLEMENT(Character.UnicodeBlock.GLAGOLITIC_SUPPLEMENT, 0x1e000, 0x1e02f),
    GOTHIC(Character.UnicodeBlock.GOTHIC, 0x10330, 0x1034f),
    GRANTHA(Character.UnicodeBlock.GRANTHA, 0x11300, 0x1137f),
    GREEK_EXTENDED(Character.UnicodeBlock.GREEK_EXTENDED, 0x1f00, 0x1fff),
    GUJARATI(Character.UnicodeBlock.GUJARATI, 0x0a80, 0x0aff),
    GUNJALA_GONDI(Character.UnicodeBlock.GUNJALA_GONDI, 0x11d60, 0x11daf),
    GURMUKHI(Character.UnicodeBlock.GURMUKHI, 0x0a00, 0x0a7f),
    HALFWIDTH_AND_FULLWIDTH_FORMS(
        Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS, 0xff00, 0xffef),
    HANGUL_COMPATIBILITY_JAMO(Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO, 0x3130, 0x318f),
    HANGUL_JAMO(Character.UnicodeBlock.HANGUL_JAMO, 0x1100, 0x11ff),
    HANGUL_JAMO_EXTENDED_A(Character.UnicodeBlock.HANGUL_JAMO_EXTENDED_A, 0xa960, 0xa97f),
    HANGUL_JAMO_EXTENDED_B(Character.UnicodeBlock.HANGUL_JAMO_EXTENDED_B, 0xd7b0, 0xd7ff),
    HANGUL_SYLLABLES(Character.UnicodeBlock.HANGUL_SYLLABLES, 0xac00, 0xd7af),
    HANIFI_ROHINGYA(Character.UnicodeBlock.HANIFI_ROHINGYA, 0x10d00, 0x10d3f),
    HANUNOO(Character.UnicodeBlock.HANUNOO, 0x1720, 0x173f),
    HATRAN(Character.UnicodeBlock.HATRAN, 0x108e0, 0x108ff),
    HEBREW(Character.UnicodeBlock.HEBREW, 0x0590, 0x05ff),
    HIGH_PRIVATE_USE_SURROGATES(Character.UnicodeBlock.HIGH_PRIVATE_USE_SURROGATES, 0xdb80, 0xdbff),
    HIGH_SURROGATES(Character.UnicodeBlock.HIGH_SURROGATES, 0xd800, 0xdb7f),
    HIRAGANA(Character.UnicodeBlock.HIRAGANA, 0x3040, 0x309f),
    IDEOGRAPHIC_DESCRIPTION_CHARACTERS(
        Character.UnicodeBlock.IDEOGRAPHIC_DESCRIPTION_CHARACTERS, 0x2ff0, 0x2fff),
    IDEOGRAPHIC_SYMBOLS_AND_PUNCTUATION(
        Character.UnicodeBlock.IDEOGRAPHIC_SYMBOLS_AND_PUNCTUATION, 0x16fe0, 0x16fff),
    IMPERIAL_ARAMAIC(Character.UnicodeBlock.IMPERIAL_ARAMAIC, 0x10840, 0x1085f),
    INDIC_SIYAQ_NUMBERS(Character.UnicodeBlock.INDIC_SIYAQ_NUMBERS, 0x1ec70, 0x1ecbf),
    INSCRIPTIONAL_PAHLAVI(Character.UnicodeBlock.INSCRIPTIONAL_PAHLAVI, 0x10b60, 0x10b7f),
    INSCRIPTIONAL_PARTHIAN(Character.UnicodeBlock.INSCRIPTIONAL_PARTHIAN, 0x10b40, 0x10b5f),
    IPA_EXTENSIONS(Character.UnicodeBlock.IPA_EXTENSIONS, 0x0250, 0x02af),
    JAVANESE(Character.UnicodeBlock.JAVANESE, 0xa980, 0xa9df),
    KAITHI(Character.UnicodeBlock.KAITHI, 0x11080, 0x110cf),
    KANA_EXTENDED_A(Character.UnicodeBlock.KANA_EXTENDED_A, 0x1b100, 0x1b12f),
    KANA_SUPPLEMENT(Character.UnicodeBlock.KANA_SUPPLEMENT, 0x1b000, 0x1b0ff),
    KANBUN(Character.UnicodeBlock.KANBUN, 0x3190, 0x319f),
    KANGXI_RADICALS(Character.UnicodeBlock.KANGXI_RADICALS, 0x2f00, 0x2fdf),
    KANNADA(Character.UnicodeBlock.KANNADA, 0x0c80, 0x0cff),
    KATAKANA(Character.UnicodeBlock.KATAKANA, 0x30a0, 0x30ff),
    KATAKANA_PHONETIC_EXTENSIONS(
        Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS, 0x31f0, 0x31ff),
    KAYAH_LI(Character.UnicodeBlock.KAYAH_LI, 0xa900, 0xa92f),
    KHAROSHTHI(Character.UnicodeBlock.KHAROSHTHI, 0x10a00, 0x10a5f),
    KHITAN_SMALL_SCRIPT(Character.UnicodeBlock.KHITAN_SMALL_SCRIPT, 0x18b00, 0x18cff),
    KHMER(Character.UnicodeBlock.KHMER, 0x1780, 0x17ff),
    KHMER_SYMBOLS(Character.UnicodeBlock.KHMER_SYMBOLS, 0x19e0, 0x19ff),
    KHOJKI(Character.UnicodeBlock.KHOJKI, 0x11200, 0x1124f),
    KHUDAWADI(Character.UnicodeBlock.KHUDAWADI, 0x112b0, 0x112ff),
    LAO(Character.UnicodeBlock.LAO, 0x0e80, 0x0eff),
    LATIN_1_SUPPLEMENT(Character.UnicodeBlock.LATIN_1_SUPPLEMENT, 0x0080, 0x00ff),
    LATIN_EXTENDED_A(Character.UnicodeBlock.LATIN_EXTENDED_A, 0x0100, 0x017f),
    LATIN_EXTENDED_ADDITIONAL(Character.UnicodeBlock.LATIN_EXTENDED_ADDITIONAL, 0x1e00, 0x1eff),
    LATIN_EXTENDED_B(Character.UnicodeBlock.LATIN_EXTENDED_B, 0x0180, 0x024f),
    LATIN_EXTENDED_C(Character.UnicodeBlock.LATIN_EXTENDED_C, 0x2c60, 0x2c7f),
    LATIN_EXTENDED_D(Character.UnicodeBlock.LATIN_EXTENDED_D, 0xa720, 0xa7ff),
    LATIN_EXTENDED_E(Character.UnicodeBlock.LATIN_EXTENDED_E, 0xab30, 0xab6f),
    LEPCHA(Character.UnicodeBlock.LEPCHA, 0x1c00, 0x1c4f),
    LETTERLIKE_SYMBOLS(Character.UnicodeBlock.LETTERLIKE_SYMBOLS, 0x2100, 0x214f),
    LIMBU(Character.UnicodeBlock.LIMBU, 0x1900, 0x194f),
    LINEAR_A(Character.UnicodeBlock.LINEAR_A, 0x10600, 0x1077f),
    LINEAR_B_IDEOGRAMS(Character.UnicodeBlock.LINEAR_B_IDEOGRAMS, 0x10080, 0x100ff),
    LINEAR_B_SYLLABARY(Character.UnicodeBlock.LINEAR_B_SYLLABARY, 0x10000, 0x1007f),
    LISU(Character.UnicodeBlock.LISU, 0xa4d0, 0xa4ff),
    LISU_SUPPLEMENT(Character.UnicodeBlock.LISU_SUPPLEMENT, 0x11fb0, 0x11fbf),
    LOW_SURROGATES(Character.UnicodeBlock.LOW_SURROGATES, 0xdc00, 0xdfff),
    LYCIAN(Character.UnicodeBlock.LYCIAN, 0x10280, 0x1029f),
    LYDIAN(Character.UnicodeBlock.LYDIAN, 0x10920, 0x1093f),
    MAHAJANI(Character.UnicodeBlock.MAHAJANI, 0x11150, 0x1117f),
    MAHJONG_TILES(Character.UnicodeBlock.MAHJONG_TILES, 0x1f000, 0x1f02f),
    MAKASAR(Character.UnicodeBlock.MAKASAR, 0x11ee0, 0x11eff),
    MALAYALAM(Character.UnicodeBlock.MALAYALAM, 0x0d00, 0x0d7f),
    MANDAIC(Character.UnicodeBlock.MANDAIC, 0x0840, 0x085f),
    MANICHAEAN(Character.UnicodeBlock.MANICHAEAN, 0x10ac0, 0x10aff),
    MARCHEN(Character.UnicodeBlock.MARCHEN, 0x11c70, 0x11cbf),
    MASARAM_GONDI(Character.UnicodeBlock.MASARAM_GONDI, 0x11d00, 0x11d5f),
    MATHEMATICAL_ALPHANUMERIC_SYMBOLS(
        Character.UnicodeBlock.MATHEMATICAL_ALPHANUMERIC_SYMBOLS, 0x1d400, 0x1d7ff),
    MATHEMATICAL_OPERATORS(Character.UnicodeBlock.MATHEMATICAL_OPERATORS, 0x2200, 0x22ff),
    MAYAN_NUMERALS(Character.UnicodeBlock.MAYAN_NUMERALS, 0x1d2e0, 0x1d2ff),
    MEDEFAIDRIN(Character.UnicodeBlock.MEDEFAIDRIN, 0x16e40, 0x16e9f),
    MEETEI_MAYEK(Character.UnicodeBlock.MEETEI_MAYEK, 0xabc0, 0xabff),
    MEETEI_MAYEK_EXTENSIONS(Character.UnicodeBlock.MEETEI_MAYEK_EXTENSIONS, 0xaae0, 0xaaff),
    MENDE_KIKAKUI(Character.UnicodeBlock.MENDE_KIKAKUI, 0x1e800, 0x1e8df),
    MEROITIC_CURSIVE(Character.UnicodeBlock.MEROITIC_CURSIVE, 0x109a0, 0x109ff),
    MEROITIC_HIEROGLYPHS(Character.UnicodeBlock.MEROITIC_HIEROGLYPHS, 0x10980, 0x1099f),
    MIAO(Character.UnicodeBlock.MIAO, 0x16f00, 0x16f9f),
    MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A(
        Character.UnicodeBlock.MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A, 0x27c0, 0x27ef),
    MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B(
        Character.UnicodeBlock.MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B, 0x2980, 0x29ff),
    MISCELLANEOUS_SYMBOLS(Character.UnicodeBlock.MISCELLANEOUS_SYMBOLS, 0x2600, 0x26ff),
    MISCELLANEOUS_SYMBOLS_AND_ARROWS(
        Character.UnicodeBlock.MISCELLANEOUS_SYMBOLS_AND_ARROWS, 0x2b00, 0x2bff),
    MISCELLANEOUS_SYMBOLS_AND_PICTOGRAPHS(
        Character.UnicodeBlock.MISCELLANEOUS_SYMBOLS_AND_PICTOGRAPHS, 0x1f300, 0x1f5ff),
    MISCELLANEOUS_TECHNICAL(Character.UnicodeBlock.MISCELLANEOUS_TECHNICAL, 0x2300, 0x23ff),
    MODI(Character.UnicodeBlock.MODI, 0x11600, 0x1165f),
    MODIFIER_TONE_LETTERS(Character.UnicodeBlock.MODIFIER_TONE_LETTERS, 0xa700, 0xa71f),
    MONGOLIAN(Character.UnicodeBlock.MONGOLIAN, 0x1800, 0x18af),
    MONGOLIAN_SUPPLEMENT(Character.UnicodeBlock.MONGOLIAN_SUPPLEMENT, 0x11660, 0x1167f),
    MRO(Character.UnicodeBlock.MRO, 0x16a40, 0x16a6f),
    MULTANI(Character.UnicodeBlock.MULTANI, 0x11280, 0x112af),
    MUSICAL_SYMBOLS(Character.UnicodeBlock.MUSICAL_SYMBOLS, 0x1d100, 0x1d1ff),
    MYANMAR(Character.UnicodeBlock.MYANMAR, 0x1000, 0x109f),
    MYANMAR_EXTENDED_A(Character.UnicodeBlock.MYANMAR_EXTENDED_A, 0xaa60, 0xaa7f),
    MYANMAR_EXTENDED_B(Character.UnicodeBlock.MYANMAR_EXTENDED_B, 0xa9e0, 0xa9ff),
    NABATAEAN(Character.UnicodeBlock.NABATAEAN, 0x10880, 0x108af),
    NANDINAGARI(Character.UnicodeBlock.NANDINAGARI, 0x119a0, 0x119ff),
    NEWA(Character.UnicodeBlock.NEWA, 0x11400, 0x1147f),
    NEW_TAI_LUE(Character.UnicodeBlock.NEW_TAI_LUE, 0x1980, 0x19df),
    NKO(Character.UnicodeBlock.NKO, 0x07c0, 0x07ff),
    NUMBER_FORMS(Character.UnicodeBlock.NUMBER_FORMS, 0x2150, 0x218f),
    NUSHU(Character.UnicodeBlock.NUSHU, 0x1b170, 0x1b2ff),
    NYIAKENG_PUACHUE_HMONG(Character.UnicodeBlock.NYIAKENG_PUACHUE_HMONG, 0x1e100, 0x1e14f),
    OGHAM(Character.UnicodeBlock.OGHAM, 0x1680, 0x169f),
    OLD_HUNGARIAN(Character.UnicodeBlock.OLD_HUNGARIAN, 0x10c80, 0x10cff),
    OLD_ITALIC(Character.UnicodeBlock.OLD_ITALIC, 0x10300, 0x1032f),
    OLD_NORTH_ARABIAN(Character.UnicodeBlock.OLD_NORTH_ARABIAN, 0x10a80, 0x10a9f),
    OLD_PERMIC(Character.UnicodeBlock.OLD_PERMIC, 0x10350, 0x1037f),
    OLD_PERSIAN(Character.UnicodeBlock.OLD_PERSIAN, 0x103a0, 0x103df),
    OLD_SOGDIAN(Character.UnicodeBlock.OLD_SOGDIAN, 0x10f00, 0x10f2f),
    OLD_SOUTH_ARABIAN(Character.UnicodeBlock.OLD_SOUTH_ARABIAN, 0x10a60, 0x10a7f),
    OLD_TURKIC(Character.UnicodeBlock.OLD_TURKIC, 0x10c00, 0x10c4f),
    OL_CHIKI(Character.UnicodeBlock.OL_CHIKI, 0x1c50, 0x1c7f),
    OPTICAL_CHARACTER_RECOGNITION(
        Character.UnicodeBlock.OPTICAL_CHARACTER_RECOGNITION, 0x2440, 0x245f),
    ORIYA(Character.UnicodeBlock.ORIYA, 0x0b00, 0x0b7f),
    ORNAMENTAL_DINGBATS(Character.UnicodeBlock.ORNAMENTAL_DINGBATS, 0x1f650, 0x1f67f),
    OSAGE(Character.UnicodeBlock.OSAGE, 0x104b0, 0x104ff),
    OSMANYA(Character.UnicodeBlock.OSMANYA, 0x10480, 0x104af),
    OTTOMAN_SIYAQ_NUMBERS(Character.UnicodeBlock.OTTOMAN_SIYAQ_NUMBERS, 0x1ed00, 0x1ed4f),
    PAHAWH_HMONG(Character.UnicodeBlock.PAHAWH_HMONG, 0x16b00, 0x16b8f),
    PALMYRENE(Character.UnicodeBlock.PALMYRENE, 0x10860, 0x1087f),
    PAU_CIN_HAU(Character.UnicodeBlock.PAU_CIN_HAU, 0x11ac0, 0x11aff),
    PHAGS_PA(Character.UnicodeBlock.PHAGS_PA, 0xa840, 0xa87f),
    PHAISTOS_DISC(Character.UnicodeBlock.PHAISTOS_DISC, 0x101d0, 0x101ff),
    PHOENICIAN(Character.UnicodeBlock.PHOENICIAN, 0x10900, 0x1091f),
    PHONETIC_EXTENSIONS(Character.UnicodeBlock.PHONETIC_EXTENSIONS, 0x1d00, 0x1d7f),
    PHONETIC_EXTENSIONS_SUPPLEMENT(
        Character.UnicodeBlock.PHONETIC_EXTENSIONS_SUPPLEMENT, 0x1d80, 0x1dbf),
    PLAYING_CARDS(Character.UnicodeBlock.PLAYING_CARDS, 0x1f0a0, 0x1f0ff),
    PRIVATE_USE_AREA(Character.UnicodeBlock.PRIVATE_USE_AREA, 0xe000, 0xf8ff),
    PSALTER_PAHLAVI(Character.UnicodeBlock.PSALTER_PAHLAVI, 0x10b80, 0x10baf),
    REJANG(Character.UnicodeBlock.REJANG, 0xa930, 0xa95f),
    RUMI_NUMERAL_SYMBOLS(Character.UnicodeBlock.RUMI_NUMERAL_SYMBOLS, 0x10e60, 0x10e7f),
    RUNIC(Character.UnicodeBlock.RUNIC, 0x16a0, 0x16ff),
    SAMARITAN(Character.UnicodeBlock.SAMARITAN, 0x0800, 0x083f),
    SAURASHTRA(Character.UnicodeBlock.SAURASHTRA, 0xa880, 0xa8df),
    SHARADA(Character.UnicodeBlock.SHARADA, 0x11180, 0x111df),
    SHAVIAN(Character.UnicodeBlock.SHAVIAN, 0x10450, 0x1047f),
    SHORTHAND_FORMAT_CONTROLS(Character.UnicodeBlock.SHORTHAND_FORMAT_CONTROLS, 0x1bca0, 0x1bcaf),
    SIDDHAM(Character.UnicodeBlock.SIDDHAM, 0x11580, 0x115ff),
    SINHALA(Character.UnicodeBlock.SINHALA, 0x0d80, 0x0dff),
    SINHALA_ARCHAIC_NUMBERS(Character.UnicodeBlock.SINHALA_ARCHAIC_NUMBERS, 0x111e0, 0x111ff),
    SMALL_FORM_VARIANTS(Character.UnicodeBlock.SMALL_FORM_VARIANTS, 0xfe50, 0xfe6f),
    SMALL_KANA_EXTENSION(Character.UnicodeBlock.SMALL_KANA_EXTENSION, 0x1b130, 0x1b16f),
    SOGDIAN(Character.UnicodeBlock.SOGDIAN, 0x10f30, 0x10f6f),
    SORA_SOMPENG(Character.UnicodeBlock.SORA_SOMPENG, 0x110d0, 0x110ff),
    SOYOMBO(Character.UnicodeBlock.SOYOMBO, 0x11a50, 0x11aaf),
    SPACING_MODIFIER_LETTERS(Character.UnicodeBlock.SPACING_MODIFIER_LETTERS, 0x02b0, 0x02ff),
    SPECIALS(Character.UnicodeBlock.SPECIALS, 0xfff0, 0xffff),
    SUNDANESE(Character.UnicodeBlock.SUNDANESE, 0x1b80, 0x1bbf),
    SUNDANESE_SUPPLEMENT(Character.UnicodeBlock.SUNDANESE_SUPPLEMENT, 0x1cc0, 0x1ccf),
    SUPERSCRIPTS_AND_SUBSCRIPTS(Character.UnicodeBlock.SUPERSCRIPTS_AND_SUBSCRIPTS, 0x2070, 0x209f),
    SUPPLEMENTAL_ARROWS_A(Character.UnicodeBlock.SUPPLEMENTAL_ARROWS_A, 0x27f0, 0x27ff),
    SUPPLEMENTAL_ARROWS_B(Character.UnicodeBlock.SUPPLEMENTAL_ARROWS_B, 0x2900, 0x297f),
    SUPPLEMENTAL_ARROWS_C(Character.UnicodeBlock.SUPPLEMENTAL_ARROWS_C, 0x1f800, 0x1f8ff),
    SUPPLEMENTAL_MATHEMATICAL_OPERATORS(
        Character.UnicodeBlock.SUPPLEMENTAL_MATHEMATICAL_OPERATORS, 0x2a00, 0x2aff),
    SUPPLEMENTAL_PUNCTUATION(Character.UnicodeBlock.SUPPLEMENTAL_PUNCTUATION, 0x2e00, 0x2e7f),
    SUPPLEMENTAL_SYMBOLS_AND_PICTOGRAPHS(
        Character.UnicodeBlock.SUPPLEMENTAL_SYMBOLS_AND_PICTOGRAPHS, 0x1f900, 0x1f9ff),
    SUPPLEMENTARY_PRIVATE_USE_AREA_A(
        Character.UnicodeBlock.SUPPLEMENTARY_PRIVATE_USE_AREA_A, 0xf0000, 0xfffff),
    SUPPLEMENTARY_PRIVATE_USE_AREA_B(
        Character.UnicodeBlock.SUPPLEMENTARY_PRIVATE_USE_AREA_B, 0x100000, 0x10ffff),
    SUTTON_SIGNWRITING(Character.UnicodeBlock.SUTTON_SIGNWRITING, 0x1d800, 0x1daaf),
    SYLOTI_NAGRI(Character.UnicodeBlock.SYLOTI_NAGRI, 0xa800, 0xa82f),
    SYMBOLS_AND_PICTOGRAPHS_EXTENDED_A(
        Character.UnicodeBlock.SYMBOLS_AND_PICTOGRAPHS_EXTENDED_A, 0x1fa70, 0x1faff),
    SYMBOLS_FOR_LEGACY_COMPUTING(
        Character.UnicodeBlock.SYMBOLS_FOR_LEGACY_COMPUTING, 0x1fb00, 0x1fbff),
    SYRIAC(Character.UnicodeBlock.SYRIAC, 0x0700, 0x074f),
    SYRIAC_SUPPLEMENT(Character.UnicodeBlock.SYRIAC_SUPPLEMENT, 0x0860, 0x086f),
    TAGALOG(Character.UnicodeBlock.TAGALOG, 0x1700, 0x171f),
    TAGBANWA(Character.UnicodeBlock.TAGBANWA, 0x1760, 0x177f),
    TAGS(Character.UnicodeBlock.TAGS, 0xe0000, 0xe007f),
    TAI_LE(Character.UnicodeBlock.TAI_LE, 0x1950, 0x197f),
    TAI_THAM(Character.UnicodeBlock.TAI_THAM, 0x1a20, 0x1aaf),
    TAI_VIET(Character.UnicodeBlock.TAI_VIET, 0xaa80, 0xaadf),
    TAI_XUAN_JING_SYMBOLS(Character.UnicodeBlock.TAI_XUAN_JING_SYMBOLS, 0x1d300, 0x1d35f),
    TAKRI(Character.UnicodeBlock.TAKRI, 0x11680, 0x116cf),
    TAMIL(Character.UnicodeBlock.TAMIL, 0x0b80, 0x0bff),
    TAMIL_SUPPLEMENT(Character.UnicodeBlock.TAMIL_SUPPLEMENT, 0x11fc0, 0x11fff),
    TANGUT(Character.UnicodeBlock.TANGUT, 0x17000, 0x187ff),
    TANGUT_COMPONENTS(Character.UnicodeBlock.TANGUT_COMPONENTS, 0x18800, 0x18aff),
    TANGUT_SUPPLEMENT(Character.UnicodeBlock.TANGUT_SUPPLEMENT, 0x18d00, 0x18d7f),
    TELUGU(Character.UnicodeBlock.TELUGU, 0x0c00, 0x0c7f),
    THAANA(Character.UnicodeBlock.THAANA, 0x0780, 0x07bf),
    THAI(Character.UnicodeBlock.THAI, 0x0e00, 0x0e7f),
    TIBETAN(Character.UnicodeBlock.TIBETAN, 0x0f00, 0x0fff),
    TIFINAGH(Character.UnicodeBlock.TIFINAGH, 0x2d30, 0x2d7f),
    TIRHUTA(Character.UnicodeBlock.TIRHUTA, 0x11480, 0x114df),
    TRANSPORT_AND_MAP_SYMBOLS(Character.UnicodeBlock.TRANSPORT_AND_MAP_SYMBOLS, 0x1f680, 0x1f6ff),
    UGARITIC(Character.UnicodeBlock.UGARITIC, 0x10380, 0x1039f),
    UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS(
        Character.UnicodeBlock.UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS, 0x1400, 0x167f),
    UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_EXTENDED(
        Character.UnicodeBlock.UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_EXTENDED, 0x18b0, 0x18ff),
    VAI(Character.UnicodeBlock.VAI, 0xa500, 0xa63f),
    VARIATION_SELECTORS(Character.UnicodeBlock.VARIATION_SELECTORS, 0xfe00, 0xfe0f),
    VARIATION_SELECTORS_SUPPLEMENT(
        Character.UnicodeBlock.VARIATION_SELECTORS_SUPPLEMENT, 0xe0100, 0xe01ef),
    VEDIC_EXTENSIONS(Character.UnicodeBlock.VEDIC_EXTENSIONS, 0x1cd0, 0x1cff),
    VERTICAL_FORMS(Character.UnicodeBlock.VERTICAL_FORMS, 0xfe10, 0xfe1f),
    WANCHO(Character.UnicodeBlock.WANCHO, 0x1e2c0, 0x1e2ff),
    WARANG_CITI(Character.UnicodeBlock.WARANG_CITI, 0x118a0, 0x118ff),
    YEZIDI(Character.UnicodeBlock.YEZIDI, 0x10e80, 0x10ebf),
    YIJING_HEXAGRAM_SYMBOLS(Character.UnicodeBlock.YIJING_HEXAGRAM_SYMBOLS, 0x4dc0, 0x4dff),
    YI_RADICALS(Character.UnicodeBlock.YI_RADICALS, 0xa490, 0xa4cf),
    YI_SYLLABLES(Character.UnicodeBlock.YI_SYLLABLES, 0xa000, 0xa48f),
    ZANABAZAR_SQUARE(Character.UnicodeBlock.ZANABAZAR_SQUARE, 0x11a00, 0x11a4f);

    private static final SecureRandom secureRandom = new SecureRandom();

    public final Character.UnicodeBlock block;
    public final char first;
    public final char last;

    UnicodeBlockRange(Character.UnicodeBlock block, int first, int last) {
      this.block = block;
      this.first = (char) first;
      this.last = (char) last;
    }

    public static UnicodeBlockRange of(Character.UnicodeBlock block) {
      for (UnicodeBlockRange range : values()) {
        if (range.block == block) {
          return range;
        }
      }
      return null;
    }

    public char randomChar() {
      return (char) secureRandom.nextInt(first, last + 1);
    }
  }
}
