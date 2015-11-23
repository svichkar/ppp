/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searializationworkshop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.InvalidAttributeValueException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author mednorcom
 */
public class SearializationWorkshop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String serializationPath = "out/serialized.dat";
        Account original = null;
        try {
            original = new Account(1055, "Vasiliy Doe", "Killer");
        } catch (InvalidAttributeValueException ex) {
            Logger.getLogger(SearializationWorkshop.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (ObjectOutputStream outputStream
                = new ObjectOutputStream(FileUtils.openOutputStream(new File(serializationPath)))) {
            outputStream.writeObject(original);
        } catch (IOException ex) {
            Logger.getLogger(SearializationWorkshop.class.getName()).log(Level.SEVERE, null, ex);
        }
        Account deserialized = null;
        try (ObjectInputStream inputStream
                = new ObjectInputStream(FileUtils.openInputStream(new File(serializationPath)))) {
            deserialized = (Account) inputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(SearializationWorkshop.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
