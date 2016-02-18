package com.manetskiy;

import java.util.*;


//Class for testing Operations.sum(Map<String, List<? extends Number>> map) method
public class Main {

    private static Random rand = new Random();
    private static final int MAP_SIZE = 5;

    public static void main(String[] args) {
        Map<String, List<? extends Number>> map = new HashMap<>(MAP_SIZE);

        //Create map with String as key and List<Float> or List<Integer> as value
        for (int i = 0; i < MAP_SIZE; i++) {
            int randomList = rand.nextInt(2);
            List<? extends Number> numbers = new ArrayList<>();
            List<String> keys = createKeys();
            switch (randomList) {
                case 0:
                    numbers = createRandomFloat();
                    break;
                case 1:
                    numbers = createRandomInteger();
                    break;
            }
            map.put(keys.get(i), numbers);
        }
        System.out.println("Before addition:");
        map.entrySet().forEach(set -> System.out.println(set.getKey() + " : " + set.getValue()));

        System.out.println("\nAfter addition:");
        printMap(Operations.sum(map));
    }

    public static void printMap(Map<String, Double> map) {
        for (Map.Entry entry : map.entrySet()) {
            System.out.printf(entry.getKey() + " : %.4f\n", entry.getValue());
        }
    }

    private static List<Float> createRandomFloat() {
        List<Float> toReturn = new ArrayList<>();
        for (int i = 0; i < MAP_SIZE; i++) {
            toReturn.add(rand.nextFloat() * ((10 - 1) + 1));
        }
        return toReturn;
    }

    private static List<Integer> createRandomInteger() {
        List<Integer> toReturn = new ArrayList<>();
        for (int i = 0; i < MAP_SIZE; i++) {
            toReturn.add(rand.nextInt(10));
        }
        return toReturn;
    }

    private static List<String> createKeys() {
        List<String> toReturn = new ArrayList<>();
        for (int i = 0; i < MAP_SIZE; i++) {
            toReturn.add("key " + (i + 1));
        }
        return toReturn;
    }

}
