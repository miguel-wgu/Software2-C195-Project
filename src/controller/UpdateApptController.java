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
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.function.BooleanSupplier;


public class UpdateApptController implements Initializable {
	AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();
	private Appointment selectedAppointment;
	@FXML
	private TextField locationTextField;
	@FXML
	private TextField appointmentIDTextField;
	@FXML
	private TextField titleTextField;
	@FXML
	private TextField typeTextField;
	@FXML
	private TextArea descriptionTextArea;
	@FXML
	private ComboBox<LocalTime> startTimeCB;
	@FXML
	private ComboBox<LocalTime> endTimeCB;
	@FXML
	private ComboBox<String> contactNameCB;
	@FXML
	private ComboBox<String> userNameCB;

	@FXML
	private ComboBox<String> customerNameCB;
	@FXML
	private DatePicker startDatePicker;
	@FXML
	private DatePicker endDatePicker;
	@FXML
	private Label apptErrLabel;
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
		selectedAppointment = MainController.getSelectedAppointment();
		String contactName = HelperFunctions.getContactName();
		appointmentIDTextField.setText(String.valueOf(selectedAppointment.getAppointmentID()));
		userNameCB.setItems(HelperFunctions.getUserNames());
		userNameCB.setValue(HelperFunctions.getUserName(selectedAppointment.getUserID()));
		customerNameCB.setItems(HelperFunctions.getCustomerNames());
		customerNameCB.setValue(HelperFunctions.getCustomerName(selectedAppointment.getCustomerID()));
		contactNameCB.setItems(HelperFunctions.getContactNames());
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

	@FXML
	public void updateApptBtnClick(ActionEvent actionEvent) throws IOException, SQLException {
		if (!emptyField.getAsBoolean()) {
			ObservableList<Appointment> appointments = appointmentDAO.getAll();
			int customerIndex = customerNameCB.getSelectionModel().getSelectedIndex() + 1;
			if (ApptHelperFunctions.validDateTime(startDatePicker, startTimeCB, endDatePicker, endTimeCB)
					&& ApptHelperFunctions.validEST(startTimeCB, endTimeCB, startDatePicker, endDatePicker)
					&& !ApptHelperFunctions.doUpdateApptOverlap(appointments, customerIndex, startDatePicker, startTimeCB, endDatePicker, endTimeCB, selectedAppointment)) {
				apptUpdate();
				HelperFunctions.goToMain(actionEvent);
			}
		} else
			ApptHelperFunctions.apptErr(apptErrLabel, userNameCB, customerNameCB, contactNameCB, typeTextField, titleTextField, locationTextField, startDatePicker, startTimeCB, endDatePicker, endTimeCB, descriptionTextArea);
	}

	private void apptUpdate() throws SQLException {
		selectedAppointment.setUserID(HelperFunctions.getUserID(userNameCB.getValue()));
		selectedAppointment.setCustomerID(customerNameCB.getSelectionModel().getSelectedIndex() + 1);
		selectedAppointment.setContactID(HelperFunctions.getContactID(contactNameCB.getValue()));
		selectedAppointment.setTitle(titleTextField.getText());
		selectedAppointment.setType(typeTextField.getText());
		selectedAppointment.setLocation(locationTextField.getText());
		selectedAppointment.setStartTime(startDatePicker.getValue().atTime(startTimeCB.getValue()));
		selectedAppointment.setEndTime(endDatePicker.getValue().atTime(endTimeCB.getValue()));
		selectedAppointment.setDescription(descriptionTextArea.getText());
		appointmentDAO.update(selectedAppointment);
	}

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

	@FXML
	public void cancelReturnMainClick(ActionEvent actionEvent) throws IOException {
		HelperFunctions.goToMain(actionEvent);
	}
}