package com.nixsolutions;

/**
 * Created by Serko on 26.11.2015.
 */
public class IntegerToString implements Converter<String, Integer[]> {
    @Override
    public String get(Integer[] a) {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i <a.length ; i++) {
            temp.append(a[i]);
            temp.append(' ');
        }
        return temp.toString();
    }
}
