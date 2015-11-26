package com.nixsolutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Created by Serko on 25.11.2015.
 */
public class GenericTask1 {
    public Map<String, Double> mapSummator(Map<String, ArrayList<? extends Number>> map) {
        Map outMap = new HashMap<String, Double>();
        Set<Map.Entry<String, ArrayList<? extends Number>>> set = map.entrySet();
        for (Map.Entry<String, ArrayList<? extends Number>> entry : set) {
            Double sum = 0.0;
            for (int i = 0; i < entry.getValue().size(); i++) {
                sum += entry.getValue().get(i).doubleValue();
            }
            outMap.put(entry.getKey(), sum);
        }
        return outMap;
    }

    public static void main(String[] args) {
        Random random = new Random();
        Map inputMap = new HashMap<String, ArrayList<Integer>>();
        for (int i = 0; i < 10; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 0; j < 10 + i; j++) {
                list.add(random.nextInt(50));
            }
            inputMap.put("TestList #" + i, list);
        }
        Set<Map.Entry<String, ArrayList<Integer>>> set = inputMap.entrySet();
        for (Map.Entry<String, ArrayList<Integer>> entry : set) {
            System.out.println(entry.getKey() + "-" + entry.getValue().toString());
        }
        System.out.println();

        GenericTask1 test = new GenericTask1();

        Map outMap;

        outMap = test.mapSummator(inputMap);
        Set<Map.Entry<String, Double>> outSet = outMap.entrySet();
        for (Map.Entry<String, Double> entry : outSet) {
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }
    }
}
