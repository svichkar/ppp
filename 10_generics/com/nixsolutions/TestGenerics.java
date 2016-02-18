package com.nixsolutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestGenerics {

    public static void main(String[] argc) {

        Map<String, List<? extends Number>> testSum = new HashMap<>();
        List<Double> l1 = new ArrayList<Double>() {{
            add(1.1);
            add(1.1);
            add(1.1);
            add(1.1);
            add(1.1);
        }};
        List<Integer> l2 = new ArrayList<Integer>() {{
            add(2);
            add(2);
            add(2);
            add(2);
            add(2);
        }};
        List<Long> l3 = new ArrayList<Long>() {{
            add(100000L);
            add(100000L);
            add(100000L);
            add(100000L);
            add(100000L);
        }};
        testSum.put("doubleValue", l1);
        testSum.put("integerValue", l2);
        testSum.put("longValue", l3);

        System.out.println("It's work of adder...");
        System.out.println(GenericUtils.sumMap(testSum) + "\n");

        System.out.println("It's work of ConvertFloatToDouble...");
        ConverterFloatToDouble dToF = new ConverterFloatToDouble();
        System.out.println("Value: " + dToF.get(223.5f));
        System.out.println("Type:" + dToF.get(223.5f).getClass() + "\n");

        System.out.println("It's work of ConverterArrayIntegerToString...");
        ConverterArrayIntegerToString arrIntToStr = new ConverterArrayIntegerToString();
        System.out.println("Value: " + arrIntToStr.get(new Integer[]{1, 2, 3}));

    }

}
