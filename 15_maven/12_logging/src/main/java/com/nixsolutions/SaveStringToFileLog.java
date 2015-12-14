package com.nixsolutions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import exception.Save;

/**
 * Created by Rybkinrolla on 13.11.2015.
 */
public class SaveStringToFileLog implements Save {
    private static final Logger customLog = LogManager.getLogger(SaveStringToFileLog.class);
    @Override
    public void save(String inputString, String path) {
        customLog.entry("Start save method");
        File inputFile = new File(path);
        if (!inputFile.exists()) {
            try (Writer wr = new FileWriter(inputFile, false)) {
                inputFile.createNewFile();
                customLog.info("File created");
                wr.write(inputString);
                customLog.info("String \"{}\" is written",inputString);
                wr.flush();
            } catch (IOException e) {
                customLog.catching(e);
                throw new RuntimeException(e);
            }
        }
        customLog.exit();
    }
}
