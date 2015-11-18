package com.nixsolutions;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by kozlovskij on 11/18/2015.
 */
public class MyIO {
    public static void isDir(File file) {

        if (file.isDirectory()) {
            System.out.println(file.getAbsolutePath());
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                isDir(files[i]);
            }
        }
    }

    public static void main(String[] args) {
        String InputDir = "D:\\IO";
        File input = new File(InputDir);

        isDir(input);
    }
}
