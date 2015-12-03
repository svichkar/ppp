package com.nixsolutions.task1;

import java.util.*;
import java.util.Map.Entry;

/**
 * Created by svichkar on 12/3/2015.
 */
public class MegaSummator {

    public static void main(String args[]) {

        Map<String, List<Float>> mapFloat = new HashMap<>();

        //fill mapFloat with random data
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            List<Float> list = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                list.add(random.nextFloat());
            }
            mapFloat.put(String.valueOf(random.nextInt(100)), list);
        }

        Map<String, Double> mapIntegerOutput = extendedSum(mapFloat);

        System.out.println("Here is the result:");
        for (Entry<String, Double> entry : mapIntegerOutput.entrySet()) {
            System.out.println(String.format("key: %s; value: %s", entry.getKey(), entry.getValue()));
        }
    }

    /**
     * method which executes extended sum operation
     *
     * @param inputMap
     * @param <T>
     * @return Map<String, Double> object
     */
    public static <T extends Number> Map<String, Double> extendedSum(Map<String, List<T>> inputMap) {

        Map<String, Double> outPut = new HashMap<>();

        if (inputMap.isEmpty() == false) {

            for (Entry<String, List<T>> entry : inputMap.entrySet()) {
                outPut.put(entry.getKey(), sumList(entry.getValue()));
            }
        }
        return outPut;
    }

    /**
     * method which perform sum of List elements
     *
     * @param inputList
     * @param <T>
     * @return Double - sum of List elements
     */
    protected static <T extends Number> Double sumList(List<T> inputList) {

        Double sum = new Double(0);

        if (inputList.isEmpty() == false) {

            for (T element : inputList) {
                sum += element.doubleValue();
            }
        }
        return sum;
    }
}
