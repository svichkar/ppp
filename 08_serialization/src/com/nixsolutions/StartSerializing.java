package com.nixsolutions;

import java.io.*;

/**
 * Created by PAVALVL on 2/8/2016.
 */
public class StartSerializing {
    private File file = new File("forSerialization.dat");
    private Account account = new Account();
    private String forSerialization, deserializedString;

    public static void main(String[] args) {
        StartSerializing start = new StartSerializing();
        start.changeFields(12, "Jack Nickolson", "CEO");
        start.serialize();
        start.deserialize();
    }

    /**
     * Serializes the Account object as a string concatenating its fields
     */
    public void serialize() {
        ObjectOutputStream objectOutputStream = null;
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(outputStream);
            forSerialization = account.getId() + "/" + account.getName() + "/" + account.getRole();
            System.out.println("Serialized object: ");
            System.out.println(forSerialization);
            objectOutputStream.writeObject(forSerialization);
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
     * Deserializes the Account object as a string concatenating its fields
     */
    public void deserialize() {
        ObjectInputStream objectInputStream = null;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            objectInputStream = new ObjectInputStream(inputStream);
            deserializedString = (String) objectInputStream.readObject();
            System.out.println("Deserialized object: ");
            System.out.println(deserializedString);
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

    /**
     * Changes the fields of the Account class
     */
    public void changeFields(long id, String name, String role) {
        account.setId(id);
        account.setName(name);
        account.setRole(role);
    }
}
