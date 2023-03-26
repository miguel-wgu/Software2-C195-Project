package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import utils.ErrMsg;
import utils.HelperFunctions;

import java.net.URL;
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


	private static String userName;

	@FXML
	public static String getUserName() {
		return userName;
	}


	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		ZoneId zoneId = ZoneId.systemDefault();
		timezoneLabel.setText(zoneId.toString());
		ResourceBundle rBundle = ResourceBundle.getBundle("Language", Locale.getDefault());
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
	 */
	public void loginBtnClick(ActionEvent actionEvent) {
		userName = userNameTextField.getText();
		String password = loginPasswordField.getText();

		// check for empty string, if false start MainScene
		try {
			if (!ErrMsg.isEmptyField(userName, password) && !ErrMsg.isIncorrect(userName, password)) {
				HelperFunctions.goToMain(actionEvent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Clear login btn click.
	 */
	public void clearLoginBtnClick() {
		userNameTextField.setText("");
		loginPasswordField.setText("");
	}
}
