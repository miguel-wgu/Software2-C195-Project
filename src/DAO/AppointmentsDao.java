package DAO;

import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.SQLException;

/**
 * The AppointmentsDao interface.
 */
public interface AppointmentsDao extends DAO<Appointments> {
	public ObservableList<Appointments> getAll() throws SQLException;

	public Appointments get(int appointmentID) throws SQLException;

	public void insert(Appointments appointment);

	public void update(Appointments appointment);

	public void delete(Appointments appointment);
}
