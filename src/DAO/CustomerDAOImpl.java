package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAOImpl implements DAO<Customer> {
	public final static String SELECT_BY_ID = "SELECT * FROM customers " +
			"INNER JOIN first_level_divisions " +
			"ON customers.Division_ID = first_level_divisions.Division_ID " +
			"WHERE Customer_ID = ?";
	public final static String SELECT_ALL = "SELECT customers.Customer_ID, customers.Customer_Name, " +
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

	}

	@Override
	public void update(Customer customer) throws SQLException {

	}

	@Override
	public void delete(Customer customer) throws SQLException {

	}

	private Customer addCustomerToList(ResultSet result) throws SQLException {
		int customerID = result.getInt("Customer_ID");
		String customerName = result.getString("Customer_Name");
		String address = result.getString("Address");
		String postalCode = result.getString("Postal_Code");
		String phone = result.getString("Phone");
		int divisionID = result.getInt("Division_ID");
		String divisionName = result.getString("Division");
		System.out.println(divisionName);
		return new Customer(customerID, customerName, address, postalCode, phone, divisionID, divisionName);
	}
}
