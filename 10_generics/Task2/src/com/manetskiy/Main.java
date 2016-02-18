package com.manetskiy;

public class Main {
    public static void main(String[] args) {
        Float f = 12.223f;
        Integer[] integers = new Integer[]{1, 2, 3, 4, 5};

        FloatToDouble floatToDouble = new FloatToDouble();
        IntegerArrayToString integerArrayToString = new IntegerArrayToString();

        Double d = floatToDouble.get(f);
        String s = integerArrayToString.get(integers);

        System.out.println(d);
        System.out.println(s);
    }
}
