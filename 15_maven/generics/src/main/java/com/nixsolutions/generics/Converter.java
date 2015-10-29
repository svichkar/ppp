package com.nixsolutions.generics;

public interface Converter<T, I> {
    public T get(I input);
}
