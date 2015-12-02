package com.nixsolutions;

/*
Реализовать интерфейс Converter с методом get, на вход принимающим параметризованный тип I
 и возвращающий параметризированный тип T. Реализовать 2 класса наследника - один 
 преобразовывает Float в Double, другой - массив Integer в String (конкатенация всех элементов через пробел).
 Показать использование на примере
*/

/**
 * The Interface Conventer.
 *
 * @param <I> the generic type
 * @param <T> the generic type
 */
public interface Conventer<I, T> {
	
	/**
	 * Gets the.
	 *
	 * @param data the input data
	 * @return the t
	 */
	public T get(I data);
	
}


