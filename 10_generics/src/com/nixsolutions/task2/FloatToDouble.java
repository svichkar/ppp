package com.nixsolutions.task2;

/**
 * Created by svichkar on 12/3/2015.
 */
public class FloatToDouble implements Converter<Float, Double> {

    /**
     * overrided get method
     * which converts Float to Double
     *
     * @param from - Float value
     * @return Double
     */
    @Override
    public Double get(Float from) {

        return Double.valueOf(from);
    }
}
