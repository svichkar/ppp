package com.nixsolutions;

/**
 * the interface has only one methid get(). Its purpose is to organize a
 * conversion of one type value (T) to an another type value (I)
 * 
 * @author kryzhanovskiy
 *
 * @param <I> a value of type T which will be converted
 * @param <T> a values of type T - result of the conversion
 */
public interface Converter<I, T> {

	/**
	 * 
	 * @param toBeconverted
	 *            recievse an (I) type value which needs to be converted in to a
	 *            T type value
	 * @return T value as a results of conversion
	 */
	public T get(I toBeconverted);

}
