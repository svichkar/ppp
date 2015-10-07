package com.nixsolutions;

public class FloatConverter<T, E> implements Converter<T, E> {

	@Override
	public <T> T get(E element) {
		
		return (T) Double.valueOf(element.toString());
	}

}
