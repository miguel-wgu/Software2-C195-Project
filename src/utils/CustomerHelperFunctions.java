package utils;

import DAO.CustomerDAOImpl;
import DAO.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Helper functions for customers.
 *
 * @author Miguel Guzman
 */
public class CustomerHelperFunctions {
	/**
	 * Returns a list of all customer names in the database.
	 *
	 * @return ObservableList<String>
	 */
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

	/**
	 * Returns a customer's ID based on their name.
	 *
	 * @param customerName Customer name
	 * @return customerID
	 */
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

	/**
	 * Returns the next available customer ID.
	 *
	 * @return int
	 */
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

	/**
	 * Deletes all appointments associated with a customer.
	 *
	 * @param customerID Customer ID
	 */
	public static void deleteCustomerAppointments(int customerID) throws SQLException {
		String sqlStatement = "DELETE FROM appointments WHERE Customer_ID = ?";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
			ps.setInt(1, customerID);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns a customer's name based on their ID.
	 *
	 * @param customerID Customer ID
	 * @return customerName Customer name
	 */
	public static String getCustomerName(int customerID) {
		String customerName = "";
		String sqlStatement = "SELECT Customer_Name FROM customers WHERE Customer_ID = ?";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
			ps.setInt(1, customerID);
			try (ResultSet result = ps.executeQuery()) {
				customerName = Optional.ofNullable(result.next() ? result.getString("Customer_Name") : null)
						.orElse("");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customerName;
	}
}
