package com.nixsolutions;

/**
 * Created by sobolenko on 2/18/2016.
 */
public interface Converter<T, I> {
    T get(I value);
}
