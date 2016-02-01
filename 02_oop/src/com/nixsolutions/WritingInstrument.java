package com.nixsolutions;

/**
 * Created  by pantiukhin on 2/1/2016
 */
public abstract class WritingInstrument {
    /**
     * Abstract method used to determine the percent of the remaining writing material
     * Implemented in the subclasses
     * @return - returns a float value that denotes the percent of the remaining writing material
     */
    public abstract float getPercentage();

    /**
     * Writes five characters
     * @param inputString - string used for writing
     */
    public void write(StringBuilder inputString) {
        for(int i = 0; i < 5; ++i) {
            inputString.append('a');
        }
    }

    /**
     * Deletes one character by instruments that can do it
     * @param inputString - string used for writing
     */
    public void erase(StringBuilder inputString) {
        inputString.deleteCharAt(inputString.length() - 1);
    }
}
