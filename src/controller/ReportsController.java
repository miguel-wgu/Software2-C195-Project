package controller;

import DAO.ReportsDaoImpl;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;
import model.Report;
import utils.HelperFunctions;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {
	@FXML
	private TableView<Report> monthTypeTableView;
	@FXML
	private TableColumn<Report, String> rep2MonthCol;
	@FXML
	private TableColumn<Report, String> rep2TypeCol;
	@FXML
	private TableColumn<Report, String> rep2TotalCol;


	@FXML
	private TableView<Appointment> custApptTableView;
	@FXML
	private TableColumn<Object, Object> rep1ApptIDCol;
	@FXML
	private TableColumn<Object, Object> rep1TitleCol;
	@FXML
	private TableColumn<Object, Object> req1DescriptionCol;
	@FXML
	private TableColumn<Object, Object> rep1TypeCol;
	@FXML
	private TableColumn<Appointment, LocalDate> rep1StartCol;
	@FXML
	private TableColumn<Object, Object> rep1EndCol;
	@FXML
	private TableColumn<Object, Object> rep1CustIDCol;
	@FXML
	private ComboBox<String> contactNameCB;


	@FXML
	private TableView<Report> countryDivisionTableView;
	@FXML
	private TableColumn<Report, String> rep3CountryCol;
	@FXML
	private TableColumn<Report, String> rep3DivisionCol;
	@FXML
	private TableColumn<Report, String> rep3TotalCol;


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

	public void displayCustApptReport() throws SQLException {
		contactNameCB.setItems(HelperFunctions.getContactNames());
	}

	public void displayMonthTypeReport() {
		ReportsDaoImpl reportsDao = new ReportsDaoImpl();
		ObservableList<Report> report2;
		report2 = reportsDao.getAppointmentTypesByMonth();
		rep2MonthCol.setCellValueFactory(new PropertyValueFactory<>("arg1"));
		rep2TypeCol.setCellValueFactory(new PropertyValueFactory<>("arg2"));
		rep2TotalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
		monthTypeTableView.setItems(report2);
	}

	public void contactCBSelect() {
		int contactID = HelperFunctions.getContactID(contactNameCB.getValue());
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

	public void customersPerDivisionReport() {
		ReportsDaoImpl reportsDao = new ReportsDaoImpl();
//		CustomerDAOImpl customerDAO = new CustomerDAOImpl();
//		ObservableList<Customer> report3;
//		report3 = customerDAO.getAppointmentByDivision();
		ObservableList<Report> report3;
		report3 = reportsDao.getAppointmentByDivision();
		System.out.println(report3);
		rep3CountryCol.setCellValueFactory(new PropertyValueFactory<>("arg1"));
		rep3DivisionCol.setCellValueFactory(new PropertyValueFactory<>("arg2"));
		rep3TotalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
		countryDivisionTableView.setItems(report3);
	}
}