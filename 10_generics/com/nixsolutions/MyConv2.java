package com.nixsolutions;

import java.util.Arrays;
import java.util.List;

/**Class for implementing interface Convertor
 * 
 * @author maxb
 *
 */
public class MyConv2 implements Convertor<Integer[], String> {

	public String get(Integer[] i) {
		return joinStrings(" ", Arrays.asList(i));
	}

	private String joinStrings(String delimiter, List<?> values) {
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
