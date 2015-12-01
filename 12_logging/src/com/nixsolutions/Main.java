package com.nixsolutions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Created by konstantin on 16.11.2015.
 */
public class Main {

    private static Logger log = LogManager.getLogger(Main.class.getName());

    public static void main(String args[]) {

        String log4jConfPath = "log4j.xml";
        PropertyConfigurator.configure(log4jConfPath);

        log.info("Starting...");
        SaveFile saveFile = new SaveFile();

        try {
            saveFile.save("Path is not absolute!!! Exception should be thrown.", "/directory/doesNotExistFile.txt");
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        try {
            saveFile.save("Write this text into file which doesn't exist.", "d:/directory/doesNotExistFile.txt");
        } catch (Exception e2) {
            e2.printStackTrace();
        }

        try {
            saveFile.save("Overwrite existing file with current text.", "d:/directory/doesNotExistFile.txt");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        log.info("Done.");
    }
}