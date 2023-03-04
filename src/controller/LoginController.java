package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Miguel Guzman
 */
public class LoginController implements Initializable {

	@FXML private TextField userNameTextField;
	@FXML private PasswordField loginPasswordField;
	@FXML private Button loginBtn;
	@FXML private Button clearLoginBtn;
	@FXML private Label schedulerLoginLabel;


	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		Locale locale = Locale.getDefault();
//		resourceBundle = ResourceBundle.getBundle("/lang/FR_Login", locale);
//		schedulerLoginLabel.setText(resourceBundle.getString("Login"));

	}

	/**
	 * Login btn click.
	 *
	 * @param actionEvent the action event
	 * @throws IOException the io exception
	 */
	public void loginBtnClick(ActionEvent actionEvent) throws IOException {
		// TODO make button work and login

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
