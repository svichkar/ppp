package com.nixsolutions;

public interface Converter<T, I> {
    public T get(I input);
}
