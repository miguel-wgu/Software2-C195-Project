package controller;

import DAO.AppointmentDAOImpl;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Appointment;
import utils.ApptHelperFunctions;
import utils.HelperFunctions;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/**
 * Controller class for AddApptScene.fxml.
 * <br><br>
 * Provides functionality for the add appointment screen.
 *
 * @author Miguel Guzman
 */
public class AddApptController implements Initializable {
	/**
	 * Appointment dao.
	 */
	AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();
	/**
	 * The appointment ID text field.
	 */
	@FXML
	private TextField apptIDTextField;
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
	 * The missing field label.
	 */
	@FXML
	private Label apptErrLabel;
	private final Control[] fields = {userNameCB, customerNameCB, contactNameCB, typeTextField, titleTextField, locationTextField, startDatePicker, startTimeCB, endDatePicker, endTimeCB, descriptionTextArea};
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
	public final BooleanSupplier emptyField = () ->
			userNameCB.getValue() == null ||
					customerNameCB.getValue() == null ||
					contactNameCB.getValue() == null ||
					typeTextField.getText().isEmpty() ||
					titleTextField.getText().isEmpty() ||
					locationTextField.getText().isEmpty() ||
					startDatePicker.getValue() == null ||
					startTimeCB.getValue() == null ||
					endDatePicker.getValue() == null ||
					endTimeCB.getValue() == null ||
					descriptionTextArea.getText().isEmpty();

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		userNameCB.setItems(HelperFunctions.getUserNames());
		customerNameCB.setItems(HelperFunctions.getCustomerNames());
		contactNameCB.setItems(HelperFunctions.getContactNames());
		startTimeCB.setItems(HelperFunctions.getBusinessHours());
		endTimeCB.setItems(HelperFunctions.getBusinessHours());
		try {
			apptIDTextField.setText(String.valueOf(HelperFunctions.getNextAppointmentID()));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Method to create an appointment when the add button is clicked.
	 *
	 * @param actionEvent The action event.
	 */
	public void addOnClick(ActionEvent actionEvent) {
		if (!emptyField.getAsBoolean()) {
			int customerIndex = customerNameCB.getSelectionModel().getSelectedIndex() + 1;
			ObservableList<Appointment> appointments = null;
			try {
				appointments = appointmentDAO.getAll();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			if (ApptHelperFunctions.validDateTime(startDatePicker, startTimeCB, endDatePicker, endTimeCB) &&
					ApptHelperFunctions.validEST(startTimeCB, endTimeCB, startDatePicker, endDatePicker) &&
					!ApptHelperFunctions.doAddApptOverlap(appointments, customerIndex, startDatePicker, startTimeCB, endDatePicker, endTimeCB)) {
				try {
					apptCreation(customerIndex);
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
				try {
					HelperFunctions.goToMain(actionEvent);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		} else
			ApptHelperFunctions.apptErr(apptErrLabel, userNameCB, customerNameCB, contactNameCB, typeTextField, titleTextField, locationTextField, startDatePicker, startTimeCB, endDatePicker, endTimeCB, descriptionTextArea);
	}

//	/**
//	 * Checks if any fields are empty.
//	 *
//	 * @return the boolean.
//	 */
//	private boolean emptyField() {
//		return userNameCB.getValue() == null ||
//				customerNameCB.getValue() == null ||
//				contactNameCB.getValue() == null ||
//				typeTextField.getText().isEmpty() ||
//				titleTextField.getText().isEmpty() ||
//				locationTextField.getText().isEmpty() ||
//				startDatePicker.getValue() == null ||
//				startTimeCB.getValue() == null ||
//				endDatePicker.getValue() == null ||
//				endTimeCB.getValue() == null ||
//				descriptionTextArea.getText().isEmpty();
//	}

	/**
	 * Appointment creation method.
	 *
	 * @param custID the customer id.
	 * @throws SQLException the sql exception.
	 *                      <p>
	 *                      LAMBDA #1
	 *                      <p>
	 *                      Using a Supplier interface means that the creation of an Appointment object is delayed until the get() method is called.
	 */
	public void apptCreation(int custID) throws SQLException {
		// The Supplier interface produces a result without taking any arguments. Only has get method.
		Supplier<Appointment> appointmentSupplier = () -> {
			int ID = Integer.parseInt(apptIDTextField.getText());
			int userID = HelperFunctions.getUserID(userNameCB.getValue());
			int contactID = HelperFunctions.getContactID(contactNameCB.getValue());
			String type = typeTextField.getText();
			String title = titleTextField.getText();
			String location = locationTextField.getText();
			LocalDateTime start = HelperFunctions.startEndFormatter(startDatePicker.getValue().toString() + " " + startTimeCB.getValue().toString());
			LocalDateTime end = HelperFunctions.startEndFormatter(endDatePicker.getValue().toString() + " " + endTimeCB.getValue().toString());
			String description = descriptionTextArea.getText();
			return new Appointment(ID, title, description, location, type, start, end, custID, userID, contactID);
		};
		Appointment appointment = appointmentSupplier.get();
		appointmentDAO.insert(appointment);
	}

	/**
	 * Cancel on click and return to main screen.
	 *
	 * @param actionEvent the action event.
	 * @throws IOException the io exception.
	 */
	public void cancelOnClick(ActionEvent actionEvent) throws IOException {
		HelperFunctions.goToMain(actionEvent);
	}
}