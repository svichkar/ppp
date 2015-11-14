package com.nixsolutions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import exception.Save;

/**
 * Created by Rybkinrolla on 13.11.2015.
 */
public class SaveStringToFile implements Save {
    @Override
    public void save(String inputString, String path) {
        File inputFile = new File(path);
        if (!inputFile.exists()) {
            try {
                inputFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        /** Autocloseable */
        try (Writer wr = new FileWriter(inputFile, false)) {
            wr.write(inputString);
            wr.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
