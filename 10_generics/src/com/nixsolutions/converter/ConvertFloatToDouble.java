package com.nixsolutions.converter;

/**
 * @author Sirotkin Mikhail
 * Class implements Converter interfase. Contains overrided get method.
 * Input value - Float, output - Double
 */
public class ConvertFloatToDouble implements Converter<Double, Float> {
    @Override
    public Double get(Float number){
        return number.doubleValue();
    }
}
