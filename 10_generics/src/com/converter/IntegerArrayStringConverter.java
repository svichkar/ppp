package com.converter;


/**
 * Created by val on 2/21/2016.
 */

public class IntegerArrayStringConverter<I, T> implements Converter<I, T> {
    @Override
    public T get(I i) {
        StringBuilder sb = new StringBuilder();
        I[] arr = (I[]) i;
        for (int y = 0; y < arr.length; y++) {
            sb.append(arr[y].toString() + " ");
        }
        return (T) sb.toString();
    }
}

