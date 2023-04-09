package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * The Appointment DAO.
 * <br><br>
 * Responsible for all database operations related to appointments.
 *
 * @author Miguel Guzman
 */
public class AppointmentDAOImpl implements DAO<Appointment> {
	/**
	 * Returns all appointments in the database.
	 *
	 * @return ObservableList<Appointment>
	 * @throws SQLException if the database cannot be accessed.
	 */
	@Override
	public ObservableList<Appointment> getAll() throws SQLException {
		ObservableList<Appointment> appointments = FXCollections.observableArrayList();
		String sqlStatement = "SELECT * FROM appointments";
		return getAppointments(appointments, sqlStatement);
	}

	/**
	 * Get appointment by id
	 *
	 * @param appointmentID the id
	 * @return the appointment
	 * @throws SQLException if the appointment does not exist.
	 */
	@Override
	public Appointment get(int appointmentID) throws SQLException {
		String sqlStatement = "SELECT * FROM appointments WHERE Appointment_ID = ?";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
			ps.setInt(1, appointmentID);
			try (ResultSet result = ps.executeQuery()) {
				if (result.next()) {
					return addApptToList(result);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Adds an appointment to the database.
	 *
	 * @param appointment Appointment to be added.
	 * @throws SQLException if the appointment already exists.
	 */
	@Override
	public void insert(Appointment appointment) throws SQLException {
		String sqlStatement = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
			setPrepStatementValues(ps, appointment);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Updates an appointment in the database.
	 *
	 * @param appointment Appointment to be updated.
	 */
	@Override
	public void update(Appointment appointment) throws SQLException {
		// Update selected appointment
		String sqlStatement = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
			setPrepStatementValues(ps, appointment);
			ps.setInt(10, appointment.getAppointmentID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets the prepared statement values.
	 *
	 * @param appointment Appointment to be set.
	 * @throws SQLException if the appointment already exists.
	 */
	@Override
	public void delete(Appointment appointment) throws SQLException {
		String sqlStatement = "DELETE FROM appointments WHERE Appointment_ID = ?";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
			ps.setInt(1, appointment.getAppointmentID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds an appointment to the list.
	 *
	 * @param result Result set to be added.
	 * @return Appointment
	 * @throws SQLException if the appointment already exists.
	 */
	private Appointment addApptToList(ResultSet result) throws SQLException {
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
		return new Appointment(appointmentID, title, description, location, type, start, end, customerID, userID, contactID);
	}

	/**
	 * Sets the values of the prepared statement.
	 *
	 * @param ps          Prepared statement to be set.
	 * @param appointment Appointment to be inserted.
	 * @throws SQLException if the appointment already exists.
	 */
	private void setPrepStatementValues(PreparedStatement ps, Appointment appointment) throws SQLException {
		LocalDateTime startDateTime = appointment.updateGetStartTime();
		LocalDateTime endDateTime = appointment.updateGetEndTime();
		Timestamp start = Timestamp.valueOf(startDateTime);
		Timestamp end = Timestamp.valueOf(endDateTime);
		ps.setString(1, appointment.getTitle());
		ps.setString(2, appointment.getDescription());
		ps.setString(3, appointment.getLocation());
		ps.setString(4, appointment.getType());
		ps.setTimestamp(5, start);
		ps.setTimestamp(6, end);
		ps.setInt(7, appointment.getCustomerID());
		ps.setInt(8, appointment.getUserID());
		ps.setInt(9, appointment.getContactID());
	}

	/**
	 * Returns all appointments in the database.
	 *
	 * @param appointments The list of appointments.
	 * @param sqlStatement The SQL statement.
	 * @return The list of appointments.
	 */
	private ObservableList<Appointment> getAppointments(ObservableList<Appointment> appointments, String sqlStatement) {
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement);
		     ResultSet result = ps.executeQuery()) {
			while (result.next()) {
				appointments.add(addApptToList(result));
			}
			return appointments;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}