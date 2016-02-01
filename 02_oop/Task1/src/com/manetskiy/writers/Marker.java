package com.manetskiy.writers;

public class Marker extends SimpleWriter {

    public Marker(float initialDurability) {
        super(initialDurability);
    }

    /**
     * Reduces durability of marker on 1% for first 20 chars,
     * for 1.09% from 20th to 40th chars, for 1.21% from 40th char to inputted string length-1.
     * If durability cannot be reduced, returned string will be truncated.
     *
     * @param text StringBuilder object
     * @return StringBuilder object
     */
    public StringBuilder write(StringBuilder text) {
        StringBuilder toReturn = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (i < 20) {
                if ((getDurability() - 1) > 0.01) {
                    reduceDurability(getDurability() - 1);
                } else break;
            }
            if ((i > 19) && (i <= 39)) {
                if ((getDurability() - 1.09) > 0.01) {
                    reduceDurability((float) (getDurability() - 1.09));
                } else break;
            }
            if (i > 39) {
                if ((getDurability() - 1.21) > 0.01) {
                    reduceDurability((float) (getDurability() - 1.21));
                } else break;
            }
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
