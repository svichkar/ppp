package com.nixsolutions;

import java.lang.reflect.Field;
import java.util.Set;

import static org.reflections.ReflectionUtils.*;


/**
 * Created by konstantin on 11/30/2015.
 */
public class MyUtilsWithLibs {

    public static Object getValue(Object object, String fieldName) throws IllegalAccessException {

        Object value = null;

        Set<Field> fields = getAllFields(object.getClass(), withName(fieldName));

        for (Field f : fields)
        {
            f.setAccessible(true);
            if (f.isAnnotationPresent(Public.class)) {
                value = f.get(object);
            }
            else throw new IllegalAccessException("Field is not marked as @Public.");
        }

        return value;
    }
}
