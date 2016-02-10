package com.nixsolutions;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CustomSerializable {

    public static <T> byte[] doSerialize(T obj) throws IOException {
        if (!(obj instanceof java.io.Serializable)) {
            throw new NotSerializableException("Class " + obj.getClass() + " not implements Serializable");
        }
        try (ByteArrayOutputStream b = new ByteArrayOutputStream(); GZIPOutputStream gzip = new GZIPOutputStream(b);
             ObjectOutputStream out = new ObjectOutputStream(gzip)) {
            out.writeObject(obj);
            out.flush();
            gzip.finish();
            return b.toByteArray();
        } catch (IOException e) {
            throw e;
        }
    }

    public static <T> T doDeserialize(byte[] byteArray) throws IOException, ClassNotFoundException {
        try (ObjectInputStream out = new ObjectInputStream(new GZIPInputStream(new ByteArrayInputStream(byteArray)))) {
            return (T) out.readObject();
        } catch (IOException e) {
            throw e;
        }
    }

}
