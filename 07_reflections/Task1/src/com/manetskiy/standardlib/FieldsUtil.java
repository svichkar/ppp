package com.manetskiy.standardlib;


import com.manetskiy.Public;

import java.lang.reflect.Field;

public class FieldsUtil {

    public static Object getField(Object ob, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Class clazz = ob.getClass();
        Field field = clazz.getDeclaredField(fieldName);
        if (field.isAnnotationPresent(Public.class)) {
            field.setAccessible(true);
            return field.get(ob);
        } else
            throw new IllegalAccessException();
    }
}
