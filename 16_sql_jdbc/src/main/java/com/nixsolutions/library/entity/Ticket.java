package com.nixsolutions.library.entity;

import java.sql.Date;

/**
 * Created by kozlovskij on 12/19/2015.
 */
public class Ticket {
    private int ticketId;
    private int bookId;
    private int clientId;
    private Date rentDate;
    private Date expiredDate;
    private Date returnDate;

    public Ticket(int ticketId, int bookId, int clientId, Date rentDate, Date expiredDate, Date returnDate) {
        this.ticketId = ticketId;
        this.bookId = bookId;
        this.clientId = clientId;
        this.rentDate = rentDate;
        this.expiredDate = expiredDate;
        this.returnDate = returnDate;
    }

    public Ticket(int bookId, int clientId, Date rentDate, Date expiredDate, Date returnDate) {
        this.bookId = bookId;
        this.clientId = clientId;
        this.rentDate = rentDate;
        this.expiredDate = expiredDate;
        this.returnDate = returnDate;
    }
}
