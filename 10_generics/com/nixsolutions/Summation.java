package com.nixsolutions;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

import javax.swing.text.html.parser.Entity;
import javax.xml.crypto.dsig.keyinfo.KeyValue;

public class Summation {

	/**
	 * @param args
	 */
	static final int LISTSIZE = 5;

	public static void main(String[] args) {
		// /generate input data
		Random rnd = new Random();

		List<Integer> intArray = new ArrayList<Integer>();
		List<Long> longArray = new ArrayList<Long>();
		List<Double> doubleArray = new ArrayList<Double>();
		List<Float> floatArray = new ArrayList<Float>();
		List<Number> numberArray = new ArrayList<Number>();
		Number[] arrayNumber = new Number[] { 1, 1.2, 3.4E-4, 10000, -10 };

		// fill arrays by random numbers
		for (int i = 0; i < LISTSIZE; i++) {
			intArray.add(rnd.nextInt());
			longArray.add(rnd.nextLong());
			doubleArray.add(rnd.nextDouble());
			floatArray.add(rnd.nextFloat());
			numberArray.add(arrayNumber[rnd.nextInt(arrayNumber.length - 1)]);
		}

		List[] values = new List[] { intArray, longArray, floatArray,
				doubleArray, numberArray };
		// /prepare input data
		Map<String, List<?>> inputData = new TreeMap<String, List<?>>();
		for (int zz = 0; zz < values.length; zz++) {
			inputData.put("key" + zz, values[zz]);
		}
		// //show result of using function
		for (Entry<String, List<?>> pair : inputData.entrySet()) {
			System.out.printf("Type %1s%n Key '%2s\' Values '%3s'%n", pair.getValue().get(0).getClass().getName(), pair.getKey(), 
					joinStrings(" ", pair.getValue()));
		}
		System.out.println("After processing...");
		Map<String, Double> result = mySummator(inputData);
		// //show result
		for (Entry<String, Double> pair : result.entrySet()) {
			System.out.printf("Key %1s Values %2.2f%n", pair.getKey(),
					pair.getValue());
		}

	}

	public static Map<String, Double> mySummator(Map<String, List<?>> sourceMap) {
		Map<String, Double> result = new TreeMap<String, Double>();
		if (!sourceMap.isEmpty()) {
			for (Entry<String, List<?>> pair : sourceMap.entrySet()) {
				result.put(pair.getKey(), getSum(pair.getValue()));
			}
		}
		
		return result;
	}

	private static Double getSum(List<?> numbers) {
		Double result = 0.0;
		Iterator<?> iter = numbers.iterator();
		while (iter.hasNext()) {
			Number numberValue = (Number) iter.next();
			result = result + numberValue.doubleValue();
		}
		return result;
	}

	private static String joinStrings(String delimiter, List<?> values) {
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
