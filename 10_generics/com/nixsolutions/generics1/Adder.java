package com.nixsolutions.generics1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Implementation of task 1 of 10_generics lab. */
public class Adder {
    /**
     * Calculates the sum of all elements for each value of the map, containing
     * the list of Number or its subclasses as a value.
     * 
     * @param map
     *            Input map, the key is String, and the value is a list of
     *            Number or its subclasses.
     * @return Returns the map having the same String key as the input map, and
     *         Double value of sum of all the elements from the list value of
     *         the input map.
     */
    public static <E extends Number> Map<String, Double> sumOfMapValueElements(
            Map<String, List<E>> map) {
        Map<String, Double> outMap = new HashMap<>();
        for (Map.Entry<String, List<E>> entry : map.entrySet()) {
            Double sum = new Double(0);
            for (E value : entry.getValue()) {
                sum += value.doubleValue();
            }
            outMap.put(entry.getKey(), sum);
        }
        return outMap;
    }

    private static <K, V> void printMap(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    /** Example of usage of the sumOfMapValueElements method. */
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(0, 9, 8, 7, 6);

        Map<String, List<Integer>> map = new HashMap<>();
        map.put("1st entry", list1);
        map.put("2nd entry", list2);

        System.out.println("Input map:");
        printMap(map);

        System.out.println();
        System.out.println("Output map:");
        printMap(Adder.sumOfMapValueElements(map));
    }
}
