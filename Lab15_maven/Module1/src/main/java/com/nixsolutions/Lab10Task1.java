package com.nixsolutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class Lab10Task1 {

	public static void main(String[] args) {
		Map<String, List<? extends Number>> mapNumber = new HashMap<>();		
		List<Integer> listInteger = new ArrayList<>();
		List<Float> listFloat = new ArrayList<>();
		List<Long> listLong = new ArrayList<>();
		Random random = new Random();
	    for (int i = 0; i < 10; i++) {
	    	listInteger.add(random.nextInt(100));
	    	listFloat.add(random.nextFloat());
	    	listLong.add(random.nextLong());	      
	    }
	    mapNumber.put("Integers", listInteger);
	    mapNumber.put("Floats", listFloat);
	    mapNumber.put("Long", listLong);
	    Map<String, Double> results = mapSummator(mapNumber);
	    for(Entry<String, Double> result : results.entrySet()){
	    	String keyResult = (String) result.getKey();
	    	Double valueResult = (Double) result.getValue();
	    	System.out.println(keyResult + "\t" + valueResult);
	    }
	}
	public static Map<String, Double> mapSummator(Map<String, List<? extends Number>> map) {
		Map<String, Double> mapToReturn = new HashMap<>();
		for (Entry<String, List<? extends Number>> entry : map.entrySet()){
			String key = (String) entry.getKey();
			List<? extends Number> list = (List<? extends Number>) entry.getValue();
			Double sum = new Double(0.0);
			for (Number element : list){
				sum += element.doubleValue();
			}
			mapToReturn.put(key, sum);
		}
		return mapToReturn;		
	}

}
