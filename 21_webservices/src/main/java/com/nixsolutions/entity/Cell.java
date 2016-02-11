package com.nixsolutions.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

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
public class Cell implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CELL_ID", unique=true, nullable=false)
	private Long cellId;
	@NotNull
	@Size(min = 3, max = 20)
	@Column (name = "NAME", nullable=false)
	private String name;
/*
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "CELL_ID", referencedColumnName = "CELL_ID")
	private List<Book> books;
	*/
	public Cell(){};
	
	public Cell(String name) {
		super();
		this.name = name;
	}

	public Long getCellId() {
		return cellId;
	}

	public void setCellId(Long cellId) {
		this.cellId = cellId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return "Cell with cellId: " + this.cellId + "; name: " + this.name;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		//result = prime * result + ((books == null) ? 0 : books.hashCode());
		result = prime * result + ((cellId == null) ? 0 : cellId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
	/*	if (books == null) {
			if (other.books != null)
				return false;
		} else if (!books.equals(other.books))
			return false;*/
		if (cellId == null) {
			if (other.cellId != null)
				return false;
		} else if (!cellId.equals(other.cellId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
