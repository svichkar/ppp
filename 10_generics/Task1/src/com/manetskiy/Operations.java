package com.manetskiy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Operations {

    public static Map<String, Double> sum(Map<String, List<? extends Number>> map) {
        Map<String, Double> toReturn = new HashMap<>();

        map.entrySet().forEach(entry -> {
            Double sum = 0.00;
            for (Number n : entry.getValue()) {
                sum += n.doubleValue();
            }
            toReturn.put(entry.getKey(), sum);
        });

        return toReturn;
    }
}
