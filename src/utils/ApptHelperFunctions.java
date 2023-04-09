package utils;

import DAO.JDBC;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Helper functions for appointment controllers.
 * <br><br>
 * Includes a lambda function.
 * <br><br>
 *
 * @author Miguel Guzman
 */
public class ApptHelperFunctions {
	/**
	 * Displays what fields are missing when adding or updating an appointment.
	 * <p>
	 *
	 * @param apptErrLabel        The error label.
	 * @param userNameCB          The username combo box.
	 * @param customerNameCB      The customer name combo box.
	 * @param contactNameCB       The contact name combo box.
	 * @param typeTextField       The type text field.
	 * @param titleTextField      The title text field.
	 * @param locationTextField   The location text field.
	 * @param startDatePicker     The start date picker.
	 * @param startTimeCB         The start time combo box.
	 * @param endDatePicker       The end date picker.
	 * @param endTimeCB           The end time combo box.
	 * @param descriptionTextArea The description text area.
	 */
	public static void apptErr(Label apptErrLabel,
	                           ComboBox<String> userNameCB,
	                           ComboBox<String> customerNameCB,
	                           ComboBox<String> contactNameCB,
	                           TextField typeTextField,
	                           TextField titleTextField,
	                           TextField locationTextField,
	                           DatePicker startDatePicker,
	                           ComboBox<LocalTime> startTimeCB,
	                           DatePicker endDatePicker,
	                           ComboBox<LocalTime> endTimeCB,
	                           TextArea descriptionTextArea) {
		apptErrLabel.setText("");
		if (userNameCB.getValue() == null) {
			apptErrLabel.setText("Please select a user\n");
		}
		if (customerNameCB.getValue() == null) {
			apptErrLabel.setText(apptErrLabel.getText() + "Please select a customer\n");
		}
		if (contactNameCB.getValue() == null) {
			apptErrLabel.setText(apptErrLabel.getText() + "Please select a contact\n");
		}
		if (typeTextField.getText().isEmpty()) {
			apptErrLabel.setText(apptErrLabel.getText() + "Please enter a type\n");
		}
		if (titleTextField.getText().isEmpty()) {
			apptErrLabel.setText(apptErrLabel.getText() + "Please enter a title\n");
		}
		if (locationTextField.getText().isEmpty()) {
			apptErrLabel.setText(apptErrLabel.getText() + "Please enter a location\n");
		}
		if (startDatePicker.getValue() == null) {
			apptErrLabel.setText(apptErrLabel.getText() + "Please select a start date\n");
		}
		if (startTimeCB.getValue() == null) {
			apptErrLabel.setText(apptErrLabel.getText() + "Please select a start time\n");
		}
		if (endDatePicker.getValue() == null) {
			apptErrLabel.setText(apptErrLabel.getText() + "Please select a end date\n");
		}
		if (endTimeCB.getValue() == null) {
			apptErrLabel.setText(apptErrLabel.getText() + "Please select a end time\n");
		}
		if (descriptionTextArea.getText().isEmpty()) {
			apptErrLabel.setText(apptErrLabel.getText() + "Please enter a description\n");
		}
	}

	/**
	 * Checks if the appointment times are within business hours.
	 *
	 * @param startTimeCB     The start time combo box.
	 * @param endTimeCB       The end time combo box.
	 * @param startDatePicker The start date picker.
	 * @param endDatePicker   The end date picker.
	 * @return True if the appointment times are within business hours.
	 */
	public static boolean validEST(ComboBox<LocalTime> startTimeCB, ComboBox<LocalTime> endTimeCB, DatePicker startDatePicker, DatePicker endDatePicker) {
		ZoneId userZone = ZoneId.systemDefault();
		ZoneId EST = ZoneId.of("America/New_York");
		LocalTime startTime = startTimeCB.getValue();
		LocalTime endTime = endTimeCB.getValue();
		ZonedDateTime userStart = LocalDateTime.of(startDatePicker.getValue(), startTime).atZone(userZone);
		ZonedDateTime ESTStart = userStart.withZoneSameInstant(EST);
		ZonedDateTime userEnd = LocalDateTime.of(endDatePicker.getValue(), endTime).atZone(userZone);
		ZonedDateTime ESTEnd = userEnd.withZoneSameInstant(EST);
		if (ESTStart.toLocalTime().isBefore(LocalTime.of(8, 0)) || ESTEnd.toLocalTime().isAfter(LocalTime.of(22, 0))) {
			ErrMsg.errorAlert("Appointments must be scheduled between 8am and 10pm EST", "Invalid Appointment Time");
			return false;
		}
		return true;
	}

	/**
	 * Checks if the dates or times are being scheduled in the past or if end time/date is before start time/date.
	 *
	 * @param startDatePicker The start date picker.
	 * @param startTimeCB     The start time combo box.
	 * @param endDatePicker   The end date picker.
	 * @param endTimeCB       The end time combo box.
	 * @return True if the dates or times do not overlap with another appointment.
	 */
	public static boolean validDateTime(DatePicker startDatePicker, ComboBox<LocalTime> startTimeCB, DatePicker endDatePicker, ComboBox<LocalTime> endTimeCB) {
		LocalDateTime startDateTime = HelperFunctions.startEndFormatter(startDatePicker.getValue().toString() + " " + startTimeCB.getValue().toString());
		LocalDateTime endDateTime = HelperFunctions.startEndFormatter(endDatePicker.getValue().toString() + " " + endTimeCB.getValue().toString());
		if (startDatePicker.getValue().getDayOfWeek().getValue() > 5 || endDatePicker.getValue().getDayOfWeek().getValue() > 5) {
			ErrMsg.errorAlert("Please select a day between Monday and Friday", "Invalid Date");
			return false;
		}
		if (startDateTime.isBefore(LocalDateTime.now())) {
			ErrMsg.errorAlert("Cannot schedule an appointment in the past", "Invalid Date");
			return false;
		}
		if (startDatePicker.getValue().isAfter(endDatePicker.getValue()) || startTimeCB.getValue().isAfter(endTimeCB.getValue()) || startDatePicker.getValue().equals(endDatePicker.getValue()) && startTimeCB.getValue().isAfter(endTimeCB.getValue())) {
			if (startDatePicker.getValue().isBefore(endDatePicker.getValue())) return true;
			ErrMsg.errorAlert("Please select a start date/time before the end date/time", "Invalid Date or Time");
			return false;
		}
		if (startDateTime.isAfter(endDateTime) || startDateTime.equals(endDateTime)) {
			ErrMsg.errorAlert("Please select a start date/time before the end date/time", "Invalid Date/Time");
			return false;
		}
		return true;
	}

	/**
	 * Checks if the appointment times overlap with another appointment when adding.
	 *
	 * @param appointments    The list of appointments.
	 * @param customerIndex   The customer index is the customer ID.
	 * @param startDatePicker The start date picker.
	 * @param startTimeCB     The start time combo box.
	 * @param endDatePicker   The end date picker.
	 * @param endTimeCB       The end time combo box.
	 * @return True if the appointment times do not overlap with another appointment.
	 */
	public static boolean doAddApptOverlap(ObservableList<Appointment> appointments, int customerIndex, DatePicker startDatePicker, ComboBox<LocalTime> startTimeCB, DatePicker endDatePicker, ComboBox<LocalTime> endTimeCB) {
		boolean overlap = false;
		for (Appointment appointment : appointments) {
			if (appointment.getCustomerID() == customerIndex) {
				LocalDateTime start = HelperFunctions.startEndFormatter(startDatePicker.getValue().toString() + " " + startTimeCB.getValue().toString());
				LocalDateTime end = HelperFunctions.startEndFormatter(endDatePicker.getValue().toString() + " " + endTimeCB.getValue().toString());
				if (appointment.updateGetStartTime().isBefore(start) && appointment.updateGetEndTime().isAfter(start) ||
						appointment.updateGetStartTime().isBefore(end) && appointment.updateGetEndTime().isAfter(end) ||
						appointment.updateGetStartTime().isAfter(start) && appointment.updateGetEndTime().isBefore(end)) {
					overlap = true;
					ErrMsg.errorAlert("This appointment overlaps with another appointment for this customer\n\n"
									+ "Appointment ID: " + appointment.getAppointmentID() + "\n"
									+ "Appointment Start: " + appointment.updateGetStartTime() + "\n"
									+ "Appointment End: " + appointment.updateGetEndTime(),
							"Appointment Overlap");
					break;
				}
			}
		}
		return overlap;
	}

	/**
	 * Checks for overlapping appointments when updating an appointment
	 * <p>
	 * LAMBDA #4
	 * <p>
	 * This lambda function is used with the anyMatch() method in the Stream API to check if there are any
	 * overlapping appointments when updating an appointment.<br>
	 * It takes an Appointment object and returns a boolean value indicating if the appointment overlaps with
	 * another appointment for the same customer.
	 *
	 * @param appointments        ObservableList of all appointments
	 * @param customerIndex       Customer ID of the customer the appointment is for
	 * @param startDatePicker     Start date of the appointment
	 * @param startTimeCB         Start time of the appointment
	 * @param endDatePicker       End date of the appointment
	 * @param endTimeCB           End time of the appointment
	 * @param selectedAppointment Appointment object of the appointment being updated
	 * @return boolean value indicating if the appointment overlaps with another appointment for the same customer
	 */
	public static boolean doUpdateApptOverlap(ObservableList<Appointment> appointments, int customerIndex, DatePicker startDatePicker, ComboBox<LocalTime> startTimeCB, DatePicker endDatePicker, ComboBox<LocalTime> endTimeCB, Appointment selectedAppointment) {
		boolean overlap = appointments.stream()
				.filter(appointment -> appointment.getCustomerID() == customerIndex && appointment.getAppointmentID() != selectedAppointment.getAppointmentID())
				.anyMatch(appointment -> {
					LocalDateTime start = HelperFunctions.startEndFormatter(startDatePicker.getValue().toString() + " " + startTimeCB.getValue().toString());
					LocalDateTime end = HelperFunctions.startEndFormatter(endDatePicker.getValue().toString() + " " + endTimeCB.getValue().toString());
					return (appointment.updateGetStartTime().isBefore(start) && appointment.updateGetEndTime().isAfter(start) ||
							appointment.updateGetStartTime().isBefore(end) && appointment.updateGetEndTime().isAfter(end) ||
							appointment.updateGetStartTime().isAfter(start) && appointment.updateGetEndTime().isBefore(end));
				});
		if (overlap) {
			Appointment overlappingAppointment = appointments.stream()
					.filter(appointment -> appointment.getCustomerID() == customerIndex && appointment.getAppointmentID() != selectedAppointment.getAppointmentID())
					.findFirst()
					.orElse(null);
			if (overlappingAppointment != null) {
				ErrMsg.errorAlert("This appointment overlaps with another appointment for this customer\n\n"
								+ "Appointment ID: " + overlappingAppointment.getAppointmentID() + "\n"
								+ "Appointment Start: " + overlappingAppointment.updateGetStartTime() + "\n"
								+ "Appointment End: " + overlappingAppointment.updateGetEndTime(),
						"Appointment Overlap");
			}
		}
		return overlap;
	}

	/**
	 * Returns the next appointment ID in order to add a new appointment to the database.
	 *
	 * @return The next appointment ID.
	 * @throws SQLException If there is an error with the SQL statement.
	 */
	public static int getNextAppointmentID() throws SQLException {
		int nextID;
		String updateKey = "SET information_schema_stats_expiry = 0";
		String sqlStatement = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'client_schedule' AND TABLE_NAME = 'appointments'";
		try (Statement statement = JDBC.connection.createStatement(); PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
			statement.execute(updateKey);
			try (ResultSet result = ps.executeQuery()) {
				if (result.next()) {
					nextID = result.getInt("AUTO_INCREMENT");
					return nextID;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * Creates an alert that displays the appointment ID and the appointment type.
	 *
	 * @param appointmentID   The appointment ID.
	 * @param appointmentType The appointment type.
	 */
	public static void appointmentDeleteAlert(int appointmentID, String appointmentType) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Appointment Alert");
		alert.setHeaderText("Appointment Alert");
		alert.setContentText("Appointment ID: " + appointmentID + " Appointment type: " + appointmentType + " has been deleted.");
		alert.showAndWait();
	}
}
