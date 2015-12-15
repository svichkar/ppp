package com.nixsolutions.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BookInstance implements Serializable{
	public BookInstance() {
	}
	@Id
	@GeneratedValue
	@Column(name ="id",nullable=false,length=10)
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "book_id",referencedColumnName = "id")
	private Book book;
	@Column(name="inventory_number",nullable=false,length=10)
	private Integer inventory_number;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public Integer getInventory_number() {
		return inventory_number;
	}
	public void setInventory_number(Integer inventory_number) {
		this.inventory_number = inventory_number;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
}
