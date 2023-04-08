package controller;

import DAO.AppointmentDAOImpl;
import DAO.CustomerDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Customer;
import utils.ErrMsg;
import utils.HelperFunctions;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The type Main controller.
 */
public class MainController implements Initializable {
	private static Appointment selectedAppointment;
	private static Customer selectedCustomer;
	private final AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();
	private ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
	/**
	 * The Appointment table.
	 */
	@FXML
	private TableView<Appointment> appointmentsTable;
	@FXML
	private RadioButton weekRadio;
	@FXML
	private RadioButton monthRadio;
	@FXML
	private RadioButton allRadio;
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
	@FXML
	private TableView<Customer> customersTable;
	/**
	 * The Customer ID column
	 */
	@FXML
	private TableColumn<Customer, Integer> customerIDColumn2;
	/**
	 * The Customer Name column
	 */
	@FXML
	private TableColumn<Customer, String> customerNameColumn;
	/**
	 * The Phone Number column
	 */
	@FXML
	private TableColumn<Customer, String> phoneNumColumn;
	/**
	 * The Address column
	 */
	@FXML
	private TableColumn<Customer, String> addressColumn;
	/**
	 * The State column
	 */
	@FXML
	private TableColumn<Customer, String> stateColumn;
	/**
	 * The Postal Code column
	 */
	@FXML
	private TableColumn<Customer, String> postalCodeColumn;
	@FXML
	private Button addCustomerBtn;
	@FXML
	private Button updateCustomerBtn;
	@FXML
	private Button reportsBtn;
	@FXML
	private Button logOutBtn;

	/**
	 * Gets selected appointment.
	 *
	 * @return the selected appointment
	 */
	public static Appointment getSelectedAppointment() {
		return selectedAppointment;
	}

	public static Customer getSelectedCustomer() {
		return selectedCustomer;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		try {
			allAppointments = appointmentDAO.getAll();
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
			appointmentsTable.setItems(allAppointments);
		} catch (Exception e) {
			e.printStackTrace();
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
	 * @throws IOException the io exception
	 */
	public void addApptBtnClick() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddApptScene.fxml"));
		Stage stage = (Stage) addApptBtn.getScene().getWindow();
		Scene scene = new Scene(loader.load());
		stage.setTitle("Add Appointment");
		stage.setScene(scene);
	}

	/**
	 * Update appt btn.
	 *
	 * @throws IOException the io exception
	 */
	public void updateApptBtn() throws IOException {
		selectedAppointment = appointmentsTable.getSelectionModel().getSelectedItem();
		try {
			if (selectedAppointment == null) {
				throw new NullPointerException();
			} else {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UpdateApptScene.fxml"));
				Stage stage = (Stage) updateApptBtn.getScene().getWindow();
				Scene scene = new Scene(loader.load());
				stage.setTitle("Update Appointment");
				stage.setScene(scene);
			}
		} catch (NullPointerException e) {
			ErrMsg.errorAlert("Please select an appointment to update", "No appointment selected");
		}
	}

	/**
	 * Delete appt btn.
	 *
	 * @throws SQLException the sql exception
	 */
	public void deleteApptBtn() throws SQLException {
		try {
			if (appointmentsTable.getSelectionModel().getSelectedItem() != null) {
				AppointmentDAOImpl appointmentDao = new AppointmentDAOImpl();
				Appointment appointment = appointmentsTable.getSelectionModel().getSelectedItem();
				appointmentDao.delete(appointment);
				appointmentsTable.getItems().remove(appointment);
				HelperFunctions.appointmentDeleteAlert(appointment.getAppointmentID(), appointment.getType());
			} else {
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			ErrMsg.errorAlert("Please select an appointment to delete", "No appointment selected");
		}
	}

	public void addCustomerBtnClick() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddCustomerScene.fxml"));
		Stage stage = (Stage) addCustomerBtn.getScene().getWindow();
		Scene scene = new Scene(loader.load());
		stage.setTitle("Add Customer");
		stage.setScene(scene);
	}

	public void updateCustomerBtnClick() {
		selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
		try {
			if (selectedCustomer == null) {
				throw new NullPointerException();
			} else {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UpdateCustomerScene.fxml"));
				Stage stage = (Stage) updateCustomerBtn.getScene().getWindow();
				Scene scene = new Scene(loader.load());
				stage.setTitle("Update Customer");
				stage.setScene(scene);
			}
		} catch (NullPointerException e) {
			ErrMsg.errorAlert("Please select a customer to update", "No customer selected");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteCustomerBtnClick() {
		selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
		if (selectedCustomer == null) {
			ErrMsg.errorAlert("Please select a customer to delete", "No customer selected");
			return;
		}
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Delete Customer");
		alert.setHeaderText("Are you sure you want to delete " + selectedCustomer.getCustomerName() + "?");
		alert.setContentText("This will delete all appointments associated with this customer");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			try {
				CustomerDAOImpl customerDao = new CustomerDAOImpl();
				HelperFunctions.deleteCustomerAppointments(selectedCustomer.getCustomerID());
				ObservableList<Appointment> appointmentsList;
				appointmentsList = appointmentDAO.getAll();
				appointmentsTable.setItems(appointmentsList);
				customerDao.delete(selectedCustomer);
				customersTable.getItems().remove(selectedCustomer);
				Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
				successAlert.setTitle("Customer Deleted");
				successAlert.setHeaderText("Success!");
				successAlert.setContentText(selectedCustomer.getCustomerName() + " has been deleted");
				successAlert.showAndWait();
			} catch (SQLException e) {
				ErrMsg.errorAlert("Error deleting " + selectedCustomer.getCustomerName(), "Error");
			}
		}
	}

	public void weekSelect() throws SQLException {
		appointmentDAO.getAll();
		ObservableList<Appointment> appointments = FXCollections.observableArrayList();
		for (Appointment appointment : allAppointments) {
			if (appointment.updateGetStartTime().isAfter(LocalDateTime.now()) && appointment.updateGetEndTime().isBefore(LocalDateTime.now().plusDays(7)))
				appointments.add(appointment);
			appointmentsTable.setItems(appointments);
		}
	}

	public void monthSelect() throws SQLException {
		appointmentDAO.getAll();
		ObservableList<Appointment> appointments = FXCollections.observableArrayList();
		for (Appointment appointment : allAppointments) {
			if (appointment.updateGetStartTime().isAfter(LocalDateTime.now()) && appointment.updateGetEndTime().isBefore(LocalDateTime.now().plusDays(30)))
				appointments.add(appointment);
			appointmentsTable.setItems(appointments);
		}
	}

	public void allSelect() throws SQLException {
		appointmentsTable.setItems(appointmentDAO.getAll());
	}

	public void reportsBtnClick() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ReportsScene.fxml"));
			Stage stage = (Stage) reportsBtn.getScene().getWindow();
			Scene scene = new Scene(loader.load());
			stage.setTitle("Reports");
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void logOutBtnClick() throws IOException {
		Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/LoginScene.fxml")));
		Stage stage = (Stage) logOutBtn.getScene().getWindow();
		stage.setTitle(ResourceBundle.getBundle("Language", Locale.getDefault()).getString("title"));
		stage.setScene(new Scene(root));
		stage.show();
	}
}