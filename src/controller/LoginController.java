package controller;

import DAO.AppointmentDAOImpl;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Appointment;
import utils.ErrMsg;
import utils.HelperFunctions;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Controller for LoginScene.fxml
 *
 * @author Miguel Guzman
 *
 * <br><br>
 * This controller is responsible for validating the user login credentials.
 */
public class LoginController implements Initializable {

	/**
	 * Constant to hold the login log file path.
	 */
	private static final String LOGIN_FILE_PATH = "./login_log.txt";
//	private static final String APPT_ALERT = "Appointment Alert";
	/**
	 * Variable to hold the username.
	 */
	private static String userName;
	/**
	 * The username field.
	 */
	@FXML
	private TextField userNameTextField;
	/**
	 * The password field.
	 */
	@FXML
	private PasswordField loginPasswordField;
	/**
	 * The login button.
	 */
	@FXML
	private Button loginBtn;
	/**
	 * The clear button.
	 */
	@FXML
	private Button clearLoginBtn;
	/**
	 * Label displays app name.
	 */
	@FXML
	private Label schedulerLoginLabel;
	/**
	 * Label displays timezone text
	 */
	@FXML
	private Label timezoneLabel;
	/**
	 * Label displays current location text
	 */
	@FXML
	private Label currentLocationLabel;

	/**
	 * Gets username.
	 *
	 * @return the username
	 */
	@FXML
	public static String getUserName() {
		return userName;
	}

	/**
	 * Creates/updates the login log file with the user login attempt.
	 *
	 * @param userName the username
	 * @param password the password
	 * @throws SQLException if there is an error with the database
	 */
	private static void logLogin(String userName, String password) throws SQLException {
		boolean attempt = (!ErrMsg.isEmptyField(userName, password) && !ErrMsg.isIncorrect(userName, password));
		try {
			LocalDateTime local = LocalDateTime.now(ZoneOffset.UTC);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy - HH:mm:ss");
			String formattedTime = local.format(formatter);
			File logFile = new File(LOGIN_FILE_PATH);
			if (!logFile.exists()) {
				boolean wasFileCreated = logFile.getParentFile().mkdirs();
				if (wasFileCreated) {
					wasFileCreated = logFile.createNewFile();
					if (!wasFileCreated)
						throw new RuntimeException("Could not create file: " + logFile.getAbsolutePath());
					else
						throw new RuntimeException("Could not create directory: " + logFile.getParentFile().getAbsolutePath());
				}
			}
			try (FileWriter writer = new FileWriter(logFile, true)) {
				writer.write("User " + userName + " " + (attempt ? "successfully" : "unsuccessfully") + " logged in: " + formattedTime + " UTC\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initializes the login scene with the current timezone and language.
	 *
	 * @param url            the url
	 * @param resourceBundle the resource bundle
	 */
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
	 * @throws SQLException the sql exception
	 */
	public void loginBtnClick(ActionEvent actionEvent) throws SQLException {
		userName = userNameTextField.getText();
		String password = loginPasswordField.getText();
		logLogin(userName, password);

		// check for empty string, if false start MainScene
		try {
			if (!ErrMsg.isEmptyField(userName, password) && !ErrMsg.isIncorrect(userName, password)) {
				HelperFunctions.goToMain(actionEvent);
				checkForAppointment();
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

	// Once the user is validated, display an alert if there is an appointment within 15 minutes of the user's login.
	private void checkForAppointment() throws SQLException {
		AppointmentDAOImpl AppointmentDAO = new AppointmentDAOImpl();
		ObservableList<Appointment> appointments = AppointmentDAO.getAll();
		boolean upcomingAppointment = false;
		ZoneId userTimeZone = ZoneId.systemDefault();
		for (Appointment appointment : appointments) {
			ZonedDateTime start = appointment.updateGetStartTime().atZone(userTimeZone);
			if (start.isBefore(ZonedDateTime.now().plusMinutes(15)) && start.isAfter(ZonedDateTime.now())) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Appointment Alert");
				alert.setHeaderText("You have an appointment within 15 minutes.");
				alert.setContentText("Appointment ID: " + appointment.getAppointmentID() + "\nDate: " + appointment.updateGetStartTime().toLocalDate() + "\nTime: " + appointment.updateGetStartTime().toLocalTime());
				alert.showAndWait();
				upcomingAppointment = true;
			}
			}
			if (!upcomingAppointment) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Appointment Alert");
			alert.setHeaderText("Appointment Alert");
			alert.setContentText("You have no upcoming appointments.");
			alert.showAndWait();
		}
	}
}