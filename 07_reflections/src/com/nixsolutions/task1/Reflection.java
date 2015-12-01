package com.nixsolutions.task1;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by konstantin on 11/30/2015.
 */
public class Reflection {

    public static void main(String args[]) {

        TestClass object = new TestClass();
        String[] fieldNames = {"course", "word", "number", "notExistingField"};

        for (String fieldName : fieldNames) {

            try {
                MyUtils.getValueByReflectionApi(object, fieldName);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

            try {
                MyUtils.getValueByGoogleReflection(object, fieldName);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

            try {
                MyUtils.getValueByBeanUtils(object, fieldName);
            } catch (NoSuchFieldException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }
}
