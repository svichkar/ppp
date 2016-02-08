package com.nixsolutions;

import java.math.BigDecimal;

/**
 * Represents the pen. Usage of the ink per written character is contant.
 * Has a button, representing the enabled / disabled state
 */
public class Pen extends WritingInstrument {
  private final BigDecimal PERCENT_PER_CHAR = new BigDecimal("1.15");

  @Override
  protected void initialize() {
    type = "Pen";
  }

  /** 
   * Calls the method to push the button, in order to prepare the pen to work.
   */
  @Override
  protected void prepare() {
    pushTheButton();
  }

  /**
   * Pushes the button on the pen, this is reflected by flipping the 
   * value of prepared field. 
   */
  protected void pushTheButton() {
    prepared = !prepared;
  }

  @Override
  protected void write(StringBuilder sb) {
    pigmentPercentPerChar = PERCENT_PER_CHAR;
    for (int i = 0; i < sb.length(); i++) {
      if (pigmentLeftPercent.compareTo(pigmentPercentPerChar) >= 0) {
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

  /** Cannot erase, so the method body is empty. */
  @Override
  protected void erase(StringBuilder sb) {}

}
