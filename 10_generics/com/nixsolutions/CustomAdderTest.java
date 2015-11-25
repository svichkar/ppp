package com.nixsolutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rybkinrolla on 25.11.2015.
 */
public class CustomAdderTest {
    public static void main(String[] args) {
        Map<String, List<? extends Number>> initialMap = new HashMap<>();
        initialMap.put("John",new ArrayList<Integer>(){{add(1);add(1);add(1);}});
        initialMap.put("Sam",new ArrayList<Integer>(){{add(2);add(2);add(2);}});
        initialMap.put("Tony",new ArrayList<Integer>(){{add(3);add(3);add(3);}});
        Map<String, Double> resultMap = CustomAdder.sumByKey(initialMap);
        for (Map.Entry entry: resultMap.entrySet()) {
            System.out.println(entry.getKey() + "-" +entry.getValue());
        }

    }
}
