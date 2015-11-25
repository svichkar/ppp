package com.nixsolutions;

/**
 * Created by Rybkinrolla on 25.11.2015.
 */
public class ConverterTest {
    public static void main(String[] args) {
        Integer[] intArr = new Integer[]{1,5,6,7,8};
        IntegerToStringConvert itsc = new IntegerToStringConvert();
        System.out.println(itsc.get(intArr));
        FloatToDoubleConvert ftdc = new FloatToDoubleConvert();
        Double fnum = ftdc.get(15f);
        System.out.println(fnum.getClass() + " - " + fnum);
    }
}
