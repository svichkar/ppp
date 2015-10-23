package com.nixsolutions;

/**
 * Interface for converting diff types
 * 
 * @author maxb
 * 
 * @param <I>
 *            source type
 * @param <T>
 *            target type
 */
public interface Convertor<I, T> {

	public T get(I i);
}
