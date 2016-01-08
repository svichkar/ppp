package com.nixsolutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class SummatorTest {

	public static void main(String[] args) {
		
		//generation of an initial Map which will be converted
		Map<String, List<? extends Number>> map = mapgenerateMap();

		// execution of the summator() method
		Map<String, Double> results = summator(map);

		// Demonstration of each of the key - value pairs in the converted array
		for (Map.Entry<String, Double> entry : results.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}

	/**
	 * 
	 * @param map
	 *            receives a Map with String key and a List of arrays which
	 *            extend Number
	 * @return Map with the same keys and a Double value (sum of an array under
	 *         this key)
	 */
	public static Map<String, Double> summator(
			Map<String, List<? extends Number>> map) {

		Map<String, Double> toBeReturned = new HashMap<>();

		for (Map.Entry<String, List<? extends Number>> entry : map.entrySet()) {
			Double summa = 0d;
			String curKey = entry.getKey();
			List<? extends Number> current = entry.getValue();

			for (Number number : current) {
				summa = summa + number.doubleValue();
			}
			toBeReturned.put(curKey, summa);
		}
		return toBeReturned;
	}

	/**
	 * the method creates a hash map of arrays which are extends Number
	 * 
	 * @return a has map which contains arrays which are extends Number
	 */
	public static Map<String, List<? extends Number>> mapgenerateMap() {

		Map<String, List<? extends Number>> map = new HashMap<>();

		List<Float> fl = new ArrayList<>();
		fl.add(254.34f);
		fl.add(11.343f);
		fl.add(2.52f);

		List<Integer> integ = new ArrayList<>();
		integ.add(62);
		integ.add(73);
		integ.add(256);

		List<Byte> by = new ArrayList<>();
		by.add((byte) 32);
		by.add((byte) 74);
		by.add((byte) 15);

		List<AtomicInteger> atIntef = new ArrayList<>();
		atIntef.add(new AtomicInteger(21));
		atIntef.add(new AtomicInteger(35));
		atIntef.add(new AtomicInteger(98));

		map.put("float", fl);
		map.put("integer", integ);
		map.put("byte", by);
		map.put("atomic", atIntef);

		return map;
	}

}
