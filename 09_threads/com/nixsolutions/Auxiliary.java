package com.nixsolutions;

import java.util.List;

/**
 * Class with auxiliary methods
 * 
 * @author maxb
 * 
 */
public class Auxiliary {

	/**
	 * Check if a value is even
	 * 
	 * @param item
	 *            any integer number
	 * @return
	 */
	public static boolean isEven(Integer item) {
		return item % 2 == 0;
	}

	/**
	 * Method for joining items of List into string
	 * 
	 * @param delimiter
	 *            define delimiter between numbers in string
	 * @param values
	 * @return
	 */
	public static String joinStrings(String delimiter, List<?> values) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < values.size(); i++) {
			if (i != values.size() - 1) {
				builder.append(values.get(i));
				builder.append(delimiter);
			} else {
				builder.append(values.get(i));
			}
		}
		return builder.toString();
	}
}
