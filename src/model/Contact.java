package model;

/**
 * The Contact class.
 */
public class Contact {
	/**
	 * The Contact ID.
	 */
	private int contactID;
	/**
	 * The Contact name.
	 */
	private String name;
	/**
	 * The Contact email.
	 */
	private String email;

	/**
	 * Instantiates a new Contact.
	 *
	 * @param contactID   the contact id
	 * @param contactName the contact name
	 * @param email       the email
	 */
	public Contact(int contactID, String contactName, String email) {
		this.contactID = contactID;
		this.name = contactName;
		this.email = email;
	}

	/**
	 * Gets contact id.
	 *
	 * @return the contact id
	 */
	public int getContactID() {
		return contactID;
	}

	/**
	 * Sets contact id.
	 *
	 * @param contactID the contact id
	 */
	public void setContactID(int contactID) {
		this.contactID = contactID;
	}

	/**
	 * Gets contact name.
	 *
	 * @return the contact name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets contact name.
	 *
	 * @param name the contact name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets email.
	 *
	 * @param email the email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}
