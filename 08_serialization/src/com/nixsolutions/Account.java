package com.nixsolutions;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The Class Account.
 */
public class Account implements Externalizable {

	/**  The id of the account. */
	private long id;

	/**  The name of the account. */
	private String name;

	/**  The role of the account. */
	private String role;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new account.
	 */
	public Account() {

	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name of the account
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role of the account
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id of the account
	 */
	public void setID(Long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see java.io.Externalizable#readExternal(java.io.ObjectInput)
	 */
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		String[] fields = ((String) in.readObject()).split(";;");
		id = Long.parseLong(fields[0]);
		name = fields[1];
		role = fields[2];
	}

	/* (non-Javadoc)
	 * @see java.io.Externalizable#writeExternal(java.io.ObjectOutput)
	 */
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject((id + ";;" + name + ";;" + role));
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ID: ");
		sb.append(id);
		sb.append("  name: ");
		sb.append(name);
		sb.append("  role: ");
		sb.append(role);

		return sb.toString();
	}

}
