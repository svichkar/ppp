package com.nixsolutions.converter;

import java.util.ArrayList;

/**
 * @author Mikhail Sirotkin
 * Class implements Converter interfase.
 * Input value - Integer[], output - String
 * Contains overrided get method that returns String with all integers in text format separated by space
 */
public class ConvertArrayOfIntegersToString implements Converter<String, Integer[]> {

    /**
     * Method returns String with all integers in text format separated by space
     * @param intArr
     * @return
     */
    @Override
    public String get(Integer[] intArr){
        StringBuilder outResult = new StringBuilder();
        for (Integer integer : intArr) {
            outResult.append(integer.toString() + " ");
        }
        return outResult.toString().trim();
    }
}
