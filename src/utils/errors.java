package utils;

import javafx.scene.control.Alert;

public class errors {
	// Method to check if username or password is empty and show an alert

	public static boolean checkEmpty(String userName, String password) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Error");
		try {
			if (userName.isEmpty() && password.isEmpty()) {
				alert.setContentText("Username and password cannot be empty");
				alert.showAndWait();
				return true;
			} else if (userName.isEmpty()) {
				alert.setContentText("Username cannot be empty");
				alert.showAndWait();
				return true;
			} else if (password.isEmpty()) {
				alert.setContentText("Password cannot be empty");
				alert.showAndWait();
				return true;
			}
		} catch (NullPointerException e) {
			alert.setContentText("Username and password cannot be empty");
			alert.showAndWait();
		}
		return false;
	}
}
