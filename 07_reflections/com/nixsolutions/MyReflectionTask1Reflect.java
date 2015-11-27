package com.nixsolutions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;

/**
 * Created by Serko on 27.11.2015.
 */
public class MyReflectionTask1Reflect {
    private static final Logger LOGGER = LogManager.getRootLogger();

    public static void main(String[] args) {
        LOGGER.entry();
        ForTestObjects test = new ForTestObjects("name", 24, true, 12.0);
        Class c = test.getClass();
        Field[] array = c.getDeclaredFields();
        for (int i = 0; i < array.length; i++) {
            try {
                LOGGER.trace(getField(test, array[i].getName()));
            } catch (IllegalAccessException | NoSuchFieldException e) {
                LOGGER.error(e);
            }
        }
        LOGGER.exit();
    }

    public static Object getField(Object object, String fieldName) throws IllegalAccessException, NoSuchFieldException {
        Class c = object.getClass();
        try {
            Field field = c.getDeclaredField(fieldName);
            if (field.isAnnotationPresent(Public.class)) {
                field.setAccessible(true);
                return field.get(object);
            } else {
                throw new IllegalAccessException("Haven't @Public annotation");
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw e;
        }
    }
}
