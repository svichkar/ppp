package com.nixsolutions.entity;

import java.util.Date;

public class RentJournal {
	private Integer rentId;
	private Integer bookId;
	private Integer clientId;
	private Date rentDate;
	private Date returnDate;

	/*
	 * public RentJournal(int rentId, int bookId, int clientId, Date rentDate) {
	 * super(); this.rentId = rentId; this.bookId = bookId; this.clientId =
	 * clientId; this.rentDate = rentDate; }
	 */
	public Integer getRentId() {
		return rentId;
	}

	public void setRentId(int rentId) {
		this.rentId = rentId;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
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
		return "rent with rentId: " + this.rentId + "; bookId: " + this.bookId + "; clientId: " + this.clientId + "; rentDate: " + this.rentDate;

	}
}
