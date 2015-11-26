package com.nixsolutions;

/**
 * Created by Serko on 26.11.2015.
 */
public class GenericTask2 {
    public static void main(String[] args) {
        float input = 3.14f;
        FloatToDouble test = new FloatToDouble();
        System.out.println(test.get(input).getClass().getCanonicalName());

        Integer[] array = new Integer[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        IntegerToString test2 = new IntegerToString();
        System.out.println(test2.get(array).getClass().getCanonicalName() + "/" + test2.get(array));
    }
}
