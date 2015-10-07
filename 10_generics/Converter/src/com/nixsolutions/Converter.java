/**
 * 
 */
package com.nixsolutions;

/**
 * @author mixeyes
 * @param <E>
 *
 */
public interface Converter<T, E> {

	public <T> T  get(E element);
}
