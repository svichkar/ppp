package com.nixsolutions.entity;

public class Client {
	private int clientId;
	private String firstName;
	private String secondName;
	private String phone;
	private String email;

	public Client(int clientId, String firstName, String secondName) {
		super();
		this.clientId = clientId;
		this.firstName = firstName;
		this.secondName = secondName;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
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

}
