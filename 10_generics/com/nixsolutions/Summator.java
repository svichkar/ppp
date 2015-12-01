package com.nixsolutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Summator {

	public static void main(String[] args) {
		List<Integer> integerList = new ArrayList<>(Arrays.asList(1, 2, 3));
		List<Double> doubleList = new ArrayList<>(Arrays.asList(4.1d, 5d, 6.5d));
		List<Float> floatList = new ArrayList<>(Arrays.asList(7.5f, 8f, 9.1f));
		Map<String, List<? extends Number>> test = new HashMap<>();
		test.put("integer", integerList);
		test.put("double", doubleList);
		test.put("float", floatList);
		System.out.println(summator(test));

	}

	public static Map<String, Double> summator(Map<String, List<? extends Number>> input) {
		Map<String, Double> output = new HashMap<>();
		for (Map.Entry<String, List<? extends Number>> e : input.entrySet()) {
			Double sum = 0d;
			for (Number n : e.getValue())
				sum += n.doubleValue();
			output.put(e.getKey(), sum);
		}
		return output;

	}

}
