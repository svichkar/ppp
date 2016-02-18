package com.manetskiy;

import java.util.Arrays;

public class IntegerArrayToString implements Converter<Integer[], String> {

    @Override
    public String get(Integer[] array) {
        return Arrays.deepToString(array).replaceAll("[\\[,\\]]", " ").trim();
    }
}
