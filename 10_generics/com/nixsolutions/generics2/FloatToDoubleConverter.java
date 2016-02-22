package com.nixsolutions.generics2;

import java.util.Random;

public class FloatToDoubleConverter implements Converter<Double, Float> {

    /** Converts the input Float value to a Double value. */
    @Override
    public Double get(Float floatValue) {
        return floatValue.doubleValue();
    }

    /** Example of usage of the converter method. */
    public static void main(String[] args) {
        Random random = new Random();
        Float number = random.nextFloat();
        FloatToDoubleConverter floatToDouble = new FloatToDoubleConverter();
        
        System.out.println("Original Float value: " + number);
        System.out.println(" converted to Double: " + floatToDouble.get(number));
    }
}
