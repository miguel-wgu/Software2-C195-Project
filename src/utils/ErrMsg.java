package utils;

import DAO.UserValidation;
import javafx.scene.control.Alert;

import java.sql.SQLException;

/**
 * Class that handles error messages
 *
 * @author Miguel Guzman
 */
public class ErrMsg {
	private static final Alert err = new Alert(Alert.AlertType.ERROR);

	/**
	 * Checks if username or password is empty and shows an alert
	 *
	 * @param userName the username
	 * @param password the password
	 * @return the boolean
	 */
	public static boolean isEmptyField(String userName, String password) {
		err.setTitle("Error");
		err.setHeaderText("Error");
		try {
			if (userName.isEmpty() && password.isEmpty()) {
				err.setContentText("Username and password cannot be empty");
				err.showAndWait();
				return true;
			} else if (userName.isEmpty()) {
				err.setContentText("Username cannot be empty");
				err.showAndWait();
				return true;
			} else if (password.isEmpty()) {
				err.setContentText("Password cannot be empty");
				err.showAndWait();
				return true;
			}
		} catch (NullPointerException e) {
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
	public static boolean isIncorrect(String userName, String password) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Error");
		try {
			if (UserValidation.validateUserID(userName, password) == -1) {
				alert.setContentText("Username or password is incorrect");
				alert.showAndWait();
				return true;
			}
		} catch (SQLException e) {
		}
		return false;
	}
}
