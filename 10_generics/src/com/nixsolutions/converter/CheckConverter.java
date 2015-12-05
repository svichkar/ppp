package com.nixsolutions.converter;

/**
 * @author Mikhail Sirotkin
 * Class contains examples of using custom converter classes (check results of overrided method get)
 */
public class CheckConverter {
    public static void main(String[] args){

        ConvertFloatToDouble converter1 = new ConvertFloatToDouble();
        Float fl = 5.5f;
        System.out.println("Convert '" + fl + "' to double. Result:");
        System.out.println(converter1.get(fl));

        ConvertArrayOfIntegersToString converter2 = new ConvertArrayOfIntegersToString();
        Integer[] intArr = new Integer[5];
        intArr[0] = 1;
        intArr[1] = 2;
        intArr[2] = 3;
        intArr[3] = 4;
        intArr[4] = 5;
        System.out.println("Convert array of Integers to single string. Result:");
        System.out.println(converter2.get(intArr));
    }
}
