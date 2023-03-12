package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.*;
import java.time.LocalDateTime;

public class AppointmentDaoImpl implements AppointmentsDao {

	@Override
	public ObservableList<Appointments> getAll() throws SQLException {
		ObservableList<Appointments> appointments = FXCollections.observableArrayList();
		String sqlStatement = "SELECT * FROM appointments";
		PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement);
		ResultSet result = ps.executeQuery();
		while (result.next()) {
			// print result title
			System.out.println(result.getString("Title"));
			int appointmentID = result.getInt("Appointment_ID");
			String title = result.getString("Title");
			String description = result.getString("Description");
			String location = result.getString("Location");
			String type = result.getString("Type");
			LocalDateTime start = result.getTimestamp("Start").toLocalDateTime();
			LocalDateTime end = result.getTimestamp("End").toLocalDateTime();
			int customerID = result.getInt("Customer_ID");
			int userID = result.getInt("User_ID");
			int contactID = result.getInt("Contact_ID");
			Appointments appt = new Appointments(appointmentID, title, description, location, type, start, end, customerID, userID, contactID);
			appointments.add(appt);
		}
		return appointments;
	}

	@Override
	public Appointments get(int appointmentID) throws SQLException {
		String sqlStatement = "SELECT * FROM appointments WHERE Appointment_ID = ?";
		PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement);
		ps.setInt(1, appointmentID);
		ResultSet result = ps.executeQuery();
		result.next();
		String title = result.getString("Title");
		String description = result.getString("Description");
		String location = result.getString("Location");
		String type = result.getString("Type");
		LocalDateTime start = result.getTimestamp("Start").toLocalDateTime();
		LocalDateTime end = result.getTimestamp("End").toLocalDateTime();
		int customerID = result.getInt("Customer_ID");
		int userID = result.getInt("User_ID");
		int contactID = result.getInt("Contact_ID");
		Appointments appt = new Appointments(appointmentID, title, description, location, type, start, end, customerID, userID, contactID);
		return appt;
	}


	@Override
	public void save(Appointments appointments) throws SQLException {

	}


	@Override
	public void insert(Appointments appointment) throws SQLException {
		String sqlStatement = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement);
		ps.setString(1, appointment.getTitle());
		ps.setString(2, appointment.getDescription());
		ps.setString(3, appointment.getLocation());
		ps.setString(4, appointment.getType());
		ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
		ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
		ps.setInt(7, 2);
		ps.setInt(8, 1);
		ps.setInt(9, 1);
		ps.executeUpdate();
	}


	@Override
	public void update(Appointments appointment) {

	}

	@Override
	public void delete(Appointments appointment) {

	}
}