package com.nixsolutions;

import exception.Save;
import java.io.IOException;
import java.nio.file.*;

/**
 * Created by konstantin on 16.11.2015.
 */
public class SaveFile implements Save {

    @Override
    public void save(String text, String filePath) {

        Path path = Paths.get(filePath);

        if (path.isAbsolute() == false)
            throw new RuntimeException("Specified path is not absolute.");

        if (Files.exists(path) == false) {
            try {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
                Files.write(path, text.getBytes());
            } catch (IOException io) {
                throw new RuntimeException("File cannot be created or written.", io);
            }
        } else {
            try {
                Files.write(path, text.getBytes());
            } catch (IOException io) {
                throw new RuntimeException("File cannot be written.", io);
            }
        }
    }
}
