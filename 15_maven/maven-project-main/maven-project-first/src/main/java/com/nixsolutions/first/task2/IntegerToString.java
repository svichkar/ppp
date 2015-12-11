package com.nixsolutions.first.task2;

/**
 * Created by svichkar on 12/3/2015.
 */
public class IntegerToString implements Converter<Integer, String> {

    /**
     * overrided method get
     * which converts Integer to String by concatenating separate digits
     *
     * @param from - Integer value
     * @return String
     */
    @Override
    public String get(Integer from) {

        String result = "";
        char[] chars = from.toString().toCharArray();

        for (char digit : chars) {
            result += digit + " ";
        }
        return result.trim();
    }
}
