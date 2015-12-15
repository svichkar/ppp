package com.nixsolutions.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Book implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false, length = 10)
	private Integer id;
	@Column(name = "name", length = 100)
	private String name;
	@Column(name = "author", length = 50)
	private String author;
	@Column(name = "publisher", length = 50)
	private String publisher;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id",referencedColumnName="id")
	private Category category;

	public Book() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
