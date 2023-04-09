package controller;

import DAO.ReportsDaoImpl;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;
import model.Report;
import utils.ContactHelperFunctions;
import utils.HelperFunctions;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Controller class for ReportsScene.fxml.
 * <br><br>
 * Provides functionality for the reports screen.
 *
 * @author Miguel Guzman
 */
public class ReportsController implements Initializable {
	/**
	 * The Customer appointment table view.
	 */
	@FXML
	private TableView<Appointment> custApptTableView;
	/**
	 * The Customer appointment ID column.
	 */
	@FXML
	private TableColumn<Object, Object> rep1ApptIDCol;
	/**
	 * Report 1 title column.
	 */
	@FXML
	private TableColumn<Object, Object> rep1TitleCol;
	/**
	 * Report 1 description column.
	 */
	@FXML
	private TableColumn<Object, Object> req1DescriptionCol;
	/**
	 * Report 1 type column.
	 */
	@FXML
	private TableColumn<Object, Object> rep1TypeCol;
	/**
	 * Report 1 start column.
	 */
	@FXML
	private TableColumn<Appointment, LocalDate> rep1StartCol;
	/**
	 * Report 1 end column.
	 */
	@FXML
	private TableColumn<Object, Object> rep1EndCol;
	/**
	 * Report 1 customer ID column.
	 */
	@FXML
	private TableColumn<Object, Object> rep1CustIDCol;

	/**
	 * The Month type table view.
	 */
	@FXML
	private TableView<Report> monthTypeTableView;
	/**
	 * Report 2 month column
	 */
	@FXML
	private TableColumn<Report, String> rep2MonthCol;
	/**
	 * Report 2 type column
	 */
	@FXML
	private TableColumn<Report, String> rep2TypeCol;
	/**
	 * Report 2 total column
	 */
	@FXML
	private TableColumn<Report, String> rep2TotalCol;

	/**
	 * The Country division table view.
	 */
	@FXML
	private TableView<Report> countryDivisionTableView;
	/**
	 * Report 3 country column
	 */
	@FXML
	private TableColumn<Report, String> rep3CountryCol;
	/**
	 * Report 3 division column
	 */
	@FXML
	private TableColumn<Report, String> rep3DivisionCol;
	/**
	 * Report 3 total column
	 */
	@FXML
	private TableColumn<Report, String> rep3TotalCol;

	/**
	 * The contact name combo box.
	 */
	@FXML
	private ComboBox<String> contactNameCB;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		try {
			displayCustApptReport();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		displayMonthTypeReport();
		customersPerDivisionReport();
	}

	/**
	 * Display customer appointment report.
	 *
	 * @throws SQLException the sql exception
	 */
	public void displayCustApptReport() throws SQLException {
		contactNameCB.setItems(ContactHelperFunctions.getContactNames());
	}

	/**
	 * Display month and appointment type report.
	 */
	public void displayMonthTypeReport() {
		ReportsDaoImpl reportsDao = new ReportsDaoImpl();
		ObservableList<Report> report2;
		report2 = reportsDao.getAppointmentTypesByMonth();
		rep2MonthCol.setCellValueFactory(new PropertyValueFactory<>("arg1"));
		rep2TypeCol.setCellValueFactory(new PropertyValueFactory<>("arg2"));
		rep2TotalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
		monthTypeTableView.setItems(report2);
	}

	/**
	 * Generates a list of appointments for a selected contact.
	 */
	public void contactCBSelect() {
		int contactID = ContactHelperFunctions.getContactID(contactNameCB.getValue());
		ReportsDaoImpl reportsDao = new ReportsDaoImpl();
		ObservableList<Appointment> report1;
		report1 = reportsDao.getAppointmentByContactId(contactID);
		rep1ApptIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
		rep1TitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
		req1DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
		rep1TypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
		rep1StartCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
		rep1EndCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
		rep1CustIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
		custApptTableView.setItems(report1);
	}

	/**
	 * Display customers per division report.
	 */
	public void customersPerDivisionReport() {
		ReportsDaoImpl reportsDao = new ReportsDaoImpl();
		ObservableList<Report> report3;
		report3 = reportsDao.getAppointmentByDivision();
		rep3CountryCol.setCellValueFactory(new PropertyValueFactory<>("arg1"));
		rep3DivisionCol.setCellValueFactory(new PropertyValueFactory<>("arg2"));
		rep3TotalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
		countryDivisionTableView.setItems(report3);
	}

	/**
	 * Cancel on click and return to main screen.
	 *
	 * @param actionEvent The action event
	 * @throws IOException The io exception
	 */
	public void cancelReportsBtnClick(ActionEvent actionEvent) throws IOException {
		HelperFunctions.goToMain(actionEvent);
	}
}