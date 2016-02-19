package com.nixsolutions;

import java.util.List;

public class IntArrToString implements Converter<List<Integer>, String> { 

	@Override
	public String get(List<Integer> value) {
		String result = "";
		for (Integer item : value) {
			result += item + " ";
		}
		return result;
	}

}
