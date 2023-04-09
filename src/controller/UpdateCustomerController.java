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
import utils.DivisionHelperFunctions;
import utils.HelperFunctions;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Controller class for UpdateCustomerScene.fxml.
 * <br><br>
 * Provides functionality for the update customer screen.
 *
 * @author Miguel Guzman
 */
public class UpdateCustomerController implements Initializable {
	/**
	 * The Customer DAO.
	 */
	CustomerDAOImpl updatedCustomerDAO = new CustomerDAOImpl();
	/**
	 * The selected customer.
	 */
	private Customer selectedCustomer;
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
	 * The phone number text field.
	 */
	@FXML
	private TextField phoneNumberTextField;
	/**
	 * The address text field.
	 */
	@FXML
	private TextField addressTextField;
	/**
	 * The country combo box.
	 */
	@FXML
	private ComboBox<String> countryCB;
	/**
	 * The division combo box.
	 */
	@FXML
	private ComboBox<String> divisionCB;
	/**
	 * The postal code text field.
	 */
	@FXML
	private TextField postalCodeTextField;
	/**
	 * The customer error label.
	 */
	@FXML
	private Label customerErrLabel;

	/**
	 * Initializes the controller class.
	 *
	 * @param url            The URL.
	 * @param resourceBundle The resource bundle.
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		selectedCustomer = MainController.getSelectedCustomer();
		countryCB.setItems(CountryHelperFunctions.getCountryNames());
		customerIDTextField.setText(String.valueOf(selectedCustomer.getCustomerID()));
		customerNameTextField.setText(selectedCustomer.getCustomerName());
		phoneNumberTextField.setText(selectedCustomer.getPhone());
		addressTextField.setText(selectedCustomer.getAddress());
		countryCB.setValue(CountryHelperFunctions.getCountry(selectedCustomer.getCustomerID()));
		divisionCB.setValue(selectedCustomer.getDivisionName());
		postalCodeTextField.setText(selectedCustomer.getPostalCode());
		int countryID = CountryHelperFunctions.getCountryID(countryCB.getValue());
		divisionCB.setItems(DivisionHelperFunctions.getDivisions(countryID));
	}

	/**
	 * Cancel and go to main screen.
	 *
	 * @param actionEvent The action event.
	 * @throws IOException the io exception.
	 */
	public void cancelBtnClick(ActionEvent actionEvent) throws IOException {
		HelperFunctions.goToMain(actionEvent);
	}

	/**
	 * Update customer and go to main screen.
	 *
	 * @param actionEvent The action event.
	 * @throws SQLException The sql exception.
	 * @throws IOException  The io exception.
	 */
	public void updateBtnClick(ActionEvent actionEvent) throws SQLException, IOException {
		if (!emptyField()) {
			selectedCustomer.setCustomerName(customerNameTextField.getText());
			selectedCustomer.setPhone(phoneNumberTextField.getText());
			selectedCustomer.setAddress(addressTextField.getText());
			selectedCustomer.setPostalCode(postalCodeTextField.getText());
			selectedCustomer.setDivisionName(divisionCB.getValue());
			selectedCustomer.setDivisionID(DivisionHelperFunctions.getDivisionID(divisionCB.getValue()));
			updatedCustomerDAO.update(selectedCustomer);
			HelperFunctions.goToMain(actionEvent);
		} else
			AddCustomerController.customerErr(customerErrLabel, customerNameTextField, phoneNumberTextField, addressTextField, countryCB, divisionCB, postalCodeTextField);
	}

	/**
	 * Check if any field is empty.
	 *
	 * @return true if any field is empty, false otherwise.
	 */
	private boolean emptyField() {
		return customerNameTextField.getText().isEmpty() ||
				phoneNumberTextField.getText().isEmpty() ||
				addressTextField.getText().isEmpty() ||
				countryCB.getValue() == null ||
				divisionCB.getValue() == null ||
				postalCodeTextField.getText().isEmpty();
	}

	/**
	 * Update division combo box.
	 */
	public void updateCountryCBSelect() {
		int countryID = CountryHelperFunctions.getCountryID(countryCB.getValue());
		divisionCB.setItems(DivisionHelperFunctions.getDivisions(countryID));
		divisionCB.setValue(null);
	}
}
