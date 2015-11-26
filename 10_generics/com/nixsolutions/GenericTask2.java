package com.nixsolutions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Serko on 26.11.2015.
 */
public class GenericTask2 {
    private static final Logger LOGGER = LogManager.getRootLogger();
    public static void main(String[] args) {
        LOGGER.entry();
        float input = 3.14f;
        FloatToDouble test = new FloatToDouble();
        LOGGER.trace(test.get(input).getClass().getCanonicalName());

        Integer[] array = new Integer[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        IntegerToString test2 = new IntegerToString();
        LOGGER.exit(test2.get(array).getClass().getCanonicalName() + "/" + test2.get(array));
    }
}
