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

import org.hibernate.validator.constraints.Email;

@Entity
public class Client implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLIENT_ID", unique = true, nullable = false)
	private Long clientId;
	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;
	@Column(name = "LAST_NAME", nullable = false)
	private String secondName;
	@Column(name = "PHONE")
	private String phone;
	@Email
	@Column(name = "EMAIL")
	private String email;

	@OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "CLIENT_ID", referencedColumnName = "CLIENT_ID")
	private List<RentJournal> rents;

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String toString() {
		return "client with clientId: " + this.clientId + "; firstName: " + this.firstName
				+ "; lastName: " + this.secondName;

	}

}
