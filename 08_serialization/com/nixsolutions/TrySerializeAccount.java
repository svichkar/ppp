package com.nixsolutions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Rybkinrolla on 19.11.2015.
 */
public class TrySerializeAccount {
    public static void main(String[] args) {
        Account acc = new Account(1232423423421L,"Merlin","Wizard");
        Account otherAcc;
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream("D:\\input.dat"));
            ooStream.writeObject(acc);
            ooStream.flush();
            ooStream.close();
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream("D:\\input.dat"));
            otherAcc = (Account) oiStream.readObject();
            oiStream.close();
            System.out.println(acc.writeAccountToString()+ " - initial object");
            System.out.println(otherAcc.writeAccountToString() + " - deserialized object");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
