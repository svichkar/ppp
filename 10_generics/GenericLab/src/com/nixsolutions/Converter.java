package com.nixsolutions;

public interface Converter<I, T> {
	public T get(I value);
}
