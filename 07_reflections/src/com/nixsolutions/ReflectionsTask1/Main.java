package com.nixsolutions.ReflectionsTask1;

/**
 * Created by sobolenko on 2/15/2016.
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        UtilClass utilClass = new UtilClass();
        Class cl = Class.forName("com.nixsolutions.ReflectionsTask1.TestClass");
        utilClass.getField(cl.newInstance(), "name");
        System.out.println(utilClass.getFieldsByGoogleReclect(cl.newInstance(), "count"));
    }
}
