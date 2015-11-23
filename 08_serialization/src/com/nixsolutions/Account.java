package com.nixsolutions;

import java.io.Serializable;

/**
 * The Class Account.
 */
public class Account implements Serializable {

	/** The id of the account */
	public long id = 0L;

	/** The name of the account */
	public String name = "";

	/** The role of the account */
	public String role = "";

	/** The Constant serialVersionUID. */
	public static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new account.
	 *
	 * @param id
	 *            the id
	 * @param name
	 *            the name
	 * @param role
	 *            the role
	 */
	public Account(long id, String name, String role) {
		this.id = id;
		this.name = name;
		this.role = role;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", role=" + role + "]";
	}

}
