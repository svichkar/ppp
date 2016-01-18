package com.nixsolutions.entity;

public class Client {
	private Long clientId;
	private String firstName;
	private String secondName;
	private String name;
	private String phone;
	private String email;

public Client(){
	name = firstName + " " + secondName;
}
	public String getName(){
		return name;
	}
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
		return this.firstName + " " + this.secondName;

	}

}
