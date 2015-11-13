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

        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file, false);
            outputStream.write(text.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            }  catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
