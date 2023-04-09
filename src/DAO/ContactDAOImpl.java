package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The Contact DAO.
 * <br><br>
 * Responsible for all database operations related to contacts.
 *
 * @author Miguel Guzman
 */
public class ContactDAOImpl implements DAO<Contact> {

	/**
	 * Returns all contacts in the database.
	 *
	 * @return ObservableList<Contact>
	 * @throws SQLException if the database cannot be accessed.
	 */
	@Override
	public ObservableList<Contact> getAll() throws SQLException {
		ObservableList<Contact> contacts = FXCollections.observableArrayList();
		String sqlStatement = "SELECT * FROM contacts";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
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

	/**
	 * Get contact by id
	 *
	 * @param id the id
	 * @return the contact
	 * @throws SQLException if the contact does not exist.
	 */
	@Override
	public Contact get(int id) throws SQLException {
		String sqlStatement = "SELECT * FROM contacts WHERE Contact_ID = ?";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
			ps.setInt(1, id);
			try (ResultSet result = ps.executeQuery()) {
				if (result.next()) {
					int contactID = result.getInt("Contact_ID");
					String contactName = result.getString("Contact_Name");
					String email = result.getString("Email");
					return new Contact(contactID, contactName, email);
				}
			}
		}
		return null;
	}

	@Override
	public void insert(Contact contact) throws SQLException {
		// Not used
	}

	@Override
	public void update(Contact contact) throws SQLException {
		// Not used
	}

	@Override
	public void delete(Contact contact) throws SQLException {
		// Not used
	}

	/**
	 * Returns the name of the contact based on the contact ID.
	 *
	 * @param ID the contact ID.
	 * @return the name of the contact.
	 * @throws SQLException if the contact does not exist.
	 */
	public String getNameBasedOnID(int ID) throws SQLException {
		ObservableList<Contact> contactsList = this.getAll();
		for (Contact contact : contactsList) {
			if (contact.getContactID() == ID) {
				return contact.getName();
			}
		}
		return null;
	}

	/**
	 * Returns the name of the contact based on the contact ID.
	 *
	 * @param contactID the contact ID.
	 * @return the name of the contact.
	 */
	public String getName(int contactID) {
		try {
			return this.getNameBasedOnID(contactID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
