package controller;

import DAO.AppointmentDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Appointment;
import utils.HelperFunctions;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;


public class UpdateApptController implements Initializable {

	private Appointment selectedAppointment;
	@FXML
	private Button updateApptBtn;
	@FXML
	private Button updateApptRtnMainBtn;
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
	public void updateApptBtnClick(ActionEvent actionEvent) throws IOException {
		AppointmentDAOImpl updatedAppt = new AppointmentDAOImpl();
		selectedAppointment.setUserID(HelperFunctions.getUserID(userNameCB.getValue()));
		selectedAppointment.setCustomerID(customerNameCB.getSelectionModel().getSelectedIndex() + 1);
		selectedAppointment.setContactID(HelperFunctions.getContactID(contactNameCB.getValue()));
		selectedAppointment.setTitle(titleTextField.getText());
		selectedAppointment.setType(typeTextField.getText());
		selectedAppointment.setLocation(locationTextField.getText());
		selectedAppointment.setStartTime(startDatePicker.getValue().atTime(startTimeCB.getValue()));
		selectedAppointment.setEndTime(endDatePicker.getValue().atTime(endTimeCB.getValue()));
		selectedAppointment.setDescription(descriptionTextArea.getText());
		updatedAppt.update(selectedAppointment);
		HelperFunctions.goToMain(actionEvent);
	}

	@FXML
	public void cancelReturnMainClick(ActionEvent actionEvent) throws IOException {
		HelperFunctions.goToMain(actionEvent);
	}
}
