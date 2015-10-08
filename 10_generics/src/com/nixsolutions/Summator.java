package com.nixsolutions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Summator {

    // Task #1 - Summator

    public static Map<String, Double> summator(Map<String, List<Number>> input) {

	Map<String, Double> out = new HashMap<String, Double>();

	for (Map.Entry<String, List<Number>> entry : input.entrySet()) {

	    Double temp = 0D;

	    for (int i = 0; i < entry.getValue().size(); i++) {
		Number n = entry.getValue().get(i);
		temp += n.doubleValue();
	    }
	    out.put(entry.getKey(), temp);
	}

	return out;
    }
}
