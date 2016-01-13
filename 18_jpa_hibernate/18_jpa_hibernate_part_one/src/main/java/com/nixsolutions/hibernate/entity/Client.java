package com.nixsolutions.hibernate.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by kozlovskij on 1/13/2016.
 */
@Entity
public class Client implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLIENT_ID")
    private Long clientId;

    @Column(name = "FIRST_NAME", nullable = false)
    private String clientFirstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String clientLastName;

    @Column(name = "PHONE")
    private String clientPhone;

    @Column(name = "EMAIL")
    private String clientEmail;

    @Transient
    public String clientFullName () {
        return clientFirstName + " " + clientLastName;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

}
