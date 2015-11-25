package com.nixsolutions;

/**
 * Created by Rybkinrolla on 25.11.2015.
 */
public interface Converter<I,T> {
    public T get(I i);
}
