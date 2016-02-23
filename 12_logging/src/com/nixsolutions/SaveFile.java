package com.nixsolutions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exception.Save;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by pantiukhin on 2/3/2016.
 */
public class SaveFile implements Save {
    private File fileName = new File("fileToBeSaved.txt");
    private String absPath = fileName.getAbsolutePath();
    private String stringToBeSaved = "String to be saved";
    private static Logger logger = LogManager.getLogger(SaveFile.class.getName());

    public static void main(String[] args) {
        logger.entry();
//        logger.debug("Debugging");
//        logger.trace("Tracing");
//        logger.warn("Warning");
//        logger.error("Error");
        SaveFile saveFile = new SaveFile();
        saveFile.save(saveFile.stringToBeSaved, saveFile.absPath);
        logger.exit();
    }

    /**
     * Implements the save method from the Save interface
     */
    public void save(String stringToBeSaved, String absolutePath) {
        try {
            logger.info("Prepraring to write");
            prepareFileForWriting();
            writeToFile(stringToBeSaved, absPath);
            logger.info("The string has been written to the file");
        } catch (CustomException ex) {
            logger.throwing(new CustomException(ex.getMessage()));
        }
    }

    /**
     * Prepares a file for saving (specifies the path and name)
     */
    public void prepareFileForWriting() throws CustomException {
        if (!fileName.exists()) {
            try {
                fileName.createNewFile();
            } catch (IOException ex) {
                throw new CustomException("File " + absPath + " has not been created");
            }
        } else {
            logger.error("The file already exists");
            throw new CustomException("File " + absPath + " already exists");
        }
    }

    /**
     * Writes a string to the new file
     */
    public void writeToFile(String stringToBeWrittenToFile, String path) throws CustomException {
        FileWriter writer = null;
        try {
            writer = new FileWriter(path);
            logger.warn("Warning: the string may not be written to file");
            writer.write(stringToBeWrittenToFile);
        } catch (IOException e) {
            throw new CustomException("String " + stringToBeWrittenToFile + " has not been written to the file");
        } finally {
            if (writer != null) {
                try {
                    logger.info("Closing the writer");
                    writer.close();
                } catch (IOException e) {
                    logger.error("The writer has not been closed!");
                    throw new CustomException("The writer has not been closed");
                }
            }
        }
    }
}
