package generic.com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args) {

		Map<String, List<? extends Number>> mainMap = new HashMap<>();
		List<Integer> intList = new ArrayList<Integer>();
		intList.add(5);
		intList.add(6);
		List<Double> doubleList = new ArrayList<Double>();
		doubleList.add(5.8);
		doubleList.add(6.7);
		mainMap.put("key1", intList);
		mainMap.put("key2", intList);
		mainMap.put("key3", doubleList);
		Map<String, Double> newMap = sum(mainMap);
		System.out.println(newMap); // {key1=11.0, key2=11.0, key3=12.5}

		Integer arrIneger[] = { 1, 200, 400, 100 };
		System.out.println("Check convert float to double:" + new ConvertFloat().get(12.35F));
		System.out.println("Check convert array to string:" + new ConvertArr().get(arrIneger));
	}

	public static Map<String, Double> sum(Map<String, List<? extends Number>> inMap) {
		Map<String, Double> resMap = new HashMap<>();
		for (Entry<String, List<? extends Number>> inner : inMap.entrySet()) {
			double sum = 0;
			for (Number i : inner.getValue()) {
				sum += i.doubleValue();
			}
			resMap.put(inner.getKey(), sum);
		}
		return resMap;
	}
}
