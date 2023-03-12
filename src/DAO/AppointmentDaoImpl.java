package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * The type Appointment dao.
 */
public class AppointmentDaoImpl implements DAO<Appointments> {
//public class AppointmentDaoImpl implements AppointmentsDao{

	/**
	 * Returns all appointments in the database.
	 *
	 * @return ObservableList<Appointments>
	 * @throws SQLException if the database is not available.
	 */
	@Override
	public ObservableList<Appointments> getAll() throws SQLException {
		ObservableList<Appointments> appointments = FXCollections.observableArrayList();
		String sqlStatement = "SELECT * FROM appointments";
		try {
			PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
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
		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		}
	}

	/**
	 * Returns a single appointment from the database.
	 *
	 * @param appointmentID ID of the appointment to be returned.
	 * @return Appointments
	 * @throws SQLException if the appointment does not exist.
	 */
	@Override
	public Appointments get(int appointmentID) throws SQLException {
		String sqlStatement = "SELECT * FROM appointments WHERE Appointment_ID = ?";
		try {
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
			return new Appointments(appointmentID, title, description, location, type, start, end, customerID, userID, contactID);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public void save(Appointments appointments) throws SQLException {

	}


	/**
	 * Inserts a new appointment into the database.
	 *
	 * @param appointment Appointment to be inserted.
	 * @throws SQLException if the appointment already exists.
	 */
	@Override
	public void insert(Appointments appointment) throws SQLException {
		String sqlStatement = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void update(Appointments appointment) {
		// Update selected appointment
		String sqlStatement = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
		try {
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
			ps.setInt(10, appointment.getAppointmentID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deletes an appointment from the database.
	 *
	 * @param appointment Appointment to be deleted.
	 * @throws SQLException if the appointment does not exist.
	 */
	@Override
	public void delete(Appointments appointment) throws SQLException {
		String sqlStatement = "DELETE FROM appointments WHERE Appointment_ID = ?";
		try {
			PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement);
			ps.setInt(1, appointment.getAppointmentID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}