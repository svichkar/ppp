package com.nixsolutions.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Book implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BOOK_ID", unique=true, nullable=false)
	private Long bookId;
	@NotNull
	@Size(min = 3)
	@Column(name = "NAME", nullable=false)
	private String name;
	@NotNull
	@Column(name = "COUNT", nullable=false)
	private Integer count;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "CELL_ID", referencedColumnName = "CELL_ID")
	private Cell cell;
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "CATEGORY_ID", referencedColumnName = "CATEGORY_ID")
	private Category category;

	@ManyToMany
	@JoinTable(name = "author_book",
				joinColumns = {@JoinColumn(name = "BOOK_ID")},
				inverseJoinColumns = {@JoinColumn(name = "AUTHOR_ID")})
	private Set<Author> authors;

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public String toString() {
		return "book with bookId: " + this.bookId + "; name: " + this.name;
	}
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void increaseCount() {
		this.count++;
	}

	public void decreaseCount() {
		if (count != null) {
			this.count--;
		}
	}

}
