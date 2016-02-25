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

        //load class in package com.nixsolutions by next path
        testLoader.setPath("E:\\JavaPPP\\temp\\05_exceptions\\out\\production\\05_exceptions\\");

        Class c2 = testLoader.findClass("com.nixsolutions.CustomRTException");

        System.out.println(c2.getName());
    }
}
