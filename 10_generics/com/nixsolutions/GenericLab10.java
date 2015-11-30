package com.nixsolutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GenericLab10 {

	public static void main(String[] args) {
		System.out.println("Task #1:");
		Map<String, List<? extends Number>> map = new HashMap<>();
		List<Integer> listIntegers = new ArrayList<>();
		List<Long> listLong = new ArrayList<>();
		List<Float> listFloat = new ArrayList<>();
		List<Double> listDouble = new ArrayList<>();

		Random rand = new Random();
		for (int j = 0; j < 5; j++) {
			listIntegers.add(rand.nextInt());
			listLong.add(rand.nextLong());
			listFloat.add(rand.nextFloat());
			listDouble.add(rand.nextDouble());
		}

		map.put("List of Integer", listIntegers);
		map.put("List of Long", listLong);
		map.put("List of Float", listFloat);
		map.put("List of Double", listDouble);
		for (Map.Entry entry : map.entrySet()) {
			String key = (String) entry.getKey();
			List<? extends Number> value = (List<? extends Number>) entry.getValue();
			System.out.println("Key: " + key + "; Value: " + value.toString());
		}
		
		System.out.println();
		Map<String, Double> result = summator(map);
		for (Map.Entry entry : result.entrySet()) {
			String key = (String) entry.getKey();
			Double value = (Double) entry.getValue();
			System.out.println("Key: " + key + "; Double res of sum of values: " + value.toString());
		}
		
		System.out.println("\nTask #2:");
		ConverterFromIntegerToString intToStr = new ConverterFromIntegerToString();
		System.out.println("Converter from Integer[] to String: " + intToStr.get(new Integer[] { 123, 45, 78, 95 }));
		ConverterFromFloatToDouble floatToDouble = new ConverterFromFloatToDouble();
		System.out.println("Converter from Float to Double: " + floatToDouble.get(1.231f));
	}

	public static Map<String, Double> summator(Map<String, List<? extends Number>> map) {
		Map<String, Double> resMap = new HashMap<String, Double>();

		for (Map.Entry entry : map.entrySet()) {
			String key = (String) entry.getKey();
			List<? extends Number> listValues = (List<? extends Number>) entry.getValue();
			Double resDouble = 0.0;
			if (listValues != null) {
				int size = listValues.size();
				for (int i = 0; i < size; i++) {
					Object val = listValues.get(i);
					if (val != null) {
						resDouble += listValues.get(i).doubleValue();
					}
				}
				if (key == null) {
					key = "Key was null";
				}
				resMap.put(key, resDouble);
			}
		}
		return resMap;
	}

}
