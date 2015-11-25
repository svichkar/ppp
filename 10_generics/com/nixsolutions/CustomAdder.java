package com.nixsolutions;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rybkinrolla on 25.11.2015.
 */
public class CustomAdder {

    public static Map<String, Double> sumByKey(Map<String, List<? extends Number>> map) {
            Map<String,Double> resultMap = new HashMap<>();
            for (Map.Entry<String, List<? extends Number>> entry : map.entrySet()) {
                resultMap.put(entry.getKey(),sumIt(entry.getValue()));
            }
        return resultMap;
    }

    private static Double sumIt(List<? extends Number> list) {
        Double sum = 0d;
        for (Number num : list) {
            sum += num.doubleValue();
        }
        return sum;
    }
}