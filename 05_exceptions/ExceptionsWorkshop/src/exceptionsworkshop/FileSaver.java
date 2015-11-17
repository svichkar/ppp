/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptionsworkshop;

import exception.Save;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Saves string to file
 *
 * @author mednorcom
 */
public class FileSaver implements Save {

    /**
     * Saves string to file
     *
     * @param string - text
     * @param string1 - file and path
     */
    @Override
    public void save(String string, String string1) {
        FileWriter file = null;
        try {
            file = new FileWriter(string1);
            file.write(string);

        } catch (IOException ex) {
            throw new RuntimeIOException(ex);
        } finally {
            try {
                if (file != null) {
                    file.close();
                }
            } catch (IOException ex) {
                throw new RuntimeIOException(ex);
            }
        }
    }
}
