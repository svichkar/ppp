package com.nixsolutions;

/**
 * the class has only 1 method get(Integer [] x). Its purpose is to convert an
 * array of integers to a string
 * 
 * @author kryzhanovskiy
 *
 */
public class ArrIntToString implements Converter<Integer[], String> {

	/**
	 * the method receives an array of integers, merge them in to a string and
	 * returns the string back
	 */
	@Override
	public String get(Integer[] toBeConverted) {
		StringBuilder toBeReturned = new StringBuilder();
		for (Integer integer : toBeConverted) {
			toBeReturned.append(integer.toString() + " ");
		}
		return toBeReturned.toString();
	}

}
