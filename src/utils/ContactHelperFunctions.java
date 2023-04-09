package utils;

import DAO.ContactDAOImpl;
import DAO.JDBC;
import controller.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Helper functions for contacts.
 * <br><br>
 *
 * @author Miguel Guzman
 */
public class ContactHelperFunctions {
	/**
	 * Returns a list of contact names.
	 *
	 * @return ObservableList<String>
	 */
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

	/**
	 * Returns the contact ID for a given contact name.
	 *
	 * @param contactName Contact name.
	 * @return int
	 */
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

	/**
	 * Returns the contact name for a given contact ID.
	 *
	 * @return String
	 */
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
