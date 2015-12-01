package com.nixsolutions.task1;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import static org.reflections.ReflectionUtils.*;


/**
 * Created by konstantin on 11/30/2015.
 */
public final class MyUtils {

    /**
     * method gets value of field using Java Reflection API
     * @param object
     * @param fieldName
     * @return value of requested field if it's annotated as Public
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static Object getValueByReflectionApi(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {

        Object value;

        Class meta = object.getClass();
        Field field = meta.getDeclaredField(fieldName);

        if (field.isAnnotationPresent(Public.class)) {
            field.setAccessible(true);
            value = field.get(object);
        } else throw new IllegalAccessException(String.format("Field [%s] is not annotated as @Public.", fieldName));

        return value;
    }

    /**
     * method gets value of field using Google Reflection API
     * @param object
     * @param fieldName
     * @return value of requested field if it's annotated as Public
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static Object getValueByGoogleReflection(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {

        Object value = null;
        Set<Field> fieldsByName = getAllFields(object.getClass(), withName(fieldName));

        if (fieldsByName.isEmpty() == false) {

            Set<Field> fieldsByAnnotationAndName = getAllFields(object.getClass(), withName(fieldName), withAnnotation(Public.class));

            if (fieldsByAnnotationAndName.isEmpty() == false) {

                for (Field f : fieldsByAnnotationAndName) {
                    f.setAccessible(true);
                    value = f.get(object);
                }

            } else throw new IllegalAccessException(String.format("Field [%s] is not annotated as @Public.", fieldName));

        } else throw new NoSuchFieldException(fieldName);

        return value;
    }

    /**
     * method gets value of field using BeanUtils
     * @param object
     * @param fieldName
     * @return value of requested field if it's annotated as Public
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static Object getValueByBeanUtils(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        Object value = null;
        Field field = object.getClass().getDeclaredField(fieldName);

        if (field.isAnnotationPresent(Public.class)) {
            value = BeanUtils.getProperty(object, fieldName);
        } else throw new IllegalAccessException(String.format("Field [%s] is not annotated as @Public.", fieldName));

        return value;
    }
}
