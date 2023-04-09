package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Report;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * The Reports DAO.
 * <br><br>
 * Responsible for all database operations related to reports.
 *
 * @author Miguel Guzman
 */
public class ReportsDaoImpl implements DAO<Report> {
	@Override
	public ObservableList<Report> getAll() throws SQLException {
		return null;
	}

	@Override
	public Report get(int id) throws SQLException {
		return null;
	}

	@Override
	public void insert(Report report) throws SQLException {
		// Not used
	}

	@Override
	public void update(Report report) throws SQLException {
		// Not used
	}

	@Override
	public void delete(Report report) throws SQLException {
		// Not used
	}

	/**
	 * Returns a list of all appointments by month.
	 *
	 * @return ObservableList<Report>
	 */
	public ObservableList<Report> getAppointmentTypesByMonth() {
		ObservableList<Report> reports = FXCollections.observableArrayList();
		String sqlStatement = "SELECT MONTHNAME(Start) AS month, type, COUNT(*) AS total_appointments FROM Appointments GROUP BY MONTHNAME(Start), type ORDER BY MONTH(Start), type";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement);
		     ResultSet result = ps.executeQuery()) {
			while (result.next()) {
				reports.add(addReportToList(result));
			}
			return reports;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * Returns a list containing the month, type, and total appointments.
	 *
	 * @param result the result set
	 * @return the report
	 */
	private Report addReportToList(ResultSet result) {
		try {
			String month = result.getString("month");
			String type = result.getString("type");
			int total = result.getInt("total_appointments");
			return new Report(month, type, total);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Returns a list of all appointments by contact id.
	 *
	 * @return ObservableList<Report>
	 */
	public ObservableList<Appointment> getAppointmentByContactId(int contactId) {
		ObservableList<Appointment> appointments = FXCollections.observableArrayList();
		String sqlStatement = "SELECT Appointment_ID, Title, Type, Description, Start, End, Customer_ID FROM Appointments WHERE Contact_ID = ?";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
			ps.setInt(1, contactId);
			try (ResultSet result = ps.executeQuery()) {
				while (result.next()) {
					appointments.add(addAppointmentToList(result));
				}
			}
			return appointments;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Turns the result set into an appointment object.
	 *
	 * @param result the result set
	 * @return the appointment
	 */
	private Appointment addAppointmentToList(ResultSet result) {
		try {
			int appointmentId = result.getInt("Appointment_ID");
			String title = result.getString("Title");
			String type = result.getString("Type");
			String description = result.getString("Description");
			LocalDateTime start = result.getTimestamp("Start").toLocalDateTime();
			LocalDateTime end = result.getTimestamp("End").toLocalDateTime();
			int customerId = result.getInt("Customer_ID");
			return new Appointment(appointmentId, title, type, description, start, end, customerId);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Returns a list of all appointments by division.
	 *
	 * @return ObservableList<Report>
	 */
	public ObservableList<Report> getAppointmentByDivision() {
		ObservableList<Report> reports = FXCollections.observableArrayList();
		String sqlStatement = "SELECT Division, Country, COUNT(*) AS total_appointments FROM Appointments JOIN Customers ON Appointments.Customer_ID = Customers.Customer_ID JOIN First_Level_Divisions ON Customers.Division_ID = First_Level_Divisions.Division_ID JOIN Countries ON First_Level_Divisions.Country_ID = Countries.Country_ID GROUP BY Division, Country ORDER BY Division";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement);
		     ResultSet result = ps.executeQuery()) {
			while (result.next()) {
				reports.add(addReportToList2(result));
			}
			return reports;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Returns a list containing the division, country, and total appointments.
	 *
	 * @param result the result set
	 * @return the report
	 */
	private Report addReportToList2(ResultSet result) {
		try {
			String division = result.getString("Division");
			String country = result.getString("Country");
			int total = result.getInt("total_appointments");
			return new Report(country, division, total);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}