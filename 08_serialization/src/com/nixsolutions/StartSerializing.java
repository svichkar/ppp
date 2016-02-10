package com.nixsolutions;

import java.io.*;

/**
 * Created by PAVALVL on 2/8/2016.
 */
public class StartSerializing {
    private File file = new File("forSerialization.dat");
    private Account account;

    public static void main(String[] args) {
        StartSerializing start = new StartSerializing();
        start.serialize();
        start.deserialize();
    }

    /**
     * Serializes the Accountant object as a string concatenating its fields
     */
    public void serialize() {
        ObjectOutputStream objectOutputStream = null;
        account = new Account(12, "Jack Nickolson", "CEO");
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(outputStream);
            System.out.println("Serialized object: ");
            System.out.println(account.getFullInfo());
            objectOutputStream.writeObject(account);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Deserializes the Accountant object as a string concatenating its fields
     */
    public void deserialize() {
        ObjectInputStream objectInputStream = null;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            objectInputStream = new ObjectInputStream(inputStream);
            Account deserializedObject = (Account) objectInputStream.readObject();
            System.out.println("Deserialized object: ");
            System.out.println(deserializedObject.getFullInfo());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
