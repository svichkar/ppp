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

    public static final int DIR_COUNT = 10;
    public static final String ROOT_DIR = "testFolder";

    public static void main(String[] args) {

        File srcFolder = null;

        try {
            srcFolder = createFileStructure(ROOT_DIR, DIR_COUNT);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Files tree was not created. Check error and try again.");
            System.exit(0);
        }

        File destFolder = new File("destinationFolder");

        try {
            copyFolder(srcFolder, destFolder);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Folder cannot be copied. Check error and try again.");
            System.exit(0);
        }

        System.out.println("Done");
    }

    /**
     * method which copies content from one directory to another
     *
     * @param src  - source directory to copy
     * @param dest - destination directory to copy in
     * @throws IOException
     */
    public static void copyFolder(File src, File dest) throws IOException {

        if (src.isDirectory()) {

            if (!dest.exists()) {
                dest.mkdir();
            }

            String files[] = src.list();
            for (String file : files) {
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                //recursive copy
                copyFolder(srcFile, destFile);
            }

        } else {
            //if file, then copy it
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

    /**
     * method creates folders and files tree
     *
     * @param rootFolder - name of root folder
     * @param count      - count of folder at the same level
     * @return File of root directory
     * @throws IOException
     */
    private static File createFileStructure(String rootFolder, int count) throws IOException {

        File[] rootFiles = fillFolder(rootFolder, count);

        for (File f : rootFiles) {
            if (f.isDirectory()) {
                for (File child : fillFolder(f.getPath(), count)) {
                    if (child.isDirectory()) {
                        fillFolder(child.getPath(), count);
                    }
                }
            }
        }

        System.out.println("Files tree is created. Root directory: " + rootFolder);
        return new File(rootFolder);
    }

    /**
     * method creates and fills specified directory with random folders and files
     *
     * @param path   - name of folder
     * @param number - count of created folders within
     * @return File[] - array of created Files (both folders and files)
     * @throws IOException
     */
    private static File[] fillFolder(String path, int number) throws IOException {

        int folderCount = 0;

        File root = new File(path);
        if (!root.exists()) {
            root.mkdir();
        }

        while (folderCount != number) {

            Random random = new Random();
            int index = random.nextInt(2);

            File file;
            String fileName = String.valueOf(random.nextInt(1000000000));

            switch (index) {

                case 0: {
                    file = new File(root.getPath() + "/file_" + fileName);
                    file.createNewFile();
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
        return root.listFiles();
    }
}
