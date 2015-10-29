package com.nixsolutions.generics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class GenericsTask1 {
    public static void main(String[] args) {
	Map<String, List<Number>> initMap = new HashMap<String, List<Number>>();
	Random rnd = new Random();
	for (int i = 1; i < 11; i++) {
	    List<Number> numList = new ArrayList<Number>();
	    int countOfNumbers = rnd.nextInt(10);
	    for (int j = 0; j < countOfNumbers; j++) {
		numList.add(rnd.nextInt(100));
	    }
	    initMap.put(String.valueOf(i), numList);
	}
	Map<String, Double> resMap = summator(initMap);
	System.out.println(initMap);
	System.out.println("\n");
	System.out.println(resMap);
    }

    public static Map<String, Double> summator(Map<String, List<Number>> inputMap) {
	Map<String, Double> outputMap = new HashMap<String, Double>();
	for (Entry<String, List<Number>> entry : inputMap.entrySet()) {
	    Double sum = 0.0;
	    for (Number n : entry.getValue()) {
		sum = sum + n.doubleValue();
	    }
	    outputMap.put(entry.getKey(), sum);
	}
	return outputMap;
    }
}
