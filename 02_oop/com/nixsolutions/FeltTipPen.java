package com.nixsolutions;

import java.math.BigDecimal;

/**
 * Represents the felt-tip pen. Uses more pigment when more characters written.
 */
class FeltTipPen extends WritingInstrument {
  private final BigDecimal PERCENT_LVL_1 = new BigDecimal("1");
  private final BigDecimal PERCENT_LVL_2 = new BigDecimal("1.09");
  private final BigDecimal PERCENT_LVL_3 = new BigDecimal("1.21");
  private final int LVL_1_MAX = 20; // TODO: Ambiguity in the requirement, need to clarify
  private final int LVL_2_MAX = 40;

  @Override
  protected void initialize() {
    type = "Felt-tip Pen";
  }

  /** 
   * Calls the local method to ensure that the cap removed, so that 
   * the item is able to write.
   */
  @Override
  protected void prepare() {
    ensureCapRemoved();
  }

  /** Cap is attached by default. Must be removed before use. */ 
  protected boolean capRemoved = false;

  /** Ensures the cap is removed. Removes if it is still attached. */
  protected void ensureCapRemoved() {
    capRemoved = true;
    if (capRemoved) {
      prepared = true;
    }
  }

  @Override
  protected void write(StringBuilder sb) {
    setPercent();
    for (int i = 0; i < sb.length(); i++) {
      if (pigmentLeftPercent.compareTo(pigmentPercentPerChar) >= 0) {
        setPercent();
        System.out.print(sb.charAt(i));
        pigmentLeftPercent = pigmentLeftPercent.subtract(pigmentPercentPerChar);
        charsWritten++;
      } else {
        System.out.print("\n" + type + ": Sorry, cannot complete the character,"
            + " there's not enough pigment left");
        lowOnPigment = true;
        break;
      }
    }

  }

  /**
   * Sets the percentage of used pigment per character written, according
   * to the specified logic of the item.
   */
  protected void setPercent() {
    if (charsWritten < LVL_1_MAX) {
      pigmentPercentPerChar = PERCENT_LVL_1;
    } else if (charsWritten >= LVL_1_MAX && charsWritten < LVL_2_MAX) {
      pigmentPercentPerChar = PERCENT_LVL_2;
    } else if (charsWritten >= LVL_2_MAX) {
      pigmentPercentPerChar = PERCENT_LVL_3;
    }
  }

  /** Cannot erase, so the body of the method is empty. */
  @Override
  protected void erase(StringBuilder sb) {}


}
