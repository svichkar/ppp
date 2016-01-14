package com.nixsolutions.hibernate.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Category implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CATEGORY_ID", unique=true, nullable=false)
	private Long categoryId;
	@NotNull
	@Size(min = 3, max = 20)
	@Column(name = "NAME", nullable=false)
	private String name;
	
	@OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "CATEGORY_ID", referencedColumnName = "CATEGORY_ID")
	private List<Book> books;

	public Category(String name) {
		super();
		this.name = name;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "category with Id: " + this.categoryId + "; name: " + this.name;

	}
}
