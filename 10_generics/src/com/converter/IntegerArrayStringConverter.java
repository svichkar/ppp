package com.converter;

import java.util.Iterator;

/**
 * Created by val on 2/21/2016.
 */

public class IntegerArrayStringConverter<I, T> implements Converter<I, T> {
    private StringBuilder sb = new StringBuilder();

    @Override
    public T get(I i) {
        T s = (T) "";
        I[] arr = (I[]) i;
        for (int y = 0; y < arr.length; y++) {
            sb.append(arr[y].toString() + " ");
            s = (T) sb.toString();
        }
        return s;
    }
}

