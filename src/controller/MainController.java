package controller;

import DAO.AppointmentDaoImpl;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;

import java.io.IOException;
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

	/**
	 * The Add appointment button.
	 */
	@FXML private Button addApptBtn;

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
	 * @throws IOException the io exception
	 */
	public void addApptBtnClick(ActionEvent actionEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddApptScene.fxml"));
		Stage stage = (Stage) addApptBtn.getScene().getWindow();
		Scene scene = new Scene(loader.load());
		stage.setTitle("Appointment Scheduler");
		stage.setScene(scene);
	}

	/**
	 * Update appt btn.
	 *
	 * @param actionEvent the action event
	 */
	public void updateApptBtn(ActionEvent actionEvent) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UpdateApptScene.fxml"));
	}

	/**
	 * Delete appt btn.
	 *
	 * @param actionEvent the action event
	 * @throws SQLException the sql exception
	 */
	public void deleteApptBtn(ActionEvent actionEvent) throws SQLException {
		AppointmentDaoImpl appointmentDao = new AppointmentDaoImpl();
		Appointments appointment = appointmentsTable.getSelectionModel().getSelectedItem();
		appointmentDao.delete(appointment);
		appointmentsTable.getItems().remove(appointment);
	}
}
