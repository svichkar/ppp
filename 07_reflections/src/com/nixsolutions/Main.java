package com.nixsolutions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by sobolenko on 2/12/2016.
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        ReflectClassLoager rcl = new ReflectClassLoager(ClassLoader.getSystemClassLoader());
        rcl.setPath("D:\\Homework_3\\target\\classes\\DBase.class");
        Class newClass = rcl.findClass("DBase");
        System.out.println(Arrays.toString(newClass.getMethods()));
        //------------------
        Method connect = newClass.getMethod("createConnection", String.class, String.class, String.class, String.class);
        Method request = newClass.getMethod("sendRequest", String.class);
        connect.setAccessible(true);
        connect.invoke(newClass.newInstance(),"localhost", "LexxN", "Deanery", "Aq134679");
        Object obj = request.invoke(newClass.newInstance(),"select * from Student");
    }
}
