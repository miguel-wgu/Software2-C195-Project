package utils;

import DAO.ContactDAOImpl;
import controller.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import model.Appointment;
import model.Contact;

import java.io.IOException;
import java.sql.SQLException;

public class HelperFunctions {

	public static void goToMain(ActionEvent actionEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(HelperFunctions.class.getResource("/view/MainScene.fxml"));
		Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
		Scene scene = new Scene(loader.load());
		stage.setTitle("Appointment Scheduler");
		stage.setScene(scene);
		stage.show();
	}

	public static ObservableList<String> getContactNames() {
		ContactDAOImpl contact = new ContactDAOImpl();
		ObservableList<Contact> contactsList = FXCollections.observableArrayList();
		try {
			contactsList.addAll(contact.getAll());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		ObservableList<String> contactNames = FXCollections.observableArrayList();
		for (Contact c : contactsList) {
			contactNames.add(c.getName());
		}
		return contactNames;
	}


	public static String getContactName() {
		Appointment selectedAppointment = MainController.getSelectedAppointment();
		ContactDAOImpl contact = new ContactDAOImpl();
		try {
			contact.getAll();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return contact.getName(selectedAppointment.getContactID());
	}
}