package com.nixsolutions.task1;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by konstantin on 11/30/2015.
 */
public class Reflection {

    public static void main(String args[]) {

        TestClass object = new TestClass();
        Field[] fields = object.getClass().getDeclaredFields();
        Object result;

        for (Field field : fields) {
            try {
                result = MyUtils.getValueByReflectionApi(object, field.getName());
                System.out.println(String.format("Field: %s; Type: %s; Value: %s", field.getName(), field.getType(), result));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

            try {
                result = MyUtils.getValueByGoogleReflection(object, field.getName());
                System.out.println(String.format("Field: %s; Type: %s; Value: %s", field.getName(), field.getType(), result));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

            try {
                result = MyUtils.getValueByBeanUtils(object, field.getName());
                System.out.println(String.format("Field: %s; Type: %s; Value: %s", field.getName(), field.getType(), result));
            } catch (NoSuchFieldException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }
}
