package com.nixsolutions;

import java.util.*;

/**
 * @author Sirotkin Mikhail
 * Methods with static method that summaries numbers of array for each key in input Map and
 * main() method that provide examples of using sumMethodForNumbersMap
 * Also contains method for printing keys and values of Map
 */
public class SummatorMain {

    public static void main(String[] args) {
        //check with Integer
        Map<String,List<? extends Number>> checkArray1 = new HashMap<>();
        checkArray1.put("FirstInteger",new ArrayList<Integer>(){{
            add(1);
            add(2);
            add(3);}});
        checkArray1.put("SecondInteger", new ArrayList<Integer>() {{
            add(120);
            add(80);
            add(1);
        }});

        Map<String, Double> res1 = sumMethodForNumbersMap(checkArray1);
        printResultFromMap(res1); //expected 6 and 201

        //check with Double
        Map<String,List<? extends Number>> checkArray2 = new HashMap<>();
        checkArray2.put("Double", new ArrayList<Double>() {{
            add(2.5d);
            add(1.4d);
            add(3.6d);
        }});
        Map<String, Double> res2 = sumMethodForNumbersMap(checkArray2);
        printResultFromMap(res2); //expected 7.5

        //check with Float
        Map<String,List<? extends Number>> checkArray3 = new HashMap<>();
        checkArray3.put("First Float", new ArrayList<Float>(){{
            add(0.2f);
            add(0.3f);
        }});
        checkArray3.put("Second Float", new ArrayList<Float>(){{
            add(0.375f);
            add(0.225f);
        }});

        Map<String, Double> res3 = sumMethodForNumbersMap(checkArray3);
        printResultFromMap(res3); //expected ~0.5 and ~0.6
    }

    /**
     * Method that summaries numbers of array for each key in input Map
     * @param inputMap that contains
     * @return Map<String, Double> that contains the same key and sum of Numbers from input array for current key
     */
    public static Map<String, Double>  sumMethodForNumbersMap(Map<String, List<? extends Number>> inputMap){
        Map<String,Double> outputMap = new HashMap<>();
        for(Map.Entry<String, List<? extends Number>> itemOfHashMap : inputMap.entrySet()){
            List<? extends Number> curArr = itemOfHashMap.getValue();
            Double resultValueForCurrentKey = new Double(0);
            for(Number elemOfCurArr : curArr){
                resultValueForCurrentKey += elemOfCurArr.doubleValue();
            }
            outputMap.put(itemOfHashMap.getKey(), resultValueForCurrentKey);
        }
        return outputMap;
    }

    /**
     * Method that print keys and values of Map
     * @param mapToPrint Map<String, ? extends Number>
     */
    public static void printResultFromMap(Map<String, ? extends Number> mapToPrint){
        for (Map.Entry entry: mapToPrint.entrySet()) {
            System.out.println("Key: " + entry.getKey() + "; value = " + entry.getValue() + ";");
        }
    }
}
