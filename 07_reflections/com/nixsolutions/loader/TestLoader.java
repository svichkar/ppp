package com.nixsolutions.loader;

import java.lang.reflect.Method;

public class TestLoader {

    public static void main(String[] argc) {
        String path = "D:\\";
        String binaryClassName = "com.nixsolutions.WorkWithString";

        //firstly class loaded and save in local cache ccl's object
        CustomClassLoader ccl = new CustomClassLoader();
        ccl.setPath(path);
        Class uploadClass = null;
        try {
            uploadClass = ccl.loadClass(binaryClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(uploadClass.getName());
        for (Method m : uploadClass.getMethods()) {
            System.out.println(m.getName());
        }

        //secondly class loader return cached Class
        Class uploadClass2 = null;
        try {
            uploadClass2 = ccl.loadClass(binaryClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(uploadClass2.getName());

    }

}
