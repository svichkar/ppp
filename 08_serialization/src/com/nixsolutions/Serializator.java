package com.nixsolutions;

import java.io.*;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by svichkar on 11/23/2015.
 */
public class Serializator {

    public static void main(String args[]) {

        TestClass before = new TestClass();
        TestClass after = new TestClass();

        before.setType("updated");
        before.setGood(false);
        before.setIndex(0);
        before.setCurrentDate(new Date());

        System.out.println("Fields of class before serialization:");
        System.out.println(String.format("type: %s.", before.getType()));
        System.out.println(String.format("index: %s;", before.getIndex()));
        System.out.println(String.format("isGood: %s;", before.getGood()));
        System.out.println(String.format("currentDate: %s;", before.getCurrentDate()));

        try {
            after = deserialize(serialize(before));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("\nFields of class after serialization:");
        System.out.println(String.format("type: %s.", after.getType()));
        System.out.println(String.format("index: %s;", after.getIndex()));
        System.out.println(String.format("isGood: %s;", after.getGood()));
        System.out.println(String.format("currentDate: %s;", after.getCurrentDate()));

        //Check that 'index' field is not serialized because it's marked as transient
        System.out.println("Values for 'index' field (marked as transient) are equal before and after serialization?");
        System.out.println(before.getIndex().equals(after.getIndex()));
    }

    /**
     * @param object - entity of class
     * @param <T>    - generic type of object
     * @return bytes array of zipped object
     * @throws IOException
     */
    public static <T> byte[] serialize(T object) throws IOException {

        ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();

        ZipOutputStream zipOut = new ZipOutputStream(bytesOut);
        zipOut.putNextEntry(new ZipEntry("entry"));

        ObjectOutputStream oos = new ObjectOutputStream(zipOut);
        oos.writeObject(object);

        oos.flush();
        oos.close();

        return bytesOut.toByteArray();
    }

    /**
     * @param byteArray - bytes array to be converted into object
     * @param <T>       - generic type of output object
     * @return object of specified type
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static <T> T deserialize(byte[] byteArray) throws IOException, ClassNotFoundException {

        ByteArrayInputStream bytesIn = new ByteArrayInputStream(byteArray);

        ZipInputStream zipIn = new ZipInputStream(bytesIn);
        zipIn.getNextEntry();

        ObjectInputStream stream = new ObjectInputStream(zipIn);
        T object = (T) stream.readObject();

        stream.close();

        return object;
    }
}
