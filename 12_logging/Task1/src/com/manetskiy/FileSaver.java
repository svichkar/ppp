package com.manetskiy;

import exception.Save;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSaver implements Save {

    private static final Logger logger = LogManager.getLogger();

    public void save(String inputText, String filePath) {
        logger.debug("Creating new file object.");
        File file = new File(filePath);
        logger.debug("File object has been created.");
        logger.debug("Checking path.");
        if (file.isAbsolute()) {
            logger.debug("Path is absolute.");
            logger.debug("Checking if file already exists.");
            if (file.exists()) {
                logger.error("{} already exists.", filePath);
                throw new FileExsistsException(file.getAbsolutePath() + " already exists.");
            } else {
                Path path = Paths.get(filePath);
                try {
                    logger.debug("Creating file from file object.");
                    Files.createDirectories(path.getParent());
                    Files.createFile(path);
                } catch (IOException e) {
                    logger.error("Failed to create file from file object.");
                    throw new RuntimeException(e);
                }
                try (FileWriter writer = new FileWriter(file)) {
                    logger.debug("Writing data into the {}", filePath);
                    writer.write(inputText);
                    logger.debug("Data has been written successfully.");
                } catch (IOException e) {
                    logger.error("Failed to write data into the file.");
                    throw new RuntimeException(e);
                }
            }
        } else {
            logger.error("Path is relative.");
            throw new RuntimeException("File path is not absolute");
        }
    }
}
