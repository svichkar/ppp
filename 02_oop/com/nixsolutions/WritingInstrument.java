package com.nixsolutions;

import java.math.BigDecimal;

/** Parent class for other classes describing pen, pencil, and felt-tip pen. */
abstract class WritingInstrument {
  protected String type;
  protected boolean prepared;
  protected boolean canErase;
  protected boolean lowOnPigment;
  protected BigDecimal pigmentLeftPercent = new BigDecimal("100");
  protected BigDecimal pigmentPercentPerChar;
  protected int charsWritten;
  protected int index;

  /** Does the initialization of the item, assigns the default values. */
  protected abstract void initialize();

  /** Performs preparation of the item, so it is ready to work */
  protected abstract void prepare();

  /**
   * Writes the passed StringBuilder, performs checks and changes 
   * according to the behavior of the item.
   * @param sb StringBuilder to write by the item.
   */
  protected abstract void write(StringBuilder sb);

  /**
   * Erases the last character of the string passed as a parameter.
   * @param sb StringBuilder to remove the last character from.
   */
  protected abstract void erase(StringBuilder sb);

  /**
   * Returns the type of the item.
   * @return Returns the type of the item from the appropriate String field of
   * the object.
   */
  protected String returnType() {
    return type;
  }

}
