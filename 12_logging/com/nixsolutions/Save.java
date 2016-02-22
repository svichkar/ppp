package com.nixsolutions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** Saves the string to file based on user input */
public class Save implements exception.Save {
    private static final Logger LOG = LogManager.getLogger();

    /**
     * Based on received parameters, saves a string to a file
     * 
     * @param arg0
     *            String to write to a file
     * @param arg1
     *            Absolute path to a file
     */
    @Override
    public void save(java.lang.String arg0, java.lang.String arg1) {
        LOG.entry(arg0, arg1);
        FileWriter fw = null;
        File file = null;

        try {
            file = new File(arg1);

            LOG.trace("Performing check if the file exists and if path is absolute.");
            if (file.exists()) {
                throw new FileAlreadyExistsException(arg1);
            } else if (!file.isAbsolute()) {
                String warnMessage = ("Path is not absolute, trying to save to the launch path.");
                System.err.println(warnMessage);
                LOG.warn(warnMessage);
                file.createNewFile();
            }

            fw = new FileWriter(file);

            LOG.debug("String to save: " + arg0);
            LOG.debug("File to save: " + file);
            LOG.trace("Writing the file.");
            fw.write(arg0);
            fw.flush();

            System.out.println("\nFile saved.");
            LOG.info("String \"" + arg0 + "\" saved to file " + arg1);
        } catch (FileAlreadyExistsException e) {
            System.err.println("File \"" + arg1 + "\" already exists, so exiting without saving.");
            LOG.error("File already exists:", e);
        } catch (IOException e) {
            System.err.println("I/O error occurred, exiting.");
            LOG.error("I/O error occurred during file write.", e);
        } finally {
            if (fw != null) {
                try {
                    LOG.trace("Closing the FileWriter.");
                    fw.close();
                } catch (IOException e) {
                    System.err.println("I/O error occurred, exiting.");
                    LOG.error("I/O error occurred during closing of FileWriter.", e);
                }
            }
            LOG.exit();
        }
    }

    /**
     * Scans the string to save to file.
     * 
     * @return Returns the string entered by user.
     */
    private static String scanString() {
        LOG.entry();
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Enter the string to save to file:");
        String string = scan.nextLine();
        
        scan = null;
        
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
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Enter the absolute path to a new file to be created with the string:");
        String path = scan.nextLine();
        
        scan = null;
        
        LOG.trace("Scanned the path to file: " + path);
        LOG.exit(path);
        return path;
    }

    /** Saves the string to file based on user inputs. */
    public static void main(String[] args) {
        LOG.entry(args);
        Save sv = new Save();
        
        LOG.trace("Asking for string and path before saving.");
        sv.save(scanString(), scanPath());
        LOG.exit();
    }
}