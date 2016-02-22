package com.converter;

/**
 * Created by val on 2/21/2016.
 */
public interface Converter<I, T> {
    public T get(I input);
}
