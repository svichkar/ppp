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
     * @throws FileAlreadyExists
     */
    @Override
    public void save(String input, String pathToFile) {
        File file = new File(pathToFile);
        FileWriter fw = null;
        if (!file.exists()) {
            try {
                file.createNewFile();
                fw = new FileWriter(file);
                fw.write(input);
                fw.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (fw != null) {
                    try {
                        fw.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } else {
            throw new FileAlreadyExists();
        }
    }

}
