package com.nixsolutions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericUtils {

    public static Map<String, Double> sumMap(Map<String, List<? extends Number>> map) {
        Map<String, Double> ret = new HashMap<>();

        for (Map.Entry<String, List<? extends Number>> entry : map.entrySet()) {
            double sum = 0;
            for (int i = 0; i < entry.getValue().size(); i++) {
                sum += entry.getValue().get(i).doubleValue();
            }
            ret.put(entry.getKey(), sum);
        }

        return ret;
    }
}
