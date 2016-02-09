package com.nixsolutions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 *  Tests serialization of the object of Account class. Asks the user to enter 
 *  the values for the fields of the object, and the path to a file where to
 *  save the object. Then performs serialization and deserialization of the
 *  object and prints the results.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in)/*.useDelimiter("\\n+")*/;
        Account a = new Account();
        
        System.out.print("Please enter the account ID: ");
        a.id = scan.nextLong();
        scan.skip("\n");
        
        System.out.print("Please enter the account name: ");
        a.name = scan.nextLine();

        System.out.print("Please enter the account role: ");
        a.role = scan.nextLine();

        System.out.println("Please enter name and path of the .dat file"
                           + " where to save the account:");
        String file = scan.nextLine();
        
        scan.close();
        
        if (!file.endsWith(".dat")) {
            file += ".dat";
        }

        System.out.print("\nSerializing object... ");
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(a);
            oos.flush();
            System.out.println("Completed.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            oos.close();
        }

        System.out.print("\nDeserializing object... ");
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            Account b = (Account) ois.readObject();
                        
            System.out.println("Completed. \n\nFields of deserialized object:"
                               + "\nid: " + b.id
                               + "\nname: " + b.name
                               + "\nrole: " + b.role);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            ois.close();
        }

    }
}
