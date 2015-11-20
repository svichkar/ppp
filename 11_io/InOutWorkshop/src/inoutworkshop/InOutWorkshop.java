/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inoutworkshop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 * I/O task
 *
 * @author mednorcom
 */
public class InOutWorkshop {

    /**
     * Copies Directory and all it's contents to the given directory
     *
     * @param pathFrom path to the source directory
     * @param pathTo path to the destination directory
     * @throws IOException
     */
    public static void copyDirectory(String pathFrom, String pathTo) throws IOException {
        File source;
        try {

            source = new File(pathFrom);
        } catch (NullPointerException ex) {
            throw (new FileNotFoundException("Source directory cannot be null"));
        }
        File destination;
        try {
            destination = new File(pathTo);
        } catch (NullPointerException ex) {
            throw (new FileNotFoundException("Destination directory cannot be null"));
        }

        FileUtils.copyDirectory(source, destination);
    }

    /**
     * Input/output task endpoint
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            InOutWorkshop.copyDirectory("C:\\spark-1.5.0-bin-hadoop2.6", "D:\\test2");

        } catch (IOException ex) {
            Logger.getLogger(InOutWorkshop.class.getName()).log(Level.SEVERE,
                    "Error occured during copying", ex);
        }

    }

}
