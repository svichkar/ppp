package com.nixsolutions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/** Saves the string to file based on user input */
public class Save implements exception.Save {
    private static Scanner scan;

    /**
     * Based on received parameters, saves a string to a file
     * 
     * @param arg0
     *            String to write to a file
     * @param arg1
     *            Absolute path to a file
     * @throws FileExistsException
     *             Throws exception if the file already exists.
     */
    @Override
    public void save(java.lang.String arg0, java.lang.String arg1) {
        File file = new File(arg1);
        if (file.exists()) {
            throw new FileExistsException(arg1);
        } else if (!file.isAbsolute()) {
            System.out.println("Path is not absolute, trying to save to the launch path...");
        }
        try (FileWriter fw = new FileWriter(file)) {
            file.createNewFile();
            fw.write(arg0);
            fw.flush();
            System.out.println("\nFile saved.");
        } catch (IOException e) {
            System.err.println("I/O error occurred, exiting.");
            e.printStackTrace(System.err);
        }
    }

    /**
     * Scans the string to save to file.
     * 
     * @return Returns the string entered by user.
     */
    private static String scanString() {
        System.out.println("Enter the string to save to file:");
        String string = scan.nextLine();
        return string;
    }

    /**
     * Scans the absolute path to save the file to.
     * 
     * @return Returns the path entered by user.
     */
    private static String scanPath() {
        System.out.println("Enter the absolute path to a new file to be created with the string:");
        String path = scan.nextLine();
        return path;
    }

    /**
     * Based on user inputs, tries to save string to a new file, and keeps
     * asking for path if the file already exists.
     */
    private static void saveStringToFile(Save saver, String string, String path) {
        try {
            saver.save(string, path);
        } catch (FileExistsException e) {
            System.out.println("File \"" + path
                    + "\" already exists, please provide another path.");
            saveStringToFile(saver, string, scanPath());
        }
    }

    /** Saves the string to file based on user inputs. */
    public static void main(String[] args) {
        scan = new Scanner(System.in);
        saveStringToFile(new Save(), scanString(), scanPath());
        scan.close();
    }
}