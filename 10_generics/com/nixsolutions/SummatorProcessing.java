package com.nixsolutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SummatorProcessing {

	public static void main(String[] args) {
		Map<String, List<Number>> inpMap = new HashMap<>();
		inpMap.put("one", new ArrayList() {
			{
				add(3f);
				add(4f);
				add(5f);
			}
		});
		inpMap.put("two", new ArrayList() {
			{
				add(2d);
				add(6d);
				add(8d);
			}
		});
		inpMap.put("three", new ArrayList() {
			{
				add(3.4f);
				add(6.1f);
				add(7.7f);
			}
		});
		Map<String, Double> res = sumByKey(inpMap);
	}
	
	private static Map<String, Double> sumByKey(Map<String, List<Number>> m) {
		Map<String, Double> resultMap = new HashMap<>();
		for (Map.Entry<String, List<Number>> e : m.entrySet()) {
			resultMap.put(e.getKey(), listSum(e.getValue()));
		}
		return resultMap;
	}
	
	private static double listSum(List<? extends Number> l) {
		double sum = 0d;
		for (Number n : l) {
			sum += n.doubleValue();
		}
		return sum;
	}
}
