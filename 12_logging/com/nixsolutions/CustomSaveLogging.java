package com.nixsolutions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by kozlovskij on 11/13/2015.
 */
public class CustomSaveLogging implements exception.Save {
    private static final Logger LOGGER = LogManager.getLogger(CustomSaveLogging.class);
    private String text;
    private String path;
    @Override
    public void save(String text, String path) throws RuntimeException {
        this.text = text;
        this.path = path;
        File file = new File(path);
        Writer writer = null;
        try {
            if (!file.exists()) {
                LOGGER.info("try creating file");
                file.createNewFile();
            }
            LOGGER.info("try copying text");
            writer = new FileWriter(file, false);
            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            LOGGER.error("file or stream are not created");
            throw new RuntimeException(e);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                LOGGER.warn("Stream isn't close");
                throw new RuntimeException(e);
            }
        }
    }

    public String getText() {
        return text;
    }

    public String getPath() {
        return path;
    }
}
