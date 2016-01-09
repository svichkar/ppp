package com.nixsolutions.library.bean;

import com.nixsolutions.library.entity.Book;
import com.nixsolutions.library.entity.Client;
import com.nixsolutions.library.entity.Ticket;

/**
 * Created by Serko on 09.01.2016.
 */
public class OverdueBook {
    private Ticket ticket;
    private Client client;
    private Book book;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
