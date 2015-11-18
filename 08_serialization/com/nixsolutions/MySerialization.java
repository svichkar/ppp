package com.nixsolutions;


import java.io.*;
import java.lang.String;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


/**
 * Created by kozlovskij on 11/18/2015.
 */


public class MySerialization<E> {

    public byte[] serializeAndPack(E input) {

        byte[] bytes = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZipOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            gZipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            objectOutputStream = new ObjectOutputStream(gZipOutputStream);
            objectOutputStream.writeObject(input);
            gZipOutputStream.finish();

            bytes = byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (gZipOutputStream != null) {
                    gZipOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return bytes;
    }

    public E deSerializeAndUnPack(byte[] bytes) {

        E deSerializeTest = null;

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new GZIPInputStream(new ByteArrayInputStream(bytes)))) {
            deSerializeTest = (E) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deSerializeTest;
    }

    public static void main(String[] args) {
        MySerialization<TransferObject> transform = new MySerialization<>();
        TransferObject test = new TransferObject(1L, "Test");


        System.out.println(test.toString());

        System.out.println(transform.deSerializeAndUnPack(transform.serializeAndPack(test)).toString());

        MySerialization<String> transformForString = new MySerialization<>();
        String someString = "someString";
        System.out.println(someString);
        byte [] temp = transformForString.serializeAndPack(someString);
        String someAnotherString;
        someAnotherString = transformForString.deSerializeAndUnPack(temp);
        System.out.println(someAnotherString);
    }
}


