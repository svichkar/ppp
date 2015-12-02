package com.nixsolutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

/*
 Реализовать метод-сумматор, на входе получающий Map со строковым ключем и списком любых Number
 (включая наследников) как значение, на выходе возвращающим Map с тем же ключем и Double
 (сумма списка по этому ключу) как значение, показать использование на примере
 */

public class MethodSum {

	public static void main(String[] args) {
		Map<String, List<? extends Number>> map = new HashMap<>();
		Map<String, Double> newMapAfterSummator = new HashMap<>();
		Random random = new Random();
		List<Float> floatExtNumber = new ArrayList<>(10);
		List<Double> doubleExtNumber = new ArrayList<>(10);
		List<Integer> integerExtNumber = new ArrayList<>(10);
		
		for(int i = 0;i<10;i++)
		{
			floatExtNumber.add(random.nextFloat());
			doubleExtNumber.add(random.nextDouble());
			integerExtNumber.add(random.nextInt());
		}
		map.put("Integer", integerExtNumber);
		map.put("Float", floatExtNumber);
		map.put("Double", doubleExtNumber);

		newMapAfterSummator = methodMapSum(map);
		System.out.println(newMapAfterSummator);

	}

	public static Map<String, Double> methodMapSum(Map<String, List<? extends Number>> map) {
		Map<String, Double> returnMap = new HashMap<>();
		for (Entry<String, List<? extends Number>> entry : map.entrySet()) {
			String keyMap = (String) entry.getKey();
			List<? extends Number> list = (List<? extends Number>) entry.getValue();
			Double sumAllNumbersInList = new Double(0.0);
			for (Number number : list) {
				sumAllNumbersInList += number.doubleValue();
			}
			returnMap.put(keyMap, sumAllNumbersInList);

		}
		return returnMap;
	}

}