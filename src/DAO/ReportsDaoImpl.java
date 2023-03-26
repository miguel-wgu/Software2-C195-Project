package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Report;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ReportsDaoImpl implements DAO<Report> {
	@Override
	public Report get(int id) throws SQLException {
		return null;
	}

	@Override
	public ObservableList<Report> getAll() throws SQLException {
		return null;
	}

	@Override
	public void insert(Report report) throws SQLException {

	}

	@Override
	public void update(Report report) throws SQLException {

	}

	@Override
	public void delete(Report report) throws SQLException {

	}

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

	public ObservableList<Appointment> getAppointmentByContactId(int contactId) {
		ObservableList<Appointment> appointments = FXCollections.observableArrayList();
		String sqlStatement = "SELECT Appointment_ID, Title, Type, Description, Start, End, Customer_ID FROM Appointments WHERE Contact_ID = ?";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
			ps.setInt(1, contactId);
			try (ResultSet result = ps.executeQuery()) {
				while (result.next()) {
					appointments.add(addAppointmentToList(result));
				}
			} return appointments;
		} catch (SQLException e) {
			e.printStackTrace();
		} return null;
	}

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

	// Get total number of appointments by division and country
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

	private Report addReportToList2(ResultSet result) {
		try {
			String division = result.getString("Division");
			System.out.println(division);
			String country = result.getString("Country");
			int total = result.getInt("total_appointments");
			return new Report(country, division, total);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}