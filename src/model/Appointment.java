package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Appointment class.
 */
public class Appointment {
	/**
	 * The Customer ID.
	 */
	private int customerID;
	/**
	 * The User ID.
	 */
	private int userID;
	/**
	 * The Contact ID.
	 */
	private int contactID;
	/**
	 * The Appointment ID.
	 */
	private int appointmentID;
	/**
	 * The Title.
	 */
	private String title;
	/**
	 * The Description.
	 */
	private String description;
	/**
	 * The Location.
	 */
	private String location;
	/**
	 * The Type.
	 */
	private String type;
	/**
	 * The Start time.
	 */
	private LocalDateTime startTime;
	/**
	 * The End time.
	 */
	private LocalDateTime endTime;

	/**
	 * Instantiates a new Appointment.
	 *
	 * @param appointmentID the appointment id
	 * @param title         the title
	 * @param description   the description
	 * @param location      the location
	 * @param type          the type
	 * @param startTime     the start
	 * @param endTime       the end
	 * @param customerID    the customer id
	 * @param userID        the user id
	 * @param contactID     the contact id
	 */
	public Appointment(int appointmentID, String title, String description, String location, String type, LocalDateTime startTime, LocalDateTime endTime, int customerID, int userID, int contactID) {
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
	 * Instantiates a new Appointment and is used for Reporting purposes.
	 *
	 * @param appointmentID the appointment id
	 * @param title         the title
	 * @param description   the description
	 * @param type          the type
	 * @param start         the start
	 * @param end           the end
	 * @param customerId    the customer id
	 */
	public Appointment(int appointmentID, String title, String description, String type, LocalDateTime start, LocalDateTime end, int customerId) {
		this.appointmentID = appointmentID;
		this.title = title;
		this.description = description;
		this.type = type;
		this.startTime = start;
		this.endTime = end;
		this.customerID = customerId;
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
	 * Returns a formatted string of the start time and date.
	 *
	 * @return the start time
	 */
	public String getStartTime() {
		LocalDateTime dateTime = this.startTime;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy 'at' h:mm a");
		return dateTime.format(formatter);
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
	 * Returns the start time
	 *
	 * @return the start time
	 */
	public LocalDateTime updateGetStartTime() {
		return this.startTime;
	}

	/**
	 * Returns a formatted string of the end time and date.
	 *
	 * @return the end time
	 */
	public String getEndTime() {
		LocalDateTime dateTime = this.endTime;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy 'at' h:mm a");
		return dateTime.format(formatter);
	}

	/**
	 * Sets end time.
	 *
	 * @param endTime the end time
	 */
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	/**
	 * Returns the end time
	 *
	 * @return the end time
	 */
	public LocalDateTime updateGetEndTime() {
		return this.endTime;
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
