package com.nixsolutions;

import java.io.IOException;

public class CheckCustomSerializable {

    public static void main(String[] argc) {
        Ticket ticket = new Ticket(1, "Dmitry", 12.25);
        byte[] byteArray;
        try {
            System.out.println("1. Before serialize = " + ticket);
            byteArray = CustomSerializable.doSerialize(ticket);
            System.out.println("2. After serialize = " + byteArray);
            Ticket ticketDeserialize = CustomSerializable.doDeserialize(byteArray);
            System.out.println("3. After deserialize = " + ticketDeserialize);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
