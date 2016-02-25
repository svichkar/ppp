package com.manetskiy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class Main {

    public static void main(String[] args) {

        //specify classpath in constructor if needed
        ClassLoader cl = new MyClassLoader();
        Class clazz = null;

        try {
            //specify binary class name to load
            clazz = cl.loadClass("com.manetskiy.MyClassLoader");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("All declared methods: ");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(m.getName());
        }


        System.out.println("\nAll declared fields: ");
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            System.out.println(f.getName());
        }
    }
}
