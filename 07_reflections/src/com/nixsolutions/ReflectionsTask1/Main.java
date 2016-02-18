package com.nixsolutions.ReflectionsTask1;

import com.nixsolutions.ReflectionsTask1.StandartLib.UtilClass;

/**
 * Created by sobolenko on 2/15/2016.
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        UtilClass utilClass = new UtilClass();
        com.nixsolutions.ReflectionsTask1.ExternalLib.UtilClass utilClassGoogleLib = new com.nixsolutions.ReflectionsTask1.ExternalLib.UtilClass();
        Class cl = Class.forName("com.nixsolutions.ReflectionsTask1.TestClass");
        System.out.println(utilClass.getField(cl.newInstance(), "name"));
        System.out.println(utilClassGoogleLib.getField(cl.newInstance(), "count"));
    }
}
