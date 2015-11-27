/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericsworkshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author mednorcom
 */
public class GenericsWorkshop {

    public static Map<String, Double> summator(Map<String, List<? extends Number>> inputMap) {
        Map<String, Double> outputMap = new HashMap<>();
        for (Map.Entry<String, List<? extends Number>> entry : inputMap.entrySet()) {
            outputMap.put(entry.getKey(), 0D);
            for (Number number : entry.getValue()) {
                outputMap.put(entry.getKey(), outputMap.get(entry.getKey()) + number.doubleValue());
            }
        }
        return outputMap;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int mapLength = 5;
        int listLength = 10;
        Map<String, List<? extends Number>> testMap = new HashMap<>();
        for (int i = 0; i < mapLength; i++) {
            List<Number> testList = new ArrayList<>();
            for (int j = 0; j < listLength; j++) {
                testList.add(new Random().nextDouble());
            }
            testMap.put("testkey" + i, testList);
        }
        System.out.println("Map content: " + testMap.toString());
        System.out.println("Summator method: " + GenericsWorkshop.summator(testMap).toString());
        System.out.println("Float to double: " + new FloatToDouble().get(12456.2123F));
        System.out.println("Array of Integers to string: " 
                + new IntArrayToString().get(new Integer[]{1, 2, 3, 4, 5, 6, 7}));

    }

}
