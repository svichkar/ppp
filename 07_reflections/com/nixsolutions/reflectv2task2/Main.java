package com.nixsolutions.reflectv2task2;

import java.util.Scanner;

/** Example of usage of custom ClassLoader */
public class Main {

    /** Tries to load the class from the path based on user input. */
    public static void main(String[] args) throws InstantiationException,
            IllegalAccessException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the additional path for class loading:");
        String path = scanner.nextLine();

        System.out.println("Enter the name of the class to load:");
        String name = scanner.nextLine();

        scanner.close();

        PathClassLoader pathClassLoader = new DirClassLoader();
        pathClassLoader.setPath(path);

        ClassLoader loader = (ClassLoader) pathClassLoader;

        Object object = loader.loadClass(name).newInstance();
        System.out.println("Loaded and instantiated: " + object.getClass());
    }

}
