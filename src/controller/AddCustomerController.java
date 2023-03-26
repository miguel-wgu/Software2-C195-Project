package controller;

import DAO.CustomerDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Customer;
import utils.HelperFunctions;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * The type Add customer controller.
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
	 * The Postal code text field.
	 */
	@FXML
	private TextField postalCodeTextField;
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
	 * The Save btn.
	 */
	@FXML
	private Button saveBtn; // TODO: Delete if not needed
	/**
	 * The Cancel btn.
	 */
	@FXML
	private Button cancelBtn; // TODO: Delete if not needed

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		countryCB.setItems(HelperFunctions.getCountryNames());
		try {
			customerIDTextField.setText(String.valueOf(HelperFunctions.getNextCustomerID()));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Save btn click.
	 *
	 * @param actionEvent the action event
	 */
	public void saveBtnClick(ActionEvent actionEvent) throws SQLException, IOException {
		CustomerDAOImpl addCustomer = new CustomerDAOImpl();
		int customerID = HelperFunctions.getNextCustomerID();
//		int divisionID = Integer.parseInt(customerIDTextField.getText());
		int divisionID = HelperFunctions.getDivisionID(divisionCB.getValue());
		Customer customer = new Customer(customerID, customerNameTextField.getText(), addressTextField.getText(), postalCodeTextField.getText(), phoneNumberTextField.getText(), divisionID, divisionCB.getValue());
		addCustomer.insert(customer);
		HelperFunctions.goToMain(actionEvent);
	}

	/**
	 * Cancel btn click.
	 *
	 * @param actionEvent the action event
	 * @throws IOException the io exception
	 */
	public void cancelBtnClick(ActionEvent actionEvent) throws IOException {
		HelperFunctions.goToMain(actionEvent);
	}

	public void countryCBSelect() {
		int countryID = HelperFunctions.getCountryID(countryCB.getValue());
		divisionCB.setItems(HelperFunctions.getDivisions(countryID));
	}
}
