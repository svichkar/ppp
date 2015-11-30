package com.nixsolutions;

import java.io.IOException;

/**
 * @author Sirotkin Mikhail
 * Claas that use and check our methods for serialization/deserialization
 */
public class CheckSerialization {

     public static void main(String[] args) throws IOException, ClassNotFoundException {
         ObjectForSerialization obj = new ObjectForSerialization(666L, "Need to be serialized");
         System.out.println("First step:");
         obj.outCurrentObjectInfo();
         byte[] serialyzedObject = CustomSerialization.serializationAndZip(obj);
         ObjectForSerialization newObj = CustomSerialization.deserializationAndUnzip(serialyzedObject);
         System.out.println("Second step:");
         newObj.outCurrentObjectInfo();
    }
}
