package com.manetskiy.apachelib;


import com.manetskiy.Public;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;

public class FieldsUtil {

    public static Object getField(Object ob, String fieldName) throws IllegalAccessException, ClassNotFoundException {
        Class clazz = ob.getClass();
        Field field = FieldUtils.getDeclaredField(clazz, fieldName, true);
        if (field.isAnnotationPresent(Public.class))
            return FieldUtils.readDeclaredField(ob, fieldName, true);
        else
            throw new IllegalAccessException();
    }
}
