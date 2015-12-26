package com.nixsolutions.library.entity;

import java.sql.Date;

/**
 * Created by kozlovskij on 12/19/2015.
 */
public class Ticket {
    private Integer ticketId;
    private Integer bookId;
    private Integer clientId;
    private Date rentDate;
    private Date expiredDate;
    private Date returnDate;

    public Ticket(Integer ticketId, Integer bookId, Integer clientId, Date rentDate, Date expiredDate, Date returnDate) {
        this.ticketId = ticketId;
        this.bookId = bookId;
        this.clientId = clientId;
        this.rentDate = rentDate;
        this.expiredDate = expiredDate;
        this.returnDate = returnDate;
    }

    public Ticket(Integer bookId, Integer clientId, Date rentDate, Date expiredDate, Date returnDate) {
        this.bookId = bookId;
        this.clientId = clientId;
        this.rentDate = rentDate;
        this.expiredDate = expiredDate;
        this.returnDate = returnDate;
    }

    public int getTicketId() {
        return ticketId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getClientId() {
        return clientId;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
