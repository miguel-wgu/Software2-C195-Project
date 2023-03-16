package controller;

import DAO.AppointmentDAOImpl;
import DAO.CustomerDAOImpl;
import DAO.FirstLevelDivisionDAOImpl;
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
import model.Appointment;
import model.Country;
import model.Customer;
import model.FirstLevelDivision;
import utils.ErrMsg;

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
	 * The Appointment table.
	 */
	@FXML
	private TableView<Appointment> appointmentsTable;
	/**
	 * The Appointment id column.
	 */
	@FXML
	private TableColumn<Appointment, Integer> appointmentIDColumn;
	/**
	 * The Title column.
	 */
	@FXML
	private TableColumn<Appointment, String> titleColumn;
	/**
	 * The Description column.
	 */
	@FXML
	private TableColumn<Appointment, String> descriptionColumn;
	/**
	 * The Location column.
	 */
	@FXML
	private TableColumn<Appointment, String> locationColumn;
	/**
	 * The Contact column.
	 */
	@FXML
	private TableColumn<Appointment, Integer> contactColumn;
	/**
	 * The Type column.
	 */
	@FXML
	private TableColumn<Appointment, String> typeColumn;
	/**
	 * The Start time column.
	 */
	@FXML
	private TableColumn<Appointment, LocalDate> startTimeColumn;
	/**
	 * The End time column.
	 */
	@FXML
	private TableColumn<Appointment, LocalDate> endTimeColumn;
	/**
	 * The Customer id column.
	 */
	@FXML
	private TableColumn<Appointment, Integer> customerIDColumn;
	/**
	 * The User id column.
	 */
	@FXML
	private TableColumn<Appointment, Integer> userIDColumn;

	private static Appointment selectedAppointment;

	/**
	 * Gets selected appointment.
	 *
	 * @return the selected appointment
	 */
	public static Appointment getSelectedAppointment() {
		return selectedAppointment;
	}

	/**
	 * The Add appointment button.
	 */
	@FXML
	private Button addApptBtn;
	@FXML
	private Button updateApptBtn;








	/**
	 * The Customer Table
	 */
	@FXML private TableView<Customer> customersTable;
	/**
	 * The Customer ID column
	 */
	@FXML private TableColumn<?, ?> customerIDColumn2;
	/**
	 * The Customer Name column
	 */
	@FXML private TableColumn<?, ?> customerNameColumn;
	/**
	 * The Phone Number column
	 */
	@FXML private TableColumn<?, ?> phoneNumColumn;
	/**
	 * The Address column
	 */
	@FXML private TableColumn<?, ?> addressColumn;
	/**
	 * The State column
	 */
	@FXML private TableColumn<?, ?> stateColumn;
	/**
	 * The Postal Code column
	 */
	@FXML private TableColumn<?, ?> postalCodeColumn;







	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		AppointmentDAOImpl appointmentsList = new AppointmentDAOImpl();
		ObservableList<Appointment> appointments;

		/**
		 * Try block to catch any SQL exceptions, otherwise get all appointments from the database
		 */
		try {
			appointments = appointmentsList.getAll();
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
		} catch (SQLException e) {
			throw new RuntimeException("Error getting all appointments", e);
		}

		CustomerDAOImpl customers = new CustomerDAOImpl();
		ObservableList<Customer> customersList;

		try {
			customersList = customers.getAll();
			customerIDColumn2.setCellValueFactory(new PropertyValueFactory<>("customerID"));
			customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
			phoneNumColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
			addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
			stateColumn.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
			postalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
			customersTable.setItems(customersList);
		} catch (SQLException e) {
			throw new RuntimeException("Error getting all customers", e);
		}

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
	 * @throws IOException the io exception
	 */
	public void updateApptBtn(ActionEvent actionEvent) throws IOException {
		selectedAppointment = appointmentsTable.getSelectionModel().getSelectedItem();
		try {
			if (selectedAppointment == null) {
				throw new NullPointerException();
			} else {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UpdateApptScene.fxml"));
				Stage stage = (Stage) updateApptBtn.getScene().getWindow();
				Scene scene = new Scene(loader.load());
				stage.setTitle("Appointment Scheduler");
				stage.setScene(scene);
			}
		} catch (NullPointerException e) {
			ErrMsg.noApptorCustSelected("Please select an appointment to update", "No appointment selected");
		}
	}

	/**
	 * Delete appt btn.
	 *
	 * @param actionEvent the action event
	 * @throws SQLException the sql exception
	 */
	public void deleteApptBtn(ActionEvent actionEvent) throws SQLException {
		try {
			if (appointmentsTable.getSelectionModel().getSelectedItem() != null) {
				AppointmentDAOImpl appointmentDao = new AppointmentDAOImpl();
				Appointment appointment = appointmentsTable.getSelectionModel().getSelectedItem();
				appointmentDao.delete(appointment);
				appointmentsTable.getItems().remove(appointment);
			} else {
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			ErrMsg.noApptorCustSelected("Please select an appointment to delete", "No appointment selected");
		}
	}
}
