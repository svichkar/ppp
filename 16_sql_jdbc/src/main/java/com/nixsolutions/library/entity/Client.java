package com.nixsolutions.library.entity;

/**
 * Created by kozlovskij on 12/19/2015.
 */
public class Client {
    private int clientId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    public Client(int clientId, String firstName, String lastName, String phone, String email) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public Client(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }
}
