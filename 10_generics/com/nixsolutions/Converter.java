package com.nixsolutions;

/**
 * Created by Serko on 26.11.2015.
 */
public interface Converter <T, I> {
    T get(I a);
}
