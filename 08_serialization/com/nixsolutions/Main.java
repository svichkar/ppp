package com.nixsolutions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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

        try {
            System.out.print("\nSerializing object... ");
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            oos.writeObject(a);
            oos.close();
            System.out.println("Completed.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            System.out.print("\nDeserializing object... ");
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            Account b = (Account) ois.readObject();
            ois.close();
            
            System.out.println("Completed. \n\nFields of deserialized object:"
                               + "\nid: " + b.id
                               + "\nname: " + b.name
                               + "\nrole: " + b.role);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
