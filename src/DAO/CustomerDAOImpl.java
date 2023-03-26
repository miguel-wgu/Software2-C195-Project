package DAO;

import controller.LoginController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import model.Customer;
import model.Report;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CustomerDAOImpl implements DAO<Customer> {
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
	@FXML
	String username;

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
		String sqlStatement = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
			ps.setString(1, customer.getCustomerName());
			ps.setString(2, customer.getAddress());
			ps.setString(3, customer.getPostalCode());
			ps.setString(4, customer.getPhone());
			ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
			ps.setString(6, username);
			ps.setInt(7, customer.getDivisionID());
			ps.setInt(8, customer.getCustomerID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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




//	public ObservableList<Customer> getAppointmentByDivision() {
//		ObservableList<Customer> reports = FXCollections.observableArrayList();
//		String sqlStatement = "SELECT Division, Country, COUNT(*) AS total_appointments FROM Appointments JOIN Customers ON Appointments.Customer_ID = Customers.Customer_ID JOIN First_Level_Divisions ON Customers.Division_ID = First_Level_Divisions.Division_ID JOIN Countries ON First_Level_Divisions.Country_ID = Countries.Country_ID GROUP BY Division, Country ORDER BY Division";
//		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement);
//		     ResultSet result = ps.executeQuery()) {
//			while (result.next()) {
//				reports.add(addReportToList2(result));
//			}
//			return reports;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//	private Customer addReportToList2(ResultSet result) {
//		try {
//			String division = result.getString("Division");
//			String country = result.getString("Country");
//			int total = result.getInt("total_appointments");
//			return new Customer(division, country, total);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
}
