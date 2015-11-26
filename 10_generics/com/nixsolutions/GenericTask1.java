package com.nixsolutions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Created by Serko on 25.11.2015.
 */
public class GenericTask1 {
    private static final Logger LOGGER = LogManager.getRootLogger();

    public Map<String, Double> mapSummator(Map<String, ArrayList<? extends Number>> map) {
        LOGGER.info("Creating new map");
        Map outMap = new HashMap<String, Double>();
        Set<Map.Entry<String, ArrayList<? extends Number>>> set = map.entrySet();
        LOGGER.info("Calculating sum for ArrayList elements, and filling out map");
        for (Map.Entry<String, ArrayList<? extends Number>> entry : set) {
            Double sum = 0.0;
            try {
                for (int i = 0; i < entry.getValue().size(); i++) {
                    sum += entry.getValue().get(i).doubleValue();
                }
                outMap.put(entry.getKey(), sum);
            } catch (NullPointerException e) {
                LOGGER.error("input map have null elements", e);
            }
        }
        return outMap;
    }

    public static void main(String[] args) {
        Random random = new Random();
        Map inputMap = new HashMap<String, ArrayList<Integer>>();
        LOGGER.entry();
        LOGGER.info("filling input map");
        for (int i = 0; i < 10; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 0; j < 10 + i; j++) {
                list.add(random.nextInt(50));
            }
            inputMap.put("TestList #" + i, list);
        }
        Set<Map.Entry<String, ArrayList<Integer>>> set = inputMap.entrySet();
        for (Map.Entry<String, ArrayList<Integer>> entry : set) {
            LOGGER.info(entry.getKey() + "-" + entry.getValue().toString());
        }
        LOGGER.info("input map filed");

        GenericTask1 test = new GenericTask1();

        Map outMap;

        outMap = test.mapSummator(inputMap);
        LOGGER.info("out map filled");
        Set<Map.Entry<String, Double>> outSet = outMap.entrySet();
        for (Map.Entry<String, Double> entry : outSet) {
            LOGGER.info(entry.getKey() + "-" + entry.getValue());
        }
        LOGGER.exit();
    }
}
