package com.nixsolutions.entity;

import java.util.Date;

public class RentJournal {
	private Long rentId;
	private Long bookId;
	private Long clientId;
	private Date rentDate;
	private Date returnDate;

	public Long getRentId() {
		return rentId;
	}

	public void setRentId(Long rentId) {
		this.rentId = rentId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
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
