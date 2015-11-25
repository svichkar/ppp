package com.nixsolutions;

/**
 * Created by Rybkinrolla on 25.11.2015.
 */
public class IntegerToStringConvert implements Converter<Integer[],String>{
    @Override
    public String get(Integer[] arrOfInt) {
        StringBuilder builder = new StringBuilder();
        for (int i : arrOfInt) {
            builder.append(i);
            builder.append(" ");
        }
        return builder.toString().trim();
    }
}
