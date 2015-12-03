package com.nixsolutions.task2;

/**
 * Created by svichkar on 12/3/2015.
 */
public class Main {

    public static void main(String args[]) {

        FloatToDouble floatToDouble = new FloatToDouble();
        Float f = new Float(125.0549);
        Double d = floatToDouble.get(f);
        System.out.println(String.format("Float: %s (type - %s); Double: %s (type - %s)", f, f.getClass().getSimpleName(), d, d.getClass().getSimpleName()));

        IntegerToString integerToString = new IntegerToString();
        Integer i = new Integer(12697);
        String s = integerToString.get(i);
        System.out.println(String.format("Integer: %s (type - %s); String: \"%s\" (type - %s)", i, i.getClass().getSimpleName(), s, s.getClass().getSimpleName()));
    }
}
