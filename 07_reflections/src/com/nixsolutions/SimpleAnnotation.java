package com.nixsolutions;


/**
 * this class was created for testing Annotation Task
 * The Class SimpleAnnotation.
 */
public class SimpleAnnotation {

	/** The first name person. */
	@ToString
	public String firstName;
	
	/** The second name person. */
	public String secondName;

	/**
	 * Instantiates a new simple annotation.
	 *
	 * @param firstName the first name
	 * @param secondName the second name
	 */
	public SimpleAnnotation(String firstName, String secondName) {
		this.firstName = firstName;
		this.secondName = secondName;
	}

	/**
	 * Instantiates a new simple annotation.
	 */
	public SimpleAnnotation() {

	}

	/**
	 * Sets the first name.
	 *
	 * @param fName the new first name
	 */
	public void setFirstName(String fName) {
		firstName = fName;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the second name.
	 *
	 * @param sName the new second name
	 */
	public void setSecondName(String sName) {
		secondName = sName;
	}

	/**
	 * Gets the second name.
	 *
	 * @return the second name
	 */
	public String getSecondName() {
		return secondName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Simple annotation";
	}

}
