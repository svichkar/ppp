package com.nixsolutions;

import exception.Save;
import java.io.IOException;
import java.nio.file.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


/**
 * Created by konstantin on 16.11.2015.
 */
public class SaveFile implements Save {

    private static Logger logger = LogManager.getLogger(SaveFile.class.getName());

    @Override
    public void save(String text, String filePath) {

        Path path = Paths.get(filePath);

        if (path.isAbsolute() == false)
            logger.error("1", new RuntimeException("Specified path is not absolute."));
            //throw new RuntimeException("Specified path is not absolute.");

        if (Files.exists(path) == false) {
            try {
                logger.info("File by path does not exists. It will be created.");
                Files.createDirectories(path.getParent());
                Files.createFile(path);
                Files.write(path, text.getBytes());
            } catch (IOException io) {
                logger.error("2", new RuntimeException("File cannot be created or written.", io));
                //throw new RuntimeException("File cannot be created or written.", io);
            }
        } else {
            try {
                Files.write(path, text.getBytes());
            } catch (IOException io) {
                logger.error("3", new RuntimeException("File cannot be written.", io));
                //throw new RuntimeException("File cannot be written.", io);
            }
        }
    }
}
