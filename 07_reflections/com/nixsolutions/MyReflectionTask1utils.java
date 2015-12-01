package com.nixsolutions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.Set;

import static org.reflections.ReflectionUtils.*;


/**
 * Created by Serko on 27.11.2015.
 */
public class MyReflectionTask1utils {
    private static final Logger LOGGER = LogManager.getRootLogger();

    public static void main(String[] args) {
        LOGGER.entry();
        ForTestObjects test = new ForTestObjects("name", 24, true, 12.0);
        Set<Field> fields = getAllFields(test.getClass());
        for (Field f : fields) {
            try {
                LOGGER.trace(getField(test, f.getName()));
            } catch (IllegalAccessException | NoSuchFieldException e) {
                LOGGER.error(e);
            }
        }
        LOGGER.exit();
    }

    public static Object getField(Object object, String fieldName) throws IllegalAccessException, NoSuchFieldException {
        Set<Field> fields = getFields(object.getClass(), withName(fieldName), withAnnotation(Public.class));
        if (!fields.isEmpty()) {
            Field field = fields.iterator().next();
            field.setAccessible(true);
            return field.get(object);
        } else {
            throw new IllegalAccessException("Haven't @Public annotation");
        }
    }
}
