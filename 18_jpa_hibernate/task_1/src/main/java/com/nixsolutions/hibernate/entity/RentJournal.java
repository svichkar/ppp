package com.nixsolutions.hibernate.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "rent_journal")
public class RentJournal implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TICKET_ID", unique = true, nullable = false)
	private Long rentId;
	@Column(name = "RENT_DATE", nullable = false)
	private Date rentDate;
	@Column(name = "RETURN_DATE")
	private Date returnDate;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOK_ID")
	private Book book;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "CLIENT_ID", referencedColumnName = "CLIENT_ID")
	private Client client;

	public Long getRentId() {
		return rentId;
	}

	public void setRentId(Long rentId) {
		this.rentId = rentId;
	}

	public Book getBookId() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Client getClient() {
		return client;
	}

	public void setClientId(Client client) {
		this.client = client;
	}

	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String toString() {
		return "rent with rentId: " + this.rentId + "; bookId: " + this.book + "; clientId: "
				+ this.client + "; rentDate: " + this.rentDate;

	}
}
