package com.nixsolutions;

/**
 * Created by sobolenko on 2/5/2016.
 */
public class Main {
    public static void main(String[] args) {
        Account user = new Account((long) 541898,"user1","admin");
        Serialize serialize = new Serialize();
        System.out.println(user.getName());
        //serialize.serializing(user);
        System.out.println(serialize.deserializing(user).getId());
    }
}
