package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Helper functions for the application.
 * <br><br>
 * Includes a few lambda functions.
 * <br><br>
 *
 * @author Miguel Guzman
 */
public class HelperFunctions {

	/**
	 * Returns to the main screen.
	 *
	 * @param actionEvent the action event
	 * @throws IOException the io exception
	 */
	public static void goToMain(ActionEvent actionEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(HelperFunctions.class.getResource("/view/MainScene.fxml"));
		Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
		Scene scene = new Scene(loader.load());
		stage.setTitle("Appointment Scheduler");
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Returns a list of business hours in EST.
	 *
	 * @return the business hours
	 */
	public static ObservableList<LocalTime> getBusinessHours() {
		ObservableList<LocalTime> businessHours = FXCollections.observableArrayList();
		LocalTime start = LocalTime.of(8, 0);
		LocalTime end = LocalTime.of(22, 0);
		ZoneId EST = ZoneId.of("America/New_York");
		LocalDate today = LocalDate.now();
		while (start.isBefore(end)) {
			LocalDateTime ldt = LocalDateTime.of(today, start);
			ZonedDateTime zdt = ZonedDateTime.of(ldt, EST);
			businessHours.add(zdt.withZoneSameInstant(ZoneId.systemDefault()).toLocalTime());
			start = start.plusMinutes(15);
		}
		return businessHours;
	}

	/**
	 * Formats the start and end times for the appointment.
	 *
	 * @param time the time
	 * @return the local date time
	 */
	public static LocalDateTime startEndFormatter(String time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return LocalDateTime.parse(time, formatter);
	}
}