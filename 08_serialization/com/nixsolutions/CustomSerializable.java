package com.nixsolutions;

import java.io.*;

public class CustomSerializable {

    public static <T> byte[] doSerialize(T obj) throws IOException {
        if (!(obj instanceof java.io.Serializable)) {
            throw new NotSerializableException("Class " + obj.getClass() + " not implements Serializable");
        }
        try (ByteArrayOutputStream b = new ByteArrayOutputStream(); ObjectOutput out = new ObjectOutputStream(b)) {
            out.writeObject(obj);
            return b.toByteArray();
        } catch (IOException e) {
            throw e;
        }
    }

    public static <T> T doDeserialize(byte[] byteArray) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream b = new ByteArrayInputStream(byteArray); ObjectInput out = new ObjectInputStream(b)) {
            return (T) out.readObject();
        } catch (IOException e) {
            throw e;
        }
    }

}
