package com.nixsolutions;

import java.math.BigDecimal;

/**
 * Represents the pencil. Uses more pigment when more characters written.
 * Should be sharpened after the specified amount of characters written.
 */
public class Pencil extends WritingInstrument {
  private final BigDecimal PERCENT_PER_CHAR = new BigDecimal("0.95");
  protected BigDecimal sharpeningRate = new BigDecimal("3");

  @Override
  protected void initialize() {
    type = "Pencil";
    canErase = true;
  }

  /**
   * Calls the sharpener to sharp the item.
   */
  @Override
  protected void prepare() {
    PencilSharpener.sharpen(this);
  }

  @Override
  protected void write(StringBuilder sb) {
    pigmentPercentPerChar = PERCENT_PER_CHAR;
    for (int i = 0; i < sb.length(); i++) {
      if (pigmentLeftPercent.compareTo(pigmentPercentPerChar) >= 0) {
        System.out.print(sb.charAt(i));
        pigmentLeftPercent = pigmentLeftPercent.subtract(pigmentPercentPerChar);
        charsWritten++;
        if (charsWritten % 20 == 0) {
          PencilSharpener.sharpen(this);
        }
      } else {
        System.out.print("\n" + type + ": Sorry, cannot complete the character,"
            + " there's not enough pigment left");
        lowOnPigment = true;
        break;
      }
    }

  }

  @Override
  protected void erase(StringBuilder sb) {
    if (sb.length() >= 1) {
      System.out.println(sb.deleteCharAt(sb.length() - 1));
    } else {
      System.out
          .println("\n" + type + ": Sorry, passed string has no characters, nothing to erase");
    }
  }

}
