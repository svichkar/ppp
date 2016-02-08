package com.nixsolutions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by pantiukhin on 2/8/2016. Using the java.io package
 */
public class IOCopy {
    public static void main(String[] args) {
        File sourceDir = new File("Foder_Structure");
        File destinationDir = new File("Foder_Structure_copy");
        IOCopy io_copy = new IOCopy();
        try {
            io_copy.copyDirectories(sourceDir, destinationDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Implements some preparatory work: creates a destination directory if it doesn't exist, makes
     * sure that the source directory exists and the source and destination are not files. The calls
     * the copy implementation method
     */
    public void prepareForCopying(File sourceDir, File destinationDir) throws IOException {
        //Create the destination directory if it doesn't exist
        if (!destinationDir.exists()) {
            destinationDir.mkdirs();
        }
        //If the source directory does'nt exist, throw an IllegalArgumentException
        if (!sourceDir.exists()) {
            throw new IllegalArgumentException("The source directory doesn't exist");
        }
        if (destinationDir.isFile() || sourceDir.isFile()) {
            throw new IllegalArgumentException("One of the directories is a file");
        }
        copyDirectories(sourceDir, destinationDir);
    }

    public void copyDirectories(File sourceDir, File destinationDir) throws IOException {
        //List all the files and directories within the source directory
        File[] contents = sourceDir.listFiles();
        if (contents != null && contents.length > 0) {
            for (File f : contents) {
                //If the source item is a directory
                if (f.isDirectory()) {
                    File copyDir = new File(destinationDir, f.getName());
                    copyDir.mkdir();
                    //Copy the directory by calling the copyPreparation method again
                    prepareForCopying(f, copyDir);
                } else {
                    //If the source is a file
                    File copiedFile = new File(destinationDir, f.getName());
                    copyOneFile(f, copiedFile);
                }
            }
        }
    }

    public void copyOneFile(File sourceFile, File destinationFile) throws IOException {
        int length;
        if (!destinationFile.exists()) {
            destinationFile.createNewFile();
        }
        InputStream inStream = null;
        OutputStream outStream = null;
        try {
            inStream = new FileInputStream(sourceFile);
            outStream = new FileOutputStream(destinationFile);
            byte[] buffer = new byte[1024];
            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }
        } finally {
            if (inStream != null) {
                inStream.close();
            }
            if (outStream != null) {
                outStream.close();
            }
        }
        System.out.println("File Copied..");
    }
}
