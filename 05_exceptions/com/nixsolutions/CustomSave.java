package com.nixsolutions;

import java.io.*;

/**
 * Created by kozlovskij on 11/13/2015.
 */
public class CustomSave implements exception.Save {
    @Override
    public void save(String text, String path) throws RuntimeException {

        File file = new File(path);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Writer writer = null;
        try {
            writer = new FileWriter(file, false);
            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
