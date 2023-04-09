package controller;

import DAO.AppointmentDAOImpl;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Appointment;
import utils.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.function.BooleanSupplier;

/**
 * Controller class for UpdateApptScene.fxml.
 * <br><br>
 * Provides functionality for the update appointment screen.
 *
 * @author Miguel Guzman
 */
public class UpdateApptController implements Initializable {
	/**
	 * Appointment DAO.
	 */
	AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();
	/**
	 * The appointment ID text field.
	 */
	private Appointment selectedAppointment;
	/**
	 * The appointment ID text field.
	 */
	@FXML
	private TextField appointmentIDTextField;
	/**
	 * The username combo box.
	 */
	@FXML
	private ComboBox<String> userNameCB;
	/**
	 * The customer name combo box.
	 */
	@FXML
	private ComboBox<String> customerNameCB;
	/**
	 * The contact name combo box.
	 */
	@FXML
	private ComboBox<String> contactNameCB;
	/**
	 * The type text field.
	 */
	@FXML
	private TextField typeTextField;
	/**
	 * The title text field.
	 */
	@FXML
	private TextField titleTextField;
	/**
	 * The location text field.
	 */
	@FXML
	private TextField locationTextField;
	/**
	 * The start time combo box.
	 */
	@FXML
	private ComboBox<LocalTime> startTimeCB;
	/**
	 * The end time combo box.
	 */
	@FXML
	private ComboBox<LocalTime> endTimeCB;
	/**
	 * The start date picker.
	 */
	@FXML
	private DatePicker startDatePicker;
	/**
	 * The end date picker.
	 */
	@FXML
	private DatePicker endDatePicker;
	/**
	 * The description text area.
	 */
	@FXML
	private TextArea descriptionTextArea;
	/**
	 * Represents a Boolean supplier lambda function that checks if certain fields are empty.
	 * <p>
	 * LAMBDA #2
	 * <p>
	 * The BooleanSupplier interface represents of a supplier of boolean values.<br>
	 * The benefit is it provides a specific and descriptive type for the functional interface.<br>
	 * It in turn helps with readability and type checking.
	 * The lambda function returns {@code true} if any of the fields are empty, otherwise {@code false}.
	 */
	public final BooleanSupplier emptyField = () -> userNameCB.getValue() == null || customerNameCB.getValue() == null || contactNameCB.getValue() == null || typeTextField.getText().isEmpty() || titleTextField.getText().isEmpty() || locationTextField.getText().isEmpty() || startDatePicker.getValue() == null || startTimeCB.getValue() == null || endDatePicker.getValue() == null || endTimeCB.getValue() == null || descriptionTextArea.getText().isEmpty();
	/**
	 * The error label.
	 */
	@FXML
	private Label apptErrLabel;

	/**
	 * Initializes the controller class.
	 *
	 * @param url The URL
	 * @param resourceBundle The resource bundle
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		selectedAppointment = MainController.getSelectedAppointment();
		String contactName = ContactHelperFunctions.getContactName();
		appointmentIDTextField.setText(String.valueOf(selectedAppointment.getAppointmentID()));
		userNameCB.setItems(UserHelperFunctions.getUserNames());
		userNameCB.setValue(UserHelperFunctions.getUserName(selectedAppointment.getUserID()));
		customerNameCB.setItems(CustomerHelperFunctions.getCustomerNames());
		customerNameCB.setValue(CustomerHelperFunctions.getCustomerName(selectedAppointment.getCustomerID()));
		contactNameCB.setItems(ContactHelperFunctions.getContactNames());
		contactNameCB.setValue(contactName);
		titleTextField.setText(selectedAppointment.getTitle());
		typeTextField.setText(selectedAppointment.getType());
		locationTextField.setText(selectedAppointment.getLocation());
		startTimeCB.setItems(HelperFunctions.getBusinessHours());
		startTimeCB.setValue(selectedAppointment.updateGetStartTime().toLocalTime());
		startDatePicker.setValue(selectedAppointment.updateGetStartTime().toLocalDate());
		endTimeCB.setItems(HelperFunctions.getBusinessHours());
		endTimeCB.setValue(selectedAppointment.updateGetEndTime().toLocalTime());
		endDatePicker.setValue(selectedAppointment.updateGetEndTime().toLocalDate());
		descriptionTextArea.setText(selectedAppointment.getDescription());
	}

	/**
	 * Lambda function that updates the appointment.
	 * <p>
	 * LAMBDA #5
	 * <p>
	 * The Runnable interface represents a runnable task.<br>
	 * The presentAction lambda expression updates the appointment with the information provided in the form,
	 * and the emptyAction lambda expression displays an error message when form fields are empty.<br>
	 * Depending on the result of {@code emptyField.getAsBoolean()}, one of the two Runnable lambda expressions
	 * is executed with the {@code run()} method.
	 */
	@FXML
	public void updateApptBtnClick(ActionEvent actionEvent) {
		Runnable presentAction = () -> {
			ObservableList<Appointment> appointments;
			try {
				appointments = appointmentDAO.getAll();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			int customerIndex = customerNameCB.getSelectionModel().getSelectedIndex() + 1;
			if (ApptHelperFunctions.validDateTime(startDatePicker, startTimeCB, endDatePicker, endTimeCB) && ApptHelperFunctions.validEST(startTimeCB, endTimeCB, startDatePicker, endDatePicker) && !ApptHelperFunctions.doUpdateApptOverlap(appointments, customerIndex, startDatePicker, startTimeCB, endDatePicker, endTimeCB, selectedAppointment)) {
				setFieldsToUpdate();
				try {
					HelperFunctions.goToMain(actionEvent);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		};
		Runnable emptyAction = () -> ApptHelperFunctions.apptErr(apptErrLabel, userNameCB, customerNameCB, contactNameCB, typeTextField, titleTextField, locationTextField, startDatePicker, startTimeCB, endDatePicker, endTimeCB, descriptionTextArea);
		if (!emptyField.getAsBoolean()) {
			presentAction.run();
		} else {
			emptyAction.run();
		}
	}

	/**
	 * Sets the fields to update.
	 */
	public void setFieldsToUpdate() {
		Thread t = new Thread(() -> {
			selectedAppointment.setUserID(UserHelperFunctions.getUserID(userNameCB.getValue()));
			selectedAppointment.setCustomerID(customerNameCB.getSelectionModel().getSelectedIndex() + 1);
			selectedAppointment.setContactID(ContactHelperFunctions.getContactID(contactNameCB.getValue()));
			selectedAppointment.setTitle(titleTextField.getText());
			selectedAppointment.setType(typeTextField.getText());
			selectedAppointment.setLocation(locationTextField.getText());
			selectedAppointment.setStartTime(startDatePicker.getValue().atTime(startTimeCB.getValue()));
			selectedAppointment.setEndTime(endDatePicker.getValue().atTime(endTimeCB.getValue()));
			selectedAppointment.setDescription(descriptionTextArea.getText());
			try {
				appointmentDAO.update(selectedAppointment);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		});
		t.start();
	}

	/**
	 * Cancels the update and returns to the main screen.
	 *
	 * @param actionEvent The event that triggered the method.
	 * @throws IOException If the FXML file cannot be found.
	 */
	@FXML
	public void cancelReturnMainClick(ActionEvent actionEvent) throws IOException {
		HelperFunctions.goToMain(actionEvent);
	}
}