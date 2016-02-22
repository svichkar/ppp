package com.addingnumbers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by val on 2/21/2016.
 */
public class Adder {
    public static void main(String[] args) {
        Adder adder = new Adder();
        Map<String, List<? extends Number>> inputMap = new HashMap<>();

        List<Number> list = new ArrayList<Number>();
        list.add(new Integer(10));
        list.add(new Double(20.0));
        list.add(new Long(10L));
        list.add(new Float(10.0f));

        inputMap.put("Numbers", list);
        System.out.println(adder.add(inputMap));
    }

    public Map<String, Double> add(Map<String, List<? extends Number>> inputMap) {
        Map<String, Double> outputMap = new HashMap<>();
        Object firstKey = inputMap.keySet().toArray()[0];
        Double finalResult = 0.00;
        for (int i = 0; i < inputMap.get(firstKey).size(); i++) {
            finalResult += new Double(inputMap.get(firstKey).get(i).doubleValue());
        }
        outputMap.put((String) firstKey, finalResult);
        return outputMap;
    }
}
