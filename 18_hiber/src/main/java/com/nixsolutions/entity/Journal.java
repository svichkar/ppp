package com.nixsolutions.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Journal implements Serializable {
	public Journal() {
	}
	
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false, length = 100)
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "book_id",referencedColumnName = "id")
	private BookInstance bookinstance;
	
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "reader_id",referencedColumnName = "id")
	private Reader reader;
	
	@Column(name = "start_date", nullable = false)
	private Date start_date;
	
	@Column(name = "end_date", nullable = false)
	private Date end_date;

	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public BookInstance getBook_instance() {
		return bookinstance;
	}
	public void setBook_instance(BookInstance book_instance) {
		this.bookinstance = book_instance;
	}
	public Reader getReader() {
		return reader;
	}
	public void setReader(Reader reader) {
		this.reader = reader;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
}
