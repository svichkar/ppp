package com.nixsolutions;

public interface Converter<T, I> {

    T get(I i);

}
