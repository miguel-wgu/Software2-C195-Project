package controller;

import DAO.AppointmentDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Appointment;
import utils.HelperFunctions;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddApptController implements Initializable {
	//	Add appointment details to the database

	@FXML
	private TextField apptTypeTextField;
	@FXML TextField addLocation;
	@FXML
	private Button addApptRtnMainBtn;
	@FXML
	private ComboBox<String> addContactNameCB;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		addContactNameCB.setItems(HelperFunctions.getContactNames());
	}

	public void addApptBtnClick(ActionEvent actionEvent) throws SQLException {
		AppointmentDAOImpl addAppt = new AppointmentDAOImpl();
		Appointment appt = new Appointment(addLocation.getText(),apptTypeTextField.getText());
		addAppt.insert(appt);
	}

	public void cancelReturnMainClick(ActionEvent actionEvent) throws IOException {
		HelperFunctions.goToMain(actionEvent);
	}
}
