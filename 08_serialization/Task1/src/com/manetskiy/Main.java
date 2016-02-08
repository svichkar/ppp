package com.manetskiy;


import java.io.*;

public class Main {
    public static void main(String[] args) {
        Account employeeSer = new Account(121321342343L, "Smith Wesson", "supervisor");
        Account employeeDeser = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        File acc = new File("storage.bin");

        try {
            fileOutputStream = new FileOutputStream(acc);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(employeeSer);

            fileInputStream = new FileInputStream(acc);
            objectInputStream = new ObjectInputStream(fileInputStream);
            employeeDeser = (Account) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectOutputStream != null) objectOutputStream.close();
                if (fileOutputStream != null) fileOutputStream.close();

                if (objectInputStream != null) objectInputStream.close();
                if (fileInputStream != null) fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Before serialization : " + employeeSer.toString());

        if (employeeDeser != null)
            System.out.println("After deserialization : " + employeeDeser.toString());
        else System.out.println("Deserialization failed.");
    }
}
