package utils;

import DAO.UserValidation;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class that handles error messages
 *
 * @author Miguel Guzman
 */
public class ErrMsg {
	/**
	 * Variable that holds the resource bundle
	 */
	private static final ResourceBundle rBundle = ResourceBundle.getBundle("Language", Locale.getDefault());
	/**
	 * Variable that holds the error string
	 */
	static final String ERROR = rBundle.getString("error");
	/**
	 * Variable that holds the error alert
	 */
	private static final Alert err = new Alert(Alert.AlertType.ERROR);

	/**
	 * Checks if username or password is empty and shows an alert
	 *
	 * @param userName the username
	 * @param password the password
	 * @return the boolean
	 */
	public static boolean isEmptyField(String userName, String password) {

		err.setTitle(ERROR);
		err.setHeaderText(ERROR);
		if (userName.isEmpty() && password.isEmpty()) {
			err.setContentText(rBundle.getString("usernamePasswordError"));
			err.showAndWait();
			return true;
		} else if (userName.isEmpty()) {
			err.setContentText(rBundle.getString("usernameError"));
			err.showAndWait();
			return true;
		} else if (password.isEmpty()) {
			err.setContentText(rBundle.getString("passwordError"));
			err.showAndWait();
			return true;
		}
		return false;
	}

	/**
	 * Checks if username or password is incorrect and shows an alert
	 *
	 * @param userName the username
	 * @param password the password
	 * @return the boolean
	 * @throws SQLException the sql exception
	 */
	public static boolean isIncorrect(String userName, String password) throws SQLException {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(ERROR);
		alert.setHeaderText(ERROR);
		if (UserValidation.validateUserID(userName, password) == -1) {
			alert.setContentText(rBundle.getString("usernamePasswordIncorrect"));
			alert.showAndWait();
			return true;
		}
		return false;
	}


	/**
	 * Simple alert that shows an error message
	 *
	 * @param type    the type
	 * @param message the message
	 */
	public static void errorAlert(String type, String message) {
		err.setTitle("Error");
		err.setContentText(type);
		err.setHeaderText(message);
		err.showAndWait();
	}
}
