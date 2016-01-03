package com.nixsolutions;

import java.io.*;
import java.util.zip.*;

/**
 * @author Sirotkin Mikhail
 * Class that contains serialization and deserialization methods
 */
public class CustomSerialization {

    /**
     * Method serializes object and archives it using zip. Then convert it to array of bytes
     * @param object to serialize
     * @param <T> object's class type
     * @return array of bytes
     * @throws IOException
     */
    public static <T> byte[] serializationAndZip(T object) throws IOException{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        GZIPOutputStream outputZipStream = new GZIPOutputStream(outputStream);
        ObjectOutputStream objStream = new ObjectOutputStream(outputZipStream);
        objStream.writeObject(object);
        objStream.close();
        return outputStream.toByteArray();
    }

    /**
     * Method restore object from byte massive (unzip and serialize stream of bytes)
     * @param arr - object in zerialized representation
     * @param <T> - type of returnable object
     * @return T object after deserialization
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static <T> T deserializationAndUnzip(byte[] arr) throws IOException, ClassNotFoundException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(arr);
        GZIPInputStream inputZipStream = new GZIPInputStream(inputStream);
        ObjectInputStream objStream = new ObjectInputStream(inputZipStream);
        T result = (T) objStream.readObject();
        objStream.close();
        return result;
    }

}
