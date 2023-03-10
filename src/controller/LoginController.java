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
		// Display timezone country and state in login screen
//		timezoneLabel.setText(Locale.getDefault().getDisplayName() + " " + Locale.getDefault().getCountry() );

		ZoneId zoneId = ZoneId.systemDefault();
		timezoneLabel.setText(zoneId.toString());

		resourceBundle = ResourceBundle.getBundle("utils/Login", Locale.FRANCE);
		schedulerLoginLabel.setText(resourceBundle.getString("loginTitle"));
		userNameTextField.setPromptText(resourceBundle.getString("usernameText"));
		loginPasswordField.setPromptText(resourceBundle.getString("passwordText"));
		loginBtn.setText(resourceBundle.getString("loginButton"));
		clearLoginBtn.setText(resourceBundle.getString("clearButton"));
		currentLocationLabel.setText(resourceBundle.getString("currentLocation"));
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
				 * Validate user ID
				 */
				int validUserID = UserValidation.validateUserID(userName, password);
				System.out.println("validUserID = " + validUserID);

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
