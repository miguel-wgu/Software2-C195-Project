package controller;

import DAO.CustomerDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Customer;
import utils.CountryHelperFunctions;
import utils.CustomerHelperFunctions;
import utils.DivisionHelperFunctions;
import utils.HelperFunctions;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Controller class for AddCustomerScene.fxml.
 * <br><br>
 * Provides functionality for the add customer screen.
 *
 * @author Miguel Guzman
 */
public class AddCustomerController implements Initializable {

	/**
	 * The Customer id text field.
	 */
	@FXML
	private TextField customerIDTextField;
	/**
	 * The Customer name text field.
	 */
	@FXML
	private TextField customerNameTextField;
	/**
	 * The Phone number text field.
	 */
	@FXML
	private TextField phoneNumberTextField;
	/**
	 * The Address text field.
	 */
	@FXML
	private TextField addressTextField;
	/**
	 * The Country cb.
	 */
	@FXML
	private ComboBox<String> countryCB;
	/**
	 * The Division cb.
	 */
	@FXML
	private ComboBox<String> divisionCB;
	/**
	 * The Postal code text field.
	 */
	@FXML
	private TextField postalCodeTextField;
	/**
	 * The Save btn.
	 */
	@FXML
	private Label customerErrLabel;

	/**
	 * Error messages for empty fields.
	 *
	 * @param customerErrLabel      The customer err label
	 * @param customerNameTextField The customer name text field
	 * @param phoneNumberTextField  The phone number text field
	 * @param addressTextField      The address text field
	 * @param countryCB             The country cb
	 * @param divisionCB            The division cb
	 * @param postalCodeTextField   The postal code text field
	 */
	public static void customerErr(Label customerErrLabel, TextField customerNameTextField, TextField phoneNumberTextField, TextField addressTextField, ComboBox<String> countryCB, ComboBox<String> divisionCB, TextField postalCodeTextField) {
		customerErrLabel.setText("");
		if (customerNameTextField.getText().isEmpty()) {
			customerErrLabel.setText(customerErrLabel.getText() + "Please enter a name\n");
		}
		if (phoneNumberTextField.getText().isEmpty()) {
			customerErrLabel.setText(customerErrLabel.getText() + "Please enter a number\n");
		}
		if (addressTextField.getText().isEmpty()) {
			customerErrLabel.setText(customerErrLabel.getText() + "Please enter an address\n");
		}
		if (countryCB.getValue() == null) {
			customerErrLabel.setText(customerErrLabel.getText() + "Please select a country\n");
		}
		if (divisionCB.getValue() == null) {
			customerErrLabel.setText(customerErrLabel.getText() + "Please select a division\n");
		}
		if (postalCodeTextField.getText().isEmpty()) {
			customerErrLabel.setText(customerErrLabel.getText() + "Please enter a postal code");
		}
	}

	/**
	 * Initializes the controller class.
	 *
	 * @param url            The URL
	 * @param resourceBundle The resource bundle
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		countryCB.setItems(CountryHelperFunctions.getCountryNames());
		try {
			customerIDTextField.setText(String.valueOf(CustomerHelperFunctions.getNextCustomerID()));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Method to save a new customer to the database.
	 *
	 * @param actionEvent The action event.
	 */
	public void saveBtnClick(ActionEvent actionEvent) throws IOException {
		try {
			CustomerDAOImpl addCustomer = new CustomerDAOImpl();
			int customerID = CustomerHelperFunctions.getNextCustomerID();
			int divisionID = DivisionHelperFunctions.getDivisionID(divisionCB.getValue());
			Customer customer = new Customer(customerID, customerNameTextField.getText(), addressTextField.getText(), postalCodeTextField.getText(), phoneNumberTextField.getText(), divisionID, divisionCB.getValue());
			addCustomer.insert(customer);
			HelperFunctions.goToMain(actionEvent);
		} catch (SQLException e) {
			customerErr(customerErrLabel, customerNameTextField, phoneNumberTextField, addressTextField, countryCB, divisionCB, postalCodeTextField);
		}
	}

	/**
	 * Cancel btn click.
	 *
	 * @param actionEvent The action event
	 * @throws IOException The io exception
	 */
	public void cancelBtnClick(ActionEvent actionEvent) throws IOException {
		HelperFunctions.goToMain(actionEvent);
	}

	/**
	 * Loads the division combo box based on the selected country.
	 */
	public void countryCBSelect() {
		int countryID = CountryHelperFunctions.getCountryID(countryCB.getValue());
		divisionCB.setItems(DivisionHelperFunctions.getDivisions(countryID));
		divisionCB.setValue(null);
	}
}