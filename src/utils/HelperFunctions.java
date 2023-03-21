package utils;

import DAO.*;
import controller.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import model.*;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;

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

	// get contact ID from contact name
	public static int getContactID(String contactName) {
		int contactID;
		String sqlStatement = "SELECT Contact_ID FROM contacts WHERE Contact_Name = ?";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
			ps.setString(1, contactName);
			try (ResultSet result = ps.executeQuery()) {
				result.next();
				contactID = result.getInt("Contact_ID");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return contactID;
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

	// get customer names
	public static ObservableList<String> getCustomerNames() {
		CustomerDAOImpl customer = new CustomerDAOImpl();
		ObservableList<Customer> customerList = FXCollections.observableArrayList();
		try {
			customerList.addAll(customer.getAll());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		ObservableList<String> customerNames = FXCollections.observableArrayList();
		for (Customer c : customerList) {
			customerNames.add(c.getCustomerName());
		}
		return customerNames;
	}

	// get customer ID from customer name
	public static int getCustomerID(String customerName) {
		int customerID;
		String sqlStatement = "SELECT Customer_ID FROM customers WHERE Customer_Name = ?";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
			ps.setString(1, customerName);
			try (ResultSet result = ps.executeQuery()) {
				result.next();
				customerID = result.getInt("Customer_ID");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return customerID;
	}

	public static ObservableList<String> getUserNames() {
		UserDAOImpl userDAO = new UserDAOImpl();
		ObservableList<User> userList = FXCollections.observableArrayList();
		try {
			userList.addAll(userDAO.getAll());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		ObservableList<String> userNames = FXCollections.observableArrayList();
		for (User u : userList) {
			userNames.add(u.getUserName());
		}
		return userNames;
	}

	// Get user ID from username
	public static int getUserID(String userName) {
		int userID;
		String sqlStatement = "SELECT User_ID FROM users WHERE User_Name = ?";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
			ps.setString(1, userName);
			try (ResultSet result = ps.executeQuery()) {
				result.next();
				userID = result.getInt("User_ID");
				return userID;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static ObservableList<String> getCountryNames() {
		CountryDAOImpl country = new CountryDAOImpl();
		ObservableList<Country> countryList = FXCollections.observableArrayList();
		try {
			countryList.addAll(country.getAll());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		ObservableList<String> countries = FXCollections.observableArrayList();
		for (Country c : countryList) {
			countries.add(c.getCountryName());
		}
		return countries;
	}

	public static int getCountryID(String countryName) {
		int countryID;
		String sqlStatement = "SELECT Country_ID FROM countries WHERE Country = ?";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
			ps.setString(1, countryName);
			try (ResultSet result = ps.executeQuery()) {
				if (result.next()) {
					countryID = result.getInt("Country_ID");
					return countryID;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static ObservableList<String> getDivisions(int ID) {
		ObservableList<String> divisionList = FXCollections.observableArrayList();
		String sqlStatement = "SELECT DIVISION FROM first_level_divisions WHERE COUNTRY_ID = ?";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
			ps.setInt(1, ID);
			try (ResultSet result = ps.executeQuery()) {
				while (result.next()) {
					divisionList.add(result.getString("Division"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return divisionList;
	}

	public static int getNextCustomerID() throws SQLException {
		int nextID;
		String sqlStatement = "SELECT MAX(Customer_ID) FROM customers";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
			try (ResultSet result = ps.executeQuery()) {
				if (result.next()) {
					nextID = result.getInt("MAX(Customer_ID)") + 1;
					return nextID;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static int getNextAppointmentID() throws SQLException {
		int nextID;
		String updateKey = "SET information_schema_stats_expiry = 0";
		String sqlStatement = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'client_schedule' AND TABLE_NAME = 'appointments'";
		try (Statement statement = JDBC.connection.createStatement(); PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
			statement.execute(updateKey);
			try (ResultSet result = ps.executeQuery()) {
				if (result.next()) {
					nextID = result.getInt("AUTO_INCREMENT");
					return nextID;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	// Set business hours using UTC and store in observable list
	public static ObservableList<LocalTime> getBusinessHours() {
		ObservableList<LocalTime> businessHours = FXCollections.observableArrayList();
		LocalTime start = LocalTime.of(8, 0);
		LocalTime end = LocalTime.of(22, 0);
		while (start.isBefore(end)) {
			businessHours.add(start);
			start = start.plusMinutes(15);
		}
		return businessHours;
	}

	// Create alert that displays Appointment ID and the Appointment Type
	public static void appointmentDeleteAlert(int appointmentID, String appointmentType) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Appointment Alert");
		alert.setHeaderText("Appointment Alert");
		alert.setContentText("Appointment ID: " + appointmentID + " Appointment type: " + appointmentType + " has been deleted.");
		alert.showAndWait();
	}

	// Delete all appointments that are associated with a customer and refresh the table
	public static void deleteCustomerAppointments(int customerID) throws SQLException {
		String sqlStatement = "DELETE FROM appointments WHERE Customer_ID = ?";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
			ps.setInt(1, customerID);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}