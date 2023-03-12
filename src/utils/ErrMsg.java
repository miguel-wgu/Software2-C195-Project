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
	private static final Alert err = new Alert(Alert.AlertType.ERROR);
	// variable to store ResourceBundle.getBundle("utils/Login", Locale.FRANCE).getString
	private static final ResourceBundle rBundle = ResourceBundle.getBundle("Language", Locale.FRANCE);

	/**
	 * Checks if username or password is empty and shows an alert
	 *
	 * @param userName the username
	 * @param password the password
	 * @return the boolean
	 */
	public static boolean isEmptyField(String userName, String password) {
		err.setTitle(rBundle.getString("error"));
		err.setHeaderText(rBundle.getString("error"));
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
	 */
	public static boolean isIncorrect(String userName, String password) throws SQLException {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(rBundle.getString("error"));
		alert.setHeaderText(rBundle.getString("error"));
		if (UserValidation.validateUserID(userName, password) == -1) {
			alert.setContentText(rBundle.getString("usernamePasswordIncorrect"));
			alert.showAndWait();
			return true;
		}
		return false;
	}
}
