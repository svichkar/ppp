package com.manetskiy;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        FilesCreator fc = new FilesCreator("root");
        FilesHandler f = new FilesHandler();
        File source = new File("root");
        File target = new File("target2");

        try {
            //create directories and files with data
            fc.createFiles();

            //select copy method
            //f.copyIO(source, target);
            //f.copyNIO(source, target);
            //f.copyFileUtils(source, target);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
