package model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Appointments {
	// Create an appointment class that creates an appointment object
	private int appointmentID;
	private String title;
	private String description;
	private String location;
	private String type;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
//	private LocalDateTime createDate;
//	private String createdBy;
	//	private LocalDate lastUpdate;
//	private String lastUpdateBy;
	public int customerID;
	public int userID;
	public int contactID;

	public Appointments(int appointmentID, String title, String description, String location, String type, LocalDateTime startTime, LocalDateTime endTime, int customerID, int userID, int contactID) {
				this.appointmentID = appointmentID;
		this.title = title;
		this.description = description;
		this.location = location;
		this.type = type;
		this.startTime = startTime;
		this.endTime = endTime;
		this.customerID = customerID;
		this.userID = userID;
		this.contactID = contactID;
	}

	/**
	 * Gets appointment id.
	 *
	 * @return the appointment id
	 */
	public int getAppointmentID() {
		return appointmentID;
	}

	/**
	 * Sets appointment id.
	 *
	 * @param appointmentID the appointment id
	 */
	public void setAppointmentID(int appointmentID) {
		this.appointmentID = appointmentID;
	}

	/**
	 * Gets title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets title.
	 *
	 * @param title the title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets description.
	 *
	 * @param description the description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets location.
	 *
	 * @return the location
	 */

	public String getLocation() {
		return location;
	}

	/**
	 * Sets location.
	 *
	 * @param location the location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Gets type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets type.
	 *
	 * @param type the type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets start time.
	 *
	 * @return the start time
	 */
	public LocalDateTime getStartTime() {
		return startTime;
	}

	/**
	 * Sets start time.
	 *
	 * @param startTime the start time
	 */
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	/**
	 * Gets end time.
	 *
	 * @return the end time
	 */
	public LocalDateTime getEndTime() {
		return endTime;
	}

	/**
	 * Sets end time.
	 *
	 * @param endTime the end time
	 */
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

//	/**
//	 * Gets create date.
//	 *
//	 * @return the create date
//	 */
//	public LocalDateTime getCreateDate() {
//		return createDate;
//	}
//
//	/**
//	 * Sets create date.
//	 *
//	 * @param createDate the create date
//	 */
//	public void setCreateDate(LocalDateTime createDate) {
//		this.createDate = createDate;
//	}
//
//	/**
//	 * Gets created by.
//	 *
//	 * @return the created by
//	 */
//	public String getCreatedBy() {
//		return createdBy;
//	}
//
//	/**
//	 * Sets created by.
//	 *
//	 * @param createdBy the created by
//	 */
//	public void setCreatedBy(String createdBy) {
//		this.createdBy = createdBy;
//	}

//	/**
//	 * Gets last update.
//	 *
//	 * @return the last update
//	 */
//	public LocalDate getLastUpdate() {
//		return lastUpdate;
//	}
//
//	/**
//	 * Sets last update.
//	 *
//	 * @param lastUpdate the last update
//	 */
//	public void setLastUpdate(LocalDate lastUpdate) {
//		this.lastUpdate = lastUpdate;
//	}
//
//	/**
//	 * Gets last update by.
//	 *
//	 * @return the last update by
//	 */
//	public String getLastUpdateBy() {
//		return lastUpdateBy;
//	}
//
//	/**
//	 * Sets last update by.
//	 *
//	 * @param lastUpdateBy the last update by
//	 */
//	public void setLastUpdateBy(String lastUpdateBy) {
//		this.lastUpdateBy = lastUpdateBy;
//	}

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
	 * Gets user id.
	 *
	 * @return the user id
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * Sets user id.
	 *
	 * @param userID the user id
	 */
	public void setUserID(int userID) {
		this.userID = userID;
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


}
