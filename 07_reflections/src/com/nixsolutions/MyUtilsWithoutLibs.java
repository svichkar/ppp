package com.nixsolutions;

import java.lang.reflect.Field;

/**
 * Created by konstantin on 11/30/2015.
 */
public final class MyUtilsWithoutLibs {

    public static Object getValue(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {

        Object value;

        Class meta = object.getClass();
        Field field = meta.getDeclaredField(fieldName);

        if (field.isAnnotationPresent(Public.class)) {
            field.setAccessible(true);
            value = field.get(object);
        } else throw new IllegalAccessException("Field is not annotated as @Public.");

        return value;
    }
}
