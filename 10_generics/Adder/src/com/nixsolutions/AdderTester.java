/**
 * 
 */
package com.nixsolutions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author mixeyes
 *
 */
public class AdderTester {

	/**
	 * @param args
	 */
	@SuppressWarnings("null")
	public static void main(String[] args) {
		HashMap<String, Collection<? extends Number>> intCollection = new HashMap<>();
		List<Integer> intList = new ArrayList<Integer>();
		for (int i = 1; i < 10; i++)
			intList.add(i);

		intCollection.put("intElements", intList);
		Map<String, Double> newCollection = Adder.adder(intCollection);
		List<Float> fList = new ArrayList<Float>();
		for (float i = 1.1f; i < 10.50f; i += 1.2f)
			fList.add(i);
		List<Long> lList = new ArrayList<Long>();
		for (Long i = 1l; i < 10l; i++)
			lList.add(i);
		intCollection.put("fElements", fList);
		intCollection.put("lElements", lList);
		newCollection = Adder.adder(intCollection);
		printMap(newCollection);
	}

	private static void printMap(Map<String, Double> newCollection) {
		Set<String> key = newCollection.keySet();
		for (String obj : key) {
			Double finalVal = 0D;
			System.out.println("Key " + obj + "; Value " + newCollection.get(obj));

		}

	}

}
