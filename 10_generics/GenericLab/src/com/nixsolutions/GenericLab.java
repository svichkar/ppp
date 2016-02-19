package com.nixsolutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class GenericLab {

	public static Map<String, Double> sumMapValues(Map<String, List<? extends Number>> mapObj) {
		Map<String, Double> result = new HashMap<String, Double>();
		for (Entry<String, List<? extends Number>> entry : mapObj.entrySet()) {
			double sum = 0.0;
			for (Number num : entry.getValue()) {
				sum += num.doubleValue();
			}
			result.put(entry.getKey(), sum);
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println("First task demostration.");
		Map<String, List<? extends Number>> inputMap = new HashMap<>();
		List<Integer> intList = new ArrayList<>();
		intList.add(1);
		intList.add(5);
		intList.add(4);
		inputMap.put("Integer", intList);
		List<Float> floatList = new ArrayList<>();
		floatList.add(0.4f);
		floatList.add(0.2f);
		floatList.add(0.4f);
		inputMap.put("Float", floatList);
		Map<String, Double> outputMap = sumMapValues(inputMap);
		System.out.println("Before convertion method:");
		for (Entry<String, List<? extends Number>> entry : inputMap.entrySet()) {
			System.out.println("Key: " + entry.getKey() + " / Value: " + entry.getValue());
		}
		System.out.println("After convertion method:");
		for (Entry<String, Double> entry : outputMap.entrySet()) {
			System.out.println("Key: " + entry.getKey() + " / Value: " + entry.getValue());
		}

		System.out.println("\nSecond task demostration.");
		FloatToDouble fdObj = new FloatToDouble();
		IntArrToString asObj = new IntArrToString();
		float value = 0.05f;
		List<Integer> arr = new ArrayList<>();
		arr.add(6);
		arr.add(5);
		arr.add(7);
		System.out.println("Float to double convertion: " + value + " / " + fdObj.get(value));
		System.out.println("Array to string convertion: " + arr.toString() + " / " + asObj.get(arr));
	}

}
