package model;

/**
 * The First level division class.
 */
public class FirstLevelDivision {
	/**
	 * The Division id.
	 */
	int divisionID;
	/**
	 * The Division name.
	 */
	String divisionName;
	/**
	 * The Country id.
	 */
	int countryID;

	/**
	 * Instantiates a new First level division.
	 *
	 * @param divisionID   the division id
	 * @param divisionName the division name
	 * @param countryID    the country id
	 */
	public FirstLevelDivision(int divisionID, String divisionName, int countryID) {
		this.divisionID = divisionID;
		this.divisionName = divisionName;
		this.countryID = countryID;
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

	/**
	 * Gets country id.
	 *
	 * @return the country id
	 */
	public int getCountryID() {
		return countryID;
	}

	/**
	 * Sets country id.
	 *
	 * @param countryID the country id
	 */
	public void setCountryID(int countryID) {
		this.countryID = countryID;
	}
}
