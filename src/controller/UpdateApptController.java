package controller;

import DAO.AppointmentDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Appointment;
import utils.HelperFunctions;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;


public class UpdateApptController implements Initializable {

	private Appointment selectedAppointment;
	@FXML
	private TextField apptTitleTextField;
	@FXML
	private TextField apptTypeTextField;
	@FXML
	TextField updateLocation;
	@FXML
	private ComboBox<LocalDateTime> startTimeCB;
	@FXML
	private ComboBox<String> updateContactNameCB;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		selectedAppointment = MainController.getSelectedAppointment();
		String contactName = HelperFunctions.getContactName();

		apptTitleTextField.setText(selectedAppointment.getTitle());
		apptTypeTextField.setText(selectedAppointment.getType());
		updateLocation.setText(selectedAppointment.getLocation());
		startTimeCB.setValue(Timestamp.valueOf(selectedAppointment.getStartTime()).toLocalDateTime());
		updateContactNameCB.setValue(contactName);
		updateContactNameCB.setItems(HelperFunctions.getContactNames());

	}

	@FXML
	public void updateApptBtnClick() {
		AppointmentDAOImpl updatedAppt = new AppointmentDAOImpl();
		selectedAppointment.setType(apptTypeTextField.getText());
		updatedAppt.update(selectedAppointment);
	}

	@FXML
	public void cancelReturnMainClick(ActionEvent actionEvent) throws IOException {
		HelperFunctions.goToMain(actionEvent);
	}
}
