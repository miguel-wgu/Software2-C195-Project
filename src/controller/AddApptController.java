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
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AddApptController implements Initializable {
	@FXML
	private TextField apptIDTextField;
	@FXML
	private TextField addLocationTextField;
	@FXML
	private TextField typeTextField;
	@FXML
	private TextField titleTextField;
	@FXML
	private TextArea discriptionTextArea;
	@FXML
	private ComboBox<String> userNameCB;
	@FXML
	private ComboBox<String> customerNameCB;
	@FXML
	private ComboBox<String> contactNameCB;
	@FXML
	private ComboBox <LocalTime>startTimeCB;
	@FXML
	private ComboBox<LocalTime> endTimeCB;
	@FXML
	private DatePicker startDatePicker;
	@FXML
	private DatePicker endDatePicker;

	@FXML
	private Button addApptBtn; // TODO: Delete if not needed

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

	public void addOnClick(ActionEvent actionEvent) throws SQLException, IOException {
		AppointmentDAOImpl addAppt = new AppointmentDAOImpl();
		int ID = Integer.parseInt(apptIDTextField.getText());
		String location = addLocationTextField.getText();
		String type = typeTextField.getText();
		int customerID = HelperFunctions.getCustomerID(customerNameCB.getValue());
		int userID = HelperFunctions.getUserID(userNameCB.getValue());
		int contactID = HelperFunctions.getContactID(contactNameCB.getValue());
		String title = titleTextField.getText();
		String description = discriptionTextArea.getText();
		String start = startDatePicker.getValue().toString() + " " + startTimeCB.getValue().toString();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime startDateTime = LocalDateTime.parse(start, formatter);
		String end = endDatePicker.getValue().toString() + " " + endTimeCB.getValue().toString();
		LocalDateTime endDateTime = LocalDateTime.parse(end, formatter);
		Appointment appt = new Appointment(ID, title, description, location, type, startDateTime, endDateTime, customerID, userID, contactID);
		addAppt.insert(appt);
		HelperFunctions.goToMain(actionEvent);
	}

	public void cancelOnClick(ActionEvent actionEvent) throws IOException {
		HelperFunctions.goToMain(actionEvent);
	}
}
