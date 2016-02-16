package com.nixsolutions.ReflectionsTask2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by sobolenko on 2/12/2016.
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        ReflectClassLoager rcl = new ReflectClassLoager(Main.class.getClassLoader());
        rcl.setPath("\\src\\com");
        Class newClass = rcl.loadClass("GetCurrentTime");
        //Class newClass = rcl.findClass("D:\\JAVA\\javappp\\04_dates\\target\\classes\\MyCalendar.class");
    }
}
