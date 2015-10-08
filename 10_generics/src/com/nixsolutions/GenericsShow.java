package com.nixsolutions;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

public class GenericsShow {

    public static void main(String[] args) {

	Map<String, List<Number>> map = new HashMap<>();
	List<Number> num = new ArrayList<Number>();
	num.add(123);
	num.add(98);
	num.add(213);
	num.add(2);
	num.add(333);
	num.add(43);
	num.add(24);
	num.add(534);
	num.add(34);
	num.add(654);
	map.put("one", num);
	map.put("two", num);
	map.put("three", num);
	Summator summ = new Summator();
	Map<String, Double> summatorOutput = summ.summator(map);

	// Float to Double presemtation

	FloatToDoubleConverter ftd = new FloatToDoubleConverter();
	Float f = ftd.get(9879687.357235);

	// Integer Array to String presentation

	IntegerAttayToStringConverter iatsc = new IntegerAttayToStringConverter();
	String str = iatsc.get(new Integer[] { 1231, 23, 32, 32, 4415, 56234, 6234, 34 });
	System.out.println(str);

    }

}
