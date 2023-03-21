package DAO;

import controller.LoginController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CustomerDAOImpl implements DAO<Customer> {
	@FXML
	String username;

	public static final String SELECT_BY_ID = "SELECT * FROM customers " +
			"INNER JOIN first_level_divisions " +
			"ON customers.Division_ID = first_level_divisions.Division_ID " +
			"WHERE Customer_ID = ?";
	public static final String SELECT_ALL = "SELECT customers.Customer_ID, customers.Customer_Name, " +
			"customers.Address, customers.Postal_Code, customers.Phone, customers.Division_ID, " +
			"first_level_divisions.Division " +
			"from customers " +
			"INNER JOIN  first_level_divisions " +
			"ON customers.Division_ID = first_level_divisions.Division_ID";

	@Override
	public Customer get(int id) throws SQLException {
		try (PreparedStatement ps = JDBC.connection.prepareStatement(SELECT_BY_ID)) {
			ps.setInt(1, id);
			try (ResultSet result = ps.executeQuery()) {
				if (result.next()) {
					return addCustomerToList(result);
				}
			}
		}
		return null;
	}

	@Override
	public ObservableList<Customer> getAll() throws SQLException {
		ObservableList<Customer> customers = FXCollections.observableArrayList();
		try (PreparedStatement ps = JDBC.connection.prepareStatement(SELECT_ALL);
		     ResultSet result = ps.executeQuery()) {
			while (result.next()) {
				customers.add(addCustomerToList(result));
			}
			return customers;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void insert(Customer customer) throws SQLException {
		username = LoginController.getUserName();
		String sqlStatement = "INSERT INTO customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?,?,?,?,?,?,?,?,?,?)";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
			ps.setInt(1, customer.getCustomerID());
			ps.setString(2, customer.getCustomerName());
			ps.setString(3, customer.getAddress());
			ps.setString(4, customer.getPostalCode());
			ps.setString(5, customer.getPhone());
			ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
			ps.setString(7, username);
			ps.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
			ps.setString(9, username);
			ps.setInt(10, customer.getDivisionID());
			ps.executeUpdate();
		}
	}

	@Override
	public void update(Customer customer) throws SQLException {
        // TODO: 3/29/2021  Update customer
	}

	@Override
	public void delete(Customer customer) throws SQLException {
		String sqlStatement = "DELETE FROM customers WHERE Customer_ID = ?";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
			ps.setInt(1, customer.getCustomerID());
			ps.executeUpdate();
		}
	}

	private Customer addCustomerToList(ResultSet result) throws SQLException {
		int customerID = result.getInt("Customer_ID");
		String customerName = result.getString("Customer_Name");
		String address = result.getString("Address");
		String postalCode = result.getString("Postal_Code");
		String phone = result.getString("Phone");
		int divisionID = result.getInt("Division_ID");
		String divisionName = result.getString("Division");
		return new Customer(customerID, customerName, address, postalCode, phone, divisionID, divisionName);
	}
}
