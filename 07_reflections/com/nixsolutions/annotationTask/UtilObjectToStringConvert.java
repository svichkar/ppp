package com.nixsolutions.annotationTask;

import java.lang.reflect.Field;
import org.reflections.ReflectionUtils;

/**
 * Created by rybkinrolla on 27.11.2015.
 */
public class UtilObjectToStringConvert {

    public static String ReflectByAnnotation(Object objToConvert) throws NoSuchFieldException, IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(objToConvert.getClass().getName()).append("]");
        for (Field f : objToConvert.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            Object value = f.get(objToConvert);
            if (f.isAnnotationPresent(ToString.class)) {
                sb.append("(").append(f.getName()).append("=").append(value.toString()).append(")");
                sb.append(":");
            }
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public static String GoogleReflectByAnnotation(Object objToConvert) throws IllegalAccessException{
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(objToConvert.getClass().getName()).append("]");
        for (Field f: ReflectionUtils.getFields(objToConvert.getClass(),ReflectionUtils.withAnnotation(ToString.class))) {
            f.setAccessible(true);
            Object value = f.get(objToConvert);
            sb.append("(").append(f.getName()).append("=").append(value.toString()).append(")");
            sb.append(":");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
