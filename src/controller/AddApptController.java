package controller;

import DAO.AppointmentDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Appointments;
import utils.HelperFunctions;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddApptController implements Initializable {
	//	Add appointment details to the database
	@FXML
	private TextField apptTypeTextField;
	@FXML
	private Button addApptRtnMainBtn;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

	}

	public void addApptBtnClick(ActionEvent actionEvent) throws SQLException {
		AppointmentDaoImpl addAppt = new AppointmentDaoImpl();
		Appointments appt = new Appointments(apptTypeTextField.getText());
		addAppt.insert(appt);
//		System.out.println(apptTypeTextField.getText());

	}

	public void cancelReturnMainClick(ActionEvent actionEvent) throws IOException {
		HelperFunctions.goToMain(actionEvent);
	}
}
