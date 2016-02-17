package com.nixsolutions.reflect;

import java.lang.reflect.Field;

import org.reflections.ReflectionUtils;


public class AnnotationUtils {

    public static void main(String[] argc) throws IllegalAccessException {
        StudentEntity se = new StudentEntity("Dima", 23, "KU-51", 75.5);
        System.out.println("With power of java.lang: " + getToStringWithStandardLibrary(se));
        System.out.println("With power of org.reflections: " + getToStringWithApache(se));
    }

    public static String getToStringWithStandardLibrary(Object obj) throws IllegalAccessException {
        Class<?> c = obj.getClass();
        Field[] fields = c.getDeclaredFields();
        String ret = c.getName() + "[";
        for (Field field : fields) {
            if (field.isAnnotationPresent(ToString.class)) {
                field.setAccessible(true);
                ret += field.getName() + " = " + field.get(obj) + "; ";
            }
        }
        return ret + "]";
    }

    public static String getToStringWithApache(Object obj) throws IllegalAccessException {
        String ret = obj.getClass().getName() + "[";
        for (Field field : ReflectionUtils.getAllFields(obj.getClass(),
                ReflectionUtils.withAnnotation(ToString.class))) {
            field.setAccessible(true);
            ret += field.getName() + " = " + field.get(obj) + "; ";
        }
        return ret + "]";
    }

}
