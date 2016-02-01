package com.manetskiy.writers;

public class Pen extends SimpleWriter {

    public Pen(float initialDurability) {
        super(initialDurability);
    }

    /**
     * Reduces durability of pen on 1.15% for each char of input string.
     * If durability cannot be reduced, returned string will be truncated.
     *
     * @param text StringBuilder object
     * @return StringBuilder object
     */
    public StringBuilder write(StringBuilder text) {
        StringBuilder toReturn = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if ((getDurability() - 1.15) > 0.01) {
                reduceDurability((float) (getDurability() - 1.15));
            } else break;
            toReturn.append(text.charAt(i));
        }
        return toReturn;
    }

    /**
     * Returns the same string as input string. No chars will be deleted.
     *
     * @param text  StringBuilder object
     * @param index char at specified position
     * @return the same as inputted StringBuilder object
     */
    public StringBuilder erase(StringBuilder text, int index) {
        return text;
    }
}
