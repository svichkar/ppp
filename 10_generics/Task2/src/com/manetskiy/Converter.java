package com.manetskiy;

public interface Converter<I, T> {
    T get(I type);
}
