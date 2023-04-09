package model;

/**
 * The Country class.
 */
public class Country {
	/**
	 * The Country ID.
	 */
	private int countryID;
	/**
	 * The Country name.
	 */
	private final String countryName;

	/**
	 * Instantiates a new Country.
	 *
	 * @param countryID   the country id
	 * @param countryName the country name
	 */
	public Country(int countryID, String countryName) {
		this.countryID = countryID;
		this.countryName = countryName;
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

	/**
	 * Gets country name.
	 *
	 * @return the country name
	 */
	public String getCountryName() {
		return countryName;
	}
}
