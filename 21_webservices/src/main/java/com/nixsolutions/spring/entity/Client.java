package com.nixsolutions.spring.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by kozlovskij on 1/13/2016.
 */
@Entity
public class Client implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "first_name", nullable = false)
    private String clientFirstName;

    @Column(name = "last_name", nullable = false)
    private String clientLastName;

    @Column(name = "phone")
    private String clientPhone;

    @Column(name = "email")
    private String clientEmail;

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    private List<Ticket> tickets;

    @Transient
    public String clientFullName () {
        return clientFirstName + " " + clientLastName;
    }

    public Client() {
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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (clientId != null ? !clientId.equals(client.clientId) : client.clientId != null) return false;
        if (clientFirstName != null ? !clientFirstName.equals(client.clientFirstName) : client.clientFirstName != null)
            return false;
        if (clientLastName != null ? !clientLastName.equals(client.clientLastName) : client.clientLastName != null)
            return false;
        if (clientPhone != null ? !clientPhone.equals(client.clientPhone) : client.clientPhone != null) return false;
        if (clientEmail != null ? !clientEmail.equals(client.clientEmail) : client.clientEmail != null) return false;
        return tickets != null ? tickets.equals(client.tickets) : client.tickets == null;

    }

    @Override
    public int hashCode() {
        int result = clientId != null ? clientId.hashCode() : 0;
        result = 31 * result + (clientFirstName != null ? clientFirstName.hashCode() : 0);
        result = 31 * result + (clientLastName != null ? clientLastName.hashCode() : 0);
        result = 31 * result + (clientPhone != null ? clientPhone.hashCode() : 0);
        result = 31 * result + (clientEmail != null ? clientEmail.hashCode() : 0);
        result = 31 * result + (tickets != null ? tickets.hashCode() : 0);
        return result;
    }
}
