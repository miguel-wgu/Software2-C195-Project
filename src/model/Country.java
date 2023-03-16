package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Country {
	// Country model class for the appointments table
	private int countryID;
	private String countryName;
	private LocalDateTime createDate;
	private String createdBy;
	private Timestamp lastUpdate;
	private String lastUpdateBy;

	/**
	 * Instantiates a new Country.
	 *
	 * @param countryID     the country id
	 * @param countryName   the country name
//	 * @param createDate    the create date
//	 * @param createdBy     the created by
//	 * @param lastUpdate    the last update
//	 * @param lastUpdateBy  the last update by
	 */
//	public Country(int countryID, String countryName, LocalDateTime createDate, String createdBy, Timestamp lastUpdate, String lastUpdateBy) {
//		this.countryID = countryID;
//		this.countryName = countryName;
//		this.createDate = createDate;
//		this.createdBy = createdBy;
//		this.lastUpdate = lastUpdate;
//		this.lastUpdateBy = lastUpdateBy;
//	}

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

	/**
	 * Sets country name.
	 *
	 * @param countryName the country name
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * Gets create date.
	 *
	 * @return the create date
	 */
	public LocalDateTime getCreateDate() {
		return createDate;
	}

	/**
	 * Sets create date.
	 *
	 * @param createDate the create date
	 */
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	/**
	 * Gets created by.
	 *
	 * @return the created by
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets created by.
	 *
	 * @param createdBy the created by
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets last update.
	 *
	 * @return the last update
	 */
	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * Sets last update.
	 *
	 * @param lastUpdate the last update
	 */
	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	/**
	 * Gets last update by.
	 *
	 * @return the last update by
	 */
	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	/**
	 * Sets last update by.
	 *
	 * @param lastUpdateBy the last update by
	 */
	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}
}
