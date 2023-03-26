package controller;

import DAO.CustomerDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Customer;
import utils.HelperFunctions;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateCustomerController implements Initializable {
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


	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		selectedCustomer = MainController.getSelectedCustomer();
//		String customerName = HelperFunctions.getCustomerName();
		countryCB.setItems(HelperFunctions.getCountryNames());

		customerIDTextField.setText(String.valueOf(selectedCustomer.getCustomerID()));
		customerNameTextField.setText(selectedCustomer.getCustomerName());
		phoneNumberTextField.setText(selectedCustomer.getPhone());
		addressTextField.setText(selectedCustomer.getAddress());
		countryCB.setValue(HelperFunctions.getCountry(selectedCustomer.getCustomerID()));
		divisionCB.setValue(selectedCustomer.getDivisionName());
		postalCodeTextField.setText(selectedCustomer.getPostalCode());
	}

	public void cancelBtnClick(ActionEvent actionEvent) throws IOException {
		HelperFunctions.goToMain(actionEvent);
	}

	public void updateBtnClick(ActionEvent actionEvent) throws SQLException, IOException {
		CustomerDAOImpl updatedCustomer = new CustomerDAOImpl();
		selectedCustomer.setCustomerName(customerNameTextField.getText());
		selectedCustomer.setPhone(phoneNumberTextField.getText());
		selectedCustomer.setAddress(addressTextField.getText());
		selectedCustomer.setDivisionName(divisionCB.getValue().toString());
		selectedCustomer.setPostalCode(postalCodeTextField.getText());
		updatedCustomer.update(selectedCustomer);
		HelperFunctions.goToMain(actionEvent);
	}

	public void updateCountryCBSelect(ActionEvent actionEvent) {
	}
}
