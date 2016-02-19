package com.nixsolutions;

import java.lang.reflect.*;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

/**
 * @author Sirotkin Mikhail
 * Utils class that contains methods for get  field value that implemeted by 2 different ways: with native reflect
 * libraries and using Apache Common BeanUtils
 */
public class Utils {

    /**
     * Static method that return field value of object
     * Implementing without using java.lang.reflect
     * @param obj
     * @param fieldName
     * @return object that contains in requested field
     * @throws IllegalAccessException if called field doesn't have @Public annotation
     * @throws NoSuchFieldException if object doesn't have field like expected
     */
    public static Object getFieldValue(Object obj, String fieldName) throws IllegalAccessException,
            NoSuchFieldException {
        Class cl = obj.getClass();
        try {
            Field field = cl.getDeclaredField(fieldName);
            if (field.isAnnotationPresent(Public.class)) {
                field.setAccessible(true);
                return field.get(obj);
            } else {
                throw new IllegalAccessException("Current field doesn't have annotation '@Public'!");
            }
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            throw ex;
        }
    }

    /**
     * Static method that return field value of object
     * Implemented using Apache Commons BeanUtils libraries
     * @param obj
     * @param fieldName
     * @return object that contains in requested field
     * @throws IllegalAccessException if called field doesn't have @Public annotation
     * @throws NoSuchFieldException if object doesn't have field like expected
     */
    public static Object getFieldValuewWithBeanUtils(Object obj, String fieldName) throws IllegalAccessException,
            InvocationTargetException, NoSuchMethodException, NoSuchFieldException {
        Class cl = obj.getClass();
        Field field = FieldUtils.getDeclaredField(cl, fieldName, true);
        if(field == null)
            throw new NoSuchFieldException("Current class doesn't contains field " + fieldName);
        try {
            if(field.isAnnotationPresent(Public.class))
                return PropertyUtils.getSimpleProperty(obj, fieldName);
            else throw new IllegalAccessException("Field " + fieldName + " doesn't have annotation @Public!");
        } catch (IllegalAccessException ex) {
            throw ex;
        }
    }

}
