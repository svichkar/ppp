package com.nixsolutions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** Saves the string to file based on user input */
public class Save implements exception.Save {
    private static final Logger LOG = LogManager.getLogger();
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
        LOG.entry(arg0, arg1);
        File file = new File(arg1);
        LOG.trace("Performing check if the file exists and if path is absolute.");
        if (file.exists()) {
            throw new FileExistsException(arg1);
        } else if (!file.isAbsolute()) {
            String warnMessage = ("Path is not absolute, trying to save to the launch path...");
            System.err.println(warnMessage);
            LOG.warn(warnMessage);
        }
        try (FileWriter fw = new FileWriter(file)) {
            file.createNewFile();
            LOG.debug("String to save: " + arg0);
            LOG.debug("File to save: " + file);
            LOG.trace("Writing the file.");
            fw.write(arg0);
            fw.flush(); // FileWriter should be closed automatically after try-with-resources.
            
            System.out.println("\nFile saved.");
            LOG.info("String \"" + arg0 + "\" saved to file " + arg1);
        } catch (IOException e) {
            System.err.println("I/O error occurred, exiting.");
            LOG.error("I/O error occurred during saving of the file.", e);
        }
        LOG.exit();
    }

    /**
     * Scans the string to save to file.
     * 
     * @return Returns the string entered by user.
     */
    private static String scanString() {
        LOG.entry();
        System.out.println("Enter the string to save to file:");
        String string = scan.nextLine();
        LOG.trace("Scanned the string to save to file: " + string);

        LOG.exit(string);
        return string;
    }

    /**
     * Scans the absolute path to save the file to.
     * 
     * @return Returns the path entered by user.
     */
    private static String scanPath() {
        LOG.entry();
        System.out.println("Enter the absolute path to a new file to be created with the string:");
        String path = scan.nextLine();
        LOG.trace("Scanned the path to file: " + path);

        LOG.exit(path);
        return path;
    }

    /**
     * Based on user inputs, tries to save string to a new file, and keeps
     * asking for path if the file already exists.
     */
    private static void saveStringToFile(Save saver, String string, String path) {
        LOG.entry(saver, string, path);
        try {
            LOG.trace("Calling saver to save given string to file.");
            saver.save(string, path);
        } catch (FileExistsException e) {
            System.out.println("File \"" + path
                    + "\" already exists, please provide another path.");
            LOG.error("File already exists:", e);
            saveStringToFile(saver, string, scanPath());
        }
        LOG.exit();
    }

    /** Saves the string to file based on user inputs. */
    public static void main(String[] args) {
        LOG.entry((Object[]) args);
        scan = new Scanner(System.in);

        LOG.trace("Asking for string and path before saving.");
        saveStringToFile(new Save(), scanString(), scanPath());
        scan.close();
        LOG.exit();
    }
}