package com.nixsolutions;

import java.io.File;

/**
 * @author Sirotkin mikhail
 * Main method set external class from directory 'currentProjectDirectory + externalClasses'
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        CustomClassLoader testLoader = new CustomClassLoader();
        //get path to current directory with project
        String path = new File("").getAbsolutePath();
        //set path for our ClassLoader /currentProjectDir/externalClasses"
        testLoader.setPath(path + File.separator + "externalClasses" + File.separator);
        //use overrided method find class to get class by name from external directory
        Class cl = testLoader.findClass("ExampleClass");
        //print out class name that we get on previous step
        System.out.println(cl.getName());
    }
}
