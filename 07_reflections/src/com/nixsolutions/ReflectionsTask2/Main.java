package com.nixsolutions.ReflectionsTask2;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * Created by sobolenko on 2/12/2016.
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        ReflectClassLoager rcl = new ReflectClassLoager(Main.class.getClassLoader());
        rcl.setPath("D:\\JAVA\\javappp\\07_reflections\\src\\com");
        Class newClass = rcl.loadClass("GetCurrentTime");
        System.out.println(newClass.getName());
        System.out.println(Arrays.toString(newClass.getDeclaredMethods()));
    }
}
