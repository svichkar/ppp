package com.nixsolutions.generics2;

import java.util.Arrays;

/** Converter for the array of Integer to a String. */
public class IntegerArrayToString implements Converter<String, Integer[]> {

    /** Converts the array of Integer to a String, space used as a delimiter. */
    @Override
    public String get(Integer[] integerArray) {
        String string = "";
        for (Integer element : integerArray) {
            string = (string + " " + element);
        }
        return string;
    }

    /** Example of usage of the converter method. */
    public static void main(String[] args) {
        Integer[] integerArray = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        IntegerArrayToString integerArrayToString = new IntegerArrayToString();

        System.out.println("Original array: " + Arrays.toString(integerArray));
        System.out.println("String representation of this array: "
                + integerArrayToString.get(integerArray));
    }

}
