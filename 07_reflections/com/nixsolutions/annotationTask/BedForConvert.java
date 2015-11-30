package com.nixsolutions.annotationtask;

/**
 * Created by rybkinrolla on 27.11.2015.
 */
public class BedForConvert {
    private String brandName;
    @ToString
    private int width;
    @ToString
    private int length;
    @ToString
    private float height;

    public BedForConvert(String brandName, int width, int length, float height) {
        this.brandName = brandName;
        this.width = width;
        this.length = length;
        this.height = height;
    }

}
