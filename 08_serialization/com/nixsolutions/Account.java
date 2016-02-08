package com.nixsolutions;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Scanner;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    public Long id;
    public String name;
    public String role;

    public Account() {
        this.id = 0L;
        this.name = "";
        this.role = "";
    }
    
    public Account (Long id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }
    
    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        String[] sa = stream.readUTF().split(";");
        id = Long.valueOf(sa[0]);
        name = sa[1];
        role = sa[2];
    }

    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException {
        stream.writeUTF(id + ";" + name + ";" + role);
    }

    public static void main(String[] args) {
        Account a = new Account();
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Please enter the account ID: ");
        a.id = scan.nextLong();
        
        System.out.print("Please enter the account name: ");
        a.name = scan.next();
        
        System.out.print("Please enter the account name: ");
        a.role = scan.next();
        
        System.out.println("Please enter name and path of the .dat file to save the account: ");
        String file = scan.next() + ".dat";
                
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(a);
            System.out.println("Completed.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            
        }

    }
}
