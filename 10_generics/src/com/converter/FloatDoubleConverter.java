package com.converter;

/**
 * Created by val on 2/21/2016.
 */
public class FloatDoubleConverter<I, T> implements Converter<I, T> {
    @Override
    public T get(I i) {
        return (T) i;
    }
}
