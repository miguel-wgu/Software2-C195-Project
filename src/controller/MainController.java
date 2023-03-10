package controller;

import DAO.AppointmentDaoImpl;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointments;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * The type Main controller.
 */
public class MainController implements Initializable {
	/**
	 * The Appointments table.
	 */
	@FXML
	private TableView<Appointments> appointmentsTable;
	/**
	 * The Appointment id column.
	 */
	@FXML
	private TableColumn<Appointments, Integer> appointmentIDColumn;
	/**
	 * The Title column.
	 */
	@FXML
	private TableColumn<Appointments, String> titleColumn;
	/**
	 * The Description column.
	 */
	@FXML
	private TableColumn<Appointments, String> descriptionColumn;
	/**
	 * The Location column.
	 */
	@FXML
	private TableColumn<Appointments, String> locationColumn;
	/**
	 * The Contact column.
	 */
	@FXML
	private TableColumn<Appointments, String> contactColumn;
	/**
	 * The Type column.
	 */
	@FXML
	private TableColumn<Appointments, String> typeColumn;
	/**
	 * The Start time column.
	 */
	@FXML
	private TableColumn<Appointments, LocalDate> startTimeColumn;
	/**
	 * The End time column.
	 */
	@FXML
	private TableColumn<Appointments, LocalDate> endTimeColumn;
	/**
	 * The Customer id column.
	 */
	@FXML
	private TableColumn<Appointments, Integer> customerIDColumn;
	/**
	 * The User id column.
	 */
	@FXML
	private TableColumn<Appointments, Integer> userIDColumn;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		AppointmentDaoImpl appointmentsList = new AppointmentDaoImpl();
		ObservableList<Appointments> appointments;
		/**
		 * Try block to catch any SQL exceptions, otherwise get all appointments from the database
		 */
		try {
			appointments = appointmentsList.getAll();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		appointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
		contactColumn.setCellValueFactory(new PropertyValueFactory<>("contactID"));
		typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
		startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
		endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
		customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
		userIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
		appointmentsTable.setItems(appointments);
	}

	/**
	 * Add appt btn click.
	 *
	 * @param actionEvent the action event
	 */
	public void addApptBtnClick(ActionEvent actionEvent) {
		// open add appointment window

	}

	/**
	 * Update appt btn.
	 *
	 * @param actionEvent the action event
	 */
	public void updateApptBtn(ActionEvent actionEvent) {
	}

	/**
	 * Delete appt btn.
	 *
	 * @param actionEvent the action event
	 */
	public void deleteApptBtn(ActionEvent actionEvent) {
	}
}
