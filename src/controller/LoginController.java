package controller;

import DAO.UserValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.ErrMsg;
import utils.HelperFunctions;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Miguel Guzman
 */
public class LoginController implements Initializable {

	@FXML
	private TextField userNameTextField;
	@FXML
	private PasswordField loginPasswordField;
	@FXML
	private Button loginBtn;
	@FXML
	private Button clearLoginBtn;
	@FXML
	private Label schedulerLoginLabel;
	@FXML
	private Label timezoneLabel;
	@FXML
	private Label currentLocationLabel;


	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		ZoneId zoneId = ZoneId.systemDefault();
		timezoneLabel.setText(zoneId.toString());
		ResourceBundle rBundle = ResourceBundle.getBundle("Language", Locale.FRANCE);
		schedulerLoginLabel.setText(rBundle.getString("loginTitle"));
		userNameTextField.setPromptText(rBundle.getString("usernameText"));
		loginPasswordField.setPromptText(rBundle.getString("passwordText"));
		loginBtn.setText(rBundle.getString("loginButton"));
		clearLoginBtn.setText(rBundle.getString("clearButton"));
		currentLocationLabel.setText(rBundle.getString("currentLocation"));
	}

	/**
	 * Login btn click.
	 *
	 * @param actionEvent the action event
	 * @throws IOException the io exception
	 */
	public void loginBtnClick(ActionEvent actionEvent) throws IOException, SQLException {
		/**
		 * Get username and password from text fields
		 */
		String userName = userNameTextField.getText();
		String password = loginPasswordField.getText();

		// check for empty string, if false start MainScene
		try {
			if (!ErrMsg.isEmptyField(userName, password) && !ErrMsg.isIncorrect(userName, password)) {
				/**
				 * After login is successful, load the main scene
				 */
				HelperFunctions.goToMain(actionEvent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Clear login btn click.
	 *
	 * @param actionEvent the action event
	 */
	public void clearLoginBtnClick(ActionEvent actionEvent) {
		userNameTextField.setText("");
		loginPasswordField.setText("");
	}
}
