package com.nixsolutions.converter;

/**
 * @author Mikhail Sirotkin
 */
public interface Converter<T, I> {
    public T get(I i);
}