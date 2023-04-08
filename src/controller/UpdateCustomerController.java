package controller;

import DAO.CustomerDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Customer;
import utils.HelperFunctions;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateCustomerController implements Initializable {
	CustomerDAOImpl updatedCustomer = new CustomerDAOImpl();
	private Customer selectedCustomer;
	@FXML
	private TextField customerIDTextField;
	@FXML
	private TextField customerNameTextField;
	@FXML
	private TextField phoneNumberTextField;
	@FXML
	private TextField addressTextField;
	@FXML
	private ComboBox<String> countryCB;
	@FXML
	private ComboBox<String> divisionCB;
	@FXML
	private TextField postalCodeTextField;
	@FXML
	private Label customerErrLabel;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		selectedCustomer = MainController.getSelectedCustomer();
		countryCB.setItems(HelperFunctions.getCountryNames());
		customerIDTextField.setText(String.valueOf(selectedCustomer.getCustomerID()));
		customerNameTextField.setText(selectedCustomer.getCustomerName());
		phoneNumberTextField.setText(selectedCustomer.getPhone());
		addressTextField.setText(selectedCustomer.getAddress());
		countryCB.setValue(HelperFunctions.getCountry(selectedCustomer.getCustomerID()));
		divisionCB.setValue(selectedCustomer.getDivisionName());
		postalCodeTextField.setText(selectedCustomer.getPostalCode());
		int countryID = HelperFunctions.getCountryID(countryCB.getValue());
		divisionCB.setItems(HelperFunctions.getDivisions(countryID));
	}

	public void cancelBtnClick(ActionEvent actionEvent) throws IOException {
		HelperFunctions.goToMain(actionEvent);
	}

	public void updateBtnClick(ActionEvent actionEvent) throws SQLException, IOException {
		if (!emptyField()) {
			selectedCustomer.setCustomerName(customerNameTextField.getText());
			selectedCustomer.setPhone(phoneNumberTextField.getText());
			selectedCustomer.setAddress(addressTextField.getText());
			selectedCustomer.setPostalCode(postalCodeTextField.getText());
			selectedCustomer.setDivisionName(divisionCB.getValue());
			selectedCustomer.setDivisionID(HelperFunctions.getDivisionID(divisionCB.getValue()));
			updatedCustomer.update(selectedCustomer);
			HelperFunctions.goToMain(actionEvent);
		} else customerErr();
	}

	private void customerErr() {
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

	private boolean emptyField() {
		return customerNameTextField.getText().isEmpty() ||
				phoneNumberTextField.getText().isEmpty() ||
				addressTextField.getText().isEmpty() ||
				countryCB.getValue() == null ||
				divisionCB.getValue() == null ||
				postalCodeTextField.getText().isEmpty();
	}

	public void updateCountryCBSelect() {
		int countryID = HelperFunctions.getCountryID(countryCB.getValue());
		divisionCB.setItems(HelperFunctions.getDivisions(countryID));
		divisionCB.setValue(null);
	}
}
