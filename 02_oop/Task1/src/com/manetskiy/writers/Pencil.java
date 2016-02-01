package com.manetskiy.writers;


public class Pencil extends SimpleWriter {

    public Pencil(float initialDurability) {
        super(initialDurability);
    }

    /**
     * Reduces durability of pencil on 0.95% for first char of input string.
     * Also reduces durability for 3% for each 20th char of input string.
     * If durability cannot be reduced, returned string will be truncated.
     *
     * @param text StringBuilder object
     * @return StringBuilder object
     */
    public StringBuilder write(StringBuilder text) {
        StringBuilder toReturn = new StringBuilder();
        int interval = 20;
        for (int i = 0; i < text.length(); i++) {
            if (i == 0) {
                if ((getDurability() - 0.95) > 0.01) {
                    reduceDurability((float) (getDurability() - 0.95));
                } else break;
            }
            if (interval == (i + 1)) {
                if ((getDurability() - 3) > 0.1) {
                    reduceDurability((getDurability() - 3));
                    interval += 20;
                } else break;
            }
            toReturn.append(text.charAt(i));
        }
        return toReturn;
    }

    public StringBuilder erase(StringBuilder text, int charIndex) {
        return text.deleteCharAt(charIndex);
    }
}
