package controller;

import DAO.AppointmentDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointments;

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
		// Cancel and return to MainScene
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainScene.fxml"));
		Stage stage = (Stage) addApptRtnMainBtn.getScene().getWindow();
		Scene scene = new Scene(loader.load());
		stage.setTitle("Appointment Scheduler");
		stage.setScene(scene);
		stage.show();
	}


}
