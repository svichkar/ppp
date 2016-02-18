package com.nixsolutions;

public class ConverterArrayIntegerToString implements Converter<String, Integer[]> {

    @Override
    public String get(Integer[] integers) {
        String ret = "";
        for (int i : integers) {
            ret += i + " ";
        }
        return ret;
    }
}
