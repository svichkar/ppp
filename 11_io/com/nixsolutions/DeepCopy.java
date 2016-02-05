package com.nixsolutions;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;

import static java.nio.file.FileSystems.getFileSystem;

public class DeepCopy {

    public static void main(String[] argc) {
        preparation();
    }

    private static void preparation() {
        String current = null;
        try {
            current = new java.io.File( "." ).getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Current dir:"+current);
        String currentDir = System.getProperty("user.dir");
        System.out.println("Current dir using System:" +currentDir);
    }

}
