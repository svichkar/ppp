package com.nixsolutions;

import exception.Save;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class Saver implements {@link Save} helps save input line to the file
 *
 * @author Dmitry Zinovyi
 */
public class Saver implements Save {

    /**
     * Save to file input string
     *
     * @param input      saving string
     * @param pathToFile path to the file
     * @throws java.nio.file.FileAlreadyExistsException
     */
    @Override
    public void save(String input, String pathToFile) {
        File file = new File(pathToFile);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                throw new FileAlreadyExists();
            } catch (FileAlreadyExists fileAlreadyExists) {
                try (FileWriter fw = new FileWriter(file)) {
                    fw.write(input);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
