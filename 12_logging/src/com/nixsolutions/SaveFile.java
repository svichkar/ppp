package com.nixsolutions;

import exception.Save;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Created by konstantin on 16.11.2015.
 */
public class SaveFile implements Save {

    private static final Logger LOG = LogManager.getLogger(SaveFile.class);

    @Override
    public void save(String text, String filePath) {

        LOG.entry();
        Path path = Paths.get(filePath);

        if (path.isAbsolute() == false) {
            LOG.error("Path \"{}\" is not absolute.", path);
            throw new RuntimeException("Specified path is not absolute.");
        }

        if (Files.exists(path) == false) {
            try {
                LOG.info("File by path does not exists. It will be created.");
                Files.createDirectories(path.getParent());
                LOG.debug("Directory \"\" {}", path.getParent());
                Files.createFile(path);
                Files.write(path, text.getBytes());

            } catch (IOException io) {
                LOG.error(io);
                throw new RuntimeException("File cannot be created or written.", io);
            }
        } else {
            try {
                Files.write(path, text.getBytes());

            } catch (IOException io) {
                LOG.error(io);
                throw new RuntimeException("File cannot be written.", io);
            }
        }

    }
}
