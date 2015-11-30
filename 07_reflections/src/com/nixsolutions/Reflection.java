package com.nixsolutions;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by konstantin on 11/30/2015.
 */
public class Reflection {

    public static void main(String args[]) {

        TestClass object = new TestClass();
        String name = "course";

        try {
            MyUtilsWithoutLibs.getValue(object, name);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }


        try {
            MyUtilsWithLibs.getValue(object, name);
        } catch (IllegalAccessException  e) {
            e.printStackTrace();
        }


    }
}
