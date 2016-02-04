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
            } else {
                file.createNewFile();
                fw = new FileWriter(file);
                fw.write(arg0);
                fw.flush();
                System.out.println("\nDone");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Asks the user to enter the string to write to a file, and an absolute
     * path to save the file
     * 
     * @param args
     *            Command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the string to save to file: ");
        String string = scan.nextLine();

        System.out.println("Enter the absolute path to file: ");
        String path = scan.nextLine();
        
        scan.close();

        Save sv = new Save();
        sv.save(string, path);
    }
}
