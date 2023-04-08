package utils;
import DAO.UserValidation;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Objects;
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

	// Display an error message when adding an appointment in a text area if:
	// 1. No user is selected
	// 2. No customer is selected
	// 3. No contact is selected
	// 4. No type isn't is entered
	// 5. No title is entered
	// 6. No start date is selected
	// 7. No start time is selected
	// 8. No end date is selected
	// 9. No end time is selected
	// 10. No description is entered

	/**
	 * Checks if the appointment is valid
	 * <p>
	 * //	 * @param customerID the customer id
	 * //	 * @param userID     the user id
	 * //	 * @param title      the title
	 * //	 * @param description the description
	 * //	 * @param location   the location
	 * //	 * @param contact    the contact
	 * //	 * @param type       the type
	 * //	 * @param url        the url
	 * //	 * @param start      the start
	 * //	 * @param end        the end
	 * //	 * @return the boolean
	 * //
	 */
//	public static void addApptErr(String userName, Label errLabel) throws IOException {
//		if (userName.isEmpty()) {
//			errLabel.setText("Please select a user");
//		}
//	}
}
