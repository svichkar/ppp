package com.nixsolutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by sobolenko on 2/3/2016.
 */
public class Adder {
    Map<String, Double> newMap = new HashMap<String, Double>();
    List<Number> innerNumber = new ArrayList<Number>();

    public Map<String, Double> addNumbers(Map<String, List<? extends Number>> inputMap) {
        for (Entry<String, List<? extends Number>> entry : inputMap.entrySet()) {
            List<? extends Number> numbers = inputMap.get(entry.getKey());
            Double summ = 0d;
            for (Number num : numbers) {
                summ = summator(summ,num);
            }
            newMap.put(entry.getKey(),summ);
        }
        return newMap;
    }
    public <T> Double summator(T number, T numberNext)
    {
        return Double.parseDouble(number.toString()) + Double.parseDouble(numberNext.toString());
    }
}
