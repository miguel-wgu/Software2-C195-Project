package model;

/**
 * The Contact class.
 */
public class Customer {
	/**
	 * The Customer ID.
	 */
	int customerID;
	/**
	 * The Customer Name.
	 */
	String customerName;
	/**
	 * The Address.
	 */
	String address;
	/**
	 * The Postal Code.
	 */
	String postalCode;
	/**
	 * The Phone.
	 */
	String phone;
	/**
	 * The Division ID.
	 */
	int divisionID;
	/**
	 * The Division Name.
	 */
	String divisionName;

	/**
	 * Instantiates a new Customer.
	 *
	 * @param customerID   the customer id
	 * @param customerName the customer name
	 * @param address      the address
	 * @param postalCode   the postal code
	 * @param phone        the phone
	 * @param divisionID   the division id
	 * @param divisionName the division name
	 */
	public Customer(int customerID, String customerName, String address, String postalCode, String phone, int divisionID, String divisionName) {
		this.customerID = customerID;
		this.customerName = customerName;
		this.address = address;
		this.postalCode = postalCode;
		this.phone = phone;
		this.divisionID = divisionID;
		this.divisionName = divisionName;
	}

	/**
	 * Gets customer id.
	 *
	 * @return the customer id
	 */
	public int getCustomerID() {
		return customerID;
	}

	/**
	 * Sets customer id.
	 *
	 * @param customerID the customer id
	 */
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	/**
	 * Gets customer name.
	 *
	 * @return the customer name
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * Sets customer name.
	 *
	 * @param customerName the customer name
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * Gets address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets address.
	 *
	 * @param address the address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets postal code.
	 *
	 * @return the postal code
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * Sets postal code.
	 *
	 * @param postalCode the postal code
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * Gets phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets phone.
	 *
	 * @param phone the phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Gets division id.
	 *
	 * @return the division id
	 */
	public int getDivisionID() {
		return divisionID;
	}

	/**
	 * Sets division id.
	 *
	 * @param divisionID the division id
	 */
	public void setDivisionID(int divisionID) {
		this.divisionID = divisionID;
	}

	/**
	 * Gets division name.
	 *
	 * @return the division name
	 */
	public String getDivisionName() {
		return divisionName;
	}

	/**
	 * Sets division name.
	 *
	 * @param divisionName the division name
	 */
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
}