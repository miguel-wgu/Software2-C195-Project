package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public void insert(Appointments appointment) {

	}


	@Override
	public void update(Appointments appointment) {

	}

	@Override
	public void delete(Appointments appointment) {

	}
}