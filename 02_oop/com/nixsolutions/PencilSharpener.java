package com.nixsolutions;

/** Represents the pencil sharpener. */
public class PencilSharpener {
  /**
   * Sharpens the pen received as a parameter.
   * Sets the prepared flag to true when done.
   * @param item Pencil to sharpen.
   */
  protected static void sharpen(Pencil item) {
    item.pigmentLeftPercent = item.pigmentLeftPercent.subtract(item.sharpeningRate);
    item.prepared = true;
  }
}
