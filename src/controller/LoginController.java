package controller;

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

import java.io.IOException;
import java.net.URL;
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
		/**
		 * Get username and password from text fields
		 */
		String userName = userNameTextField.getText();
		String passWord = loginPasswordField.getText();

		/**
		 * After login is successful, load the main scene
		 */
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainScene.fxml"));
		Stage stage = (Stage) loginBtn.getScene().getWindow();
		Scene scene = new Scene(loader.load());
		stage.setTitle("Appointment Scheduler");
		stage.setScene(scene);
		stage.show();
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
