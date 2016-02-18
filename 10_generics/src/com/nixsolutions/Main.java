package com.nixsolutions;

import java.util.*;

/**
 * Created by sobolenko on 2/17/2016.
 */
public class Main {
    static Random random = new Random();

    public static void main(String[] args) {
        Map<String, List<? extends Number>> newMap = new Hashtable<String, List<? extends Number>>();
        Map<String, Double> resultMap = new Hashtable<String, Double>();
        //String keyString = generateRandomString();
        //List<Number> number = generateRandomNumber();
        for (int i = 0; i < 10; i++) {
            newMap.put(generateRandomString(), generateRandomNumberList());
        }
        Adder adder = new Adder();
        resultMap = adder.addNumbers(newMap);
        for (Map.Entry<String, List<? extends Number>> entry : newMap.entrySet()) {
            System.out.println("Key: "+entry.getKey()+"\t Values:\t "+newMap.get(entry.getKey()));
        }
        System.out.println("Summa");
        for (Map.Entry<String, Double> entry : resultMap.entrySet()) {
            System.out.println("Key: "+entry.getKey()+"\t Values:\t "+resultMap.get(entry.getKey()));
        }
    }

    public static String generateRandomString() {
        Random random = new Random();
        char newChar;
        String result = "";
        for (int i = 0; i < (random.nextInt(5) + 3); i++) {
            newChar = (char) (random.nextInt(95) + 32);
            if (Character.isLetterOrDigit(newChar)) {
                result += newChar;
            }
        }
        return result;
    }

    public static List<? extends Number> generateRandomNumberList() {
        int sw = random.nextInt(3) + 1;
        switch (sw) {
            case 1:
                List<Integer> intNumber = new ArrayList<Integer>();
                for (int i = 0; i < (random.nextInt(7) + 3); i++) {
                    intNumber.add(random.nextInt(95));
                }
                return intNumber;
            case 2:
                List<Float> floatNumber = new ArrayList<Float>();
                for (int i = 0; i < (random.nextInt(7) + 3); i++) {
                    floatNumber.add(random.nextFloat() * 10);
                }
                return floatNumber;
            case 3:
                List<Long> longNumber = new ArrayList<Long>();
                for (int i = 0; i < (random.nextInt(7) + 3); i++) {
                    longNumber.add(random.nextLong());
                }
                return longNumber;
        }
        return null;
    }
}
