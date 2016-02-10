package com.nixsolutions;

/**
 * Created  by pantiukhin on 2/1/2016
 */
public class Marker extends WritingInstrument {
    private float percentage = 100f;
    private float toBeSubtracted = 1f;

    /**
     * Writes characters by invoking the corresponding write() method of the super class Also,
     * calculates the percent of the remaining writing material
     *
     * @param builder - StringBuilder used for writing strings
     */
    @Override
    public void write(StringBuilder builder) {
        super.write(builder);
        if (builder.length() >= 20 && builder.length() <= 40) {
            toBeSubtracted = 1.9f;
        }

        if (builder.length() > 40) {
            toBeSubtracted = 1.21f;
        }
        this.setPercentage();
    }

    /**
     * Returns the percent of the remaining writing material
     *
     * @return - float denoting the percent of the remaining writing material
     */
    public float getPercentage() {
        return percentage;
    }

    /**
     * Calculates the remaining percent of the writing material
     */
    public void setPercentage() {
        percentage = (float) Math.round(percentage - (toBeSubtracted * 5));
    }
}
