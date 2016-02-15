package com.nixsolutions.ReflectionsTask2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by sobolenko on 2/12/2016.
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        ReflectClassLoager rcl = new ReflectClassLoager(ClassLoader.getSystemClassLoader());
        rcl.setPath(System.getProperty("user.dir"));
        Class newClass = rcl.findClass("GetCurrentTime");
    }
}
