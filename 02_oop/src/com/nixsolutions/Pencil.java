package com.nixsolutions;

/**
 * Created  by pantiukhin on 2/1/2016
 */
public class Pencil extends WritingInstrument {
    private float percentage = 100f;
    private final float TOBESUBTRACTED = 0.95f;

    /**
     * Writes characters by invoking the corresponding write() method of the super class Also,
     * calculates the percent of the remaining writing material
     *
     * @param builder - StringBuilder used for writing strings
     */
    @Override
    public void write(StringBuilder builder) {
        super.write(builder);
        if (builder.length() % 20 == 0) {
            this.percentage -= 3f;
        }
        erase(builder);
        setPercentage();
    }

    /**
     * Returns the percent of the remaining writing material
     *
     * @return - float denoting the percent of the remaining writing material
     */
    public float getPercentage() {
        return percentage;
    }

    public void setPercentage() {
        percentage = (float) Math.round(percentage - (TOBESUBTRACTED * 5));
    }
}
