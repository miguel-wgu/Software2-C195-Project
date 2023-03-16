package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactDAOImpl implements DAO<Contact> {

	@Override
	public Contact get(int id) throws SQLException {
		// Get a single contact from the database based on the contact ID
		String sqlStatement = "SELECT * FROM contacts WHERE Contact_ID = ?";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement);) {
			ps.setInt(1, id);
			try (ResultSet result = ps.executeQuery()) {
				if (result.next()) {
					int contactID = result.getInt("Contact_ID");
					String contactName = result.getString("Contact_Name");
					String email = result.getString("Email");
					Contact contact = new Contact(contactID, contactName, email);
					return contact;
				}
			}
		}
		return null;
	}

	@Override
	public ObservableList<Contact> getAll() throws SQLException {
		ObservableList<Contact> contacts = FXCollections.observableArrayList();
		String sqlStatement = "SELECT * FROM contacts";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement);) {
			try (ResultSet result = ps.executeQuery()) {
				while (result.next()) {
					int contactID = result.getInt("Contact_ID");
					String contactName = result.getString("Contact_Name");
					String email = result.getString("Email");
					Contact contact = new Contact(contactID, contactName, email);
					contacts.add(contact);
				}
				return contacts;
			}
		}
	}

	@Override
	public void insert(Contact contact) throws SQLException {

	}

	@Override
	public void update(Contact contact) throws SQLException {

	}

	@Override
	public void delete(Contact contact) throws SQLException {

	}

	public String getNameBasedOnID(int ID) throws SQLException {
		ObservableList<Contact> contactsList = this.getAll();
		for (Contact contact : contactsList) {
			if (contact.getContactID() == ID) {
				return contact.getName();
			}
		}
		return "";
	}

	public String getName(int contactID) {
		try {
			return this.getNameBasedOnID(contactID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
}
