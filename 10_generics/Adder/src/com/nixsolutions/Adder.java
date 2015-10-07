/**
 * 
 */
package com.nixsolutions;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * @author mixeyes
 *
 */
public class Adder {

	public static Map<String, Double> adder(Map<String, Collection<? extends Number>> collection) {
		HashMap<String, Double> newValue = new HashMap<>();
		Set<String> key = collection.keySet();
		for (String obj : key) {
			Double finalVal = 0D;
			for (Object elems : collection.get(obj)) {
				finalVal += (Double.valueOf(elems.toString()));
				/*
				 * List<Number> values = (List<Number>) elems; for (Number val :
				 * values) { finalVal+=(Double)val; }
				 */
			}
			newValue.put(obj, finalVal);

		}

		return newValue;
	}

}
