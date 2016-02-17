package com.nixsolutions.loader;

import java.lang.reflect.Method;

public class TestLoader {

    public static void main(String[] argc) {
        String path = "D:\\IdeaProjects\\javappp\\02_oop";
        String binaryClassName = "com.nixsolutions.Square";

        CustomClassLoader ccl = new CustomClassLoader(path);
        try {
            //firstly class loaded
            Class uploadClass = ccl.loadClass(binaryClassName);
            System.out.println("define class\n");
            System.out.println(uploadClass.getName());
            for (Method m : uploadClass.getMethods()) {
                System.out.println(m.getName());
            }
            System.out.println("\n\n");

            //secondly class return from cache
            Class uploadClass2 = ccl.loadClass(binaryClassName);
            System.out.println("return from cache\n");
            System.out.println(uploadClass2.getName());
            for (Method m : uploadClass.getMethods()) {
                System.out.println(m.getName());
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
