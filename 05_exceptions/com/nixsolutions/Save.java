package com.nixsolutions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Scanner;

/** Saves the string to file based on user input */
public class Save implements exception.Save {

    /**
     * Based on received parameters, saves of a string to a file
     * 
     * @param arg0
     *            String to write to a file
     * @param arg1
     *            Absolute path to a file
     */
    @Override
    public void save(java.lang.String arg0, java.lang.String arg1) {
        FileWriter fw = null;
        File file = null;

        try {
            file = new File(arg1);
            if (file.exists()) {
                throw new FileAlreadyExistsException(arg1);
            } else if (!file.isAbsolute()) {
                System.out.println(
                        "Path is not absolute, trying to save to the launch path.");
                file.createNewFile();
            }
            fw = new FileWriter(file);
            fw.write(arg0);
            fw.flush();
            System.out.println("\nFile saved.");
        } catch (FileAlreadyExistsException e) {
            System.out.println(
                    "File \"" + arg1 + "\" already exists, so not saving.");
            e.printStackTrace(System.err);
        } catch (IOException e) {
            System.out.println("I/O error occurred, exiting.");
            e.printStackTrace(System.err);
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    System.out.println("I/O error occurred, exiting.");
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    /**
     * Scans the string to save to file.
     * 
     * @return Returns the string entered by user.
     */
    private static String scanString() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the string to save to file:");
        String string = scan.nextLine();
        scan = null;
        return string;
    }

    /**
     * Scans the absolute path to save the file to.
     * 
     * @return Returns the path entered by user.
     */
    private static String scanPath() {
        Scanner scan = new Scanner(System.in);
        System.out.println(
                "Enter the absolute path to a new file to be created with the string:");
        String path = scan.nextLine();
        scan = null;
        return path;
    }

    /** Saves the string to file based on user inputs. */
    public static void main(String[] args) {
        Save sv = new Save();
        sv.save(scanString(), scanPath());
    }
}