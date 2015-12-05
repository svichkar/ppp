package com.nixsolutions.task2;

/**
 * Created by svichkar on 12/1/2015.
 */
public class Main {

    public static void main(String args[]) {

        MyClassLoader loader = new MyClassLoader();
        loader.setPath("C:\\gitRepo\\javappp\\11_io\\out\\production\\InputOutput");
        Class loadClass = null;
        try {
            loadClass = loader.loadClass("com.nixsolutions.CopyFilesStructure");
            System.out.println(String.format("Class by path '%s' OR from CLASSPATH was loaded.", loader.getPath()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Object object;
        try {
            object = loadClass.newInstance();
            System.out.println(String.format("Instance of %s was created. Object: %s", loadClass, object));
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
