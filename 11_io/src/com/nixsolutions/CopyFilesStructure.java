package com.nixsolutions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

/**
 * Created by svichkar on 11/18/2015.
 */

public class CopyFilesStructure {
    public static final int DEPTH = 3;
    public static final int FOLDER_COUNT = 10;
    public static final String ROOT = "testFolder";

    public static void main(String[] args) {

        createFileStructure(ROOT, DEPTH, FOLDER_COUNT);

        File srcFolder = new File(ROOT);
        File destFolder = new File("destinationFolder");

        //make sure source exists
        if (!srcFolder.exists()) {

            System.out.println("Directory does not exist.");
            //just exit
            System.exit(0);

        } else {

            try {
                copyFolder(srcFolder, destFolder);
            } catch (IOException e) {
                e.printStackTrace();
                //error, just exit
                System.exit(0);
            }
        }

        System.out.println("Done");
    }

    private static void createFileStructure(String rootName, int depth, int count) {

        int folderCount = 0;

        File root = new File(rootName);
        root.mkdir();

        while (folderCount != count) {

            Random random = new Random();

            int index = random.nextInt(2);

            File file;
            String fileName = String.valueOf(random.nextInt(1000000000));

            switch (index) {

                case 0: {
                    file = new File(root.getPath() + "/file_" + fileName);
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }

                case 1: {
                    file = new File(root.getPath() + "/directory_" + fileName);
                    file.mkdir();
                    folderCount++;
                    break;
                }
            }
        }

        if (root.getName().equals(ROOT)) {

            for (File f : root.listFiles()) {
                folderCount = 0;
                if (f.isDirectory()) {
                    while (folderCount != count) {
                        createFileStructure(f.getPath(), DEPTH, FOLDER_COUNT);
                        root = new File(ROOT);
                    }
                }
            }
        }

    }

    public static void copyFolder(File src, File dest)
            throws IOException {

        if (src.isDirectory()) {

            //if directory not exists, create it
            if (!dest.exists()) {
                dest.mkdir();
                System.out.println("Directory copied from "
                        + src + "  to " + dest);
            }

            //list all the directory contents
            String files[] = src.list();

            for (String file : files) {
                //construct the src and dest file structure
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                //recursive copy
                copyFolder(srcFile, destFile);
            }

        } else {
            //if file, then copy it
            //Use bytes stream to support all file types
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;
            //copy the file content in bytes
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }

            in.close();
            out.close();
            System.out.println("File copied from " + src + " to " + dest);
        }
    }
}
