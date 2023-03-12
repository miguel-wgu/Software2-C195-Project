package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

public class HelperFunctions {
	public static void goToMain(ActionEvent actionEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(HelperFunctions.class.getResource("/view/MainScene.fxml"));
		Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
		Scene scene = new Scene(loader.load());
		stage.setTitle("Appointment Scheduler");
		stage.setScene(scene);
		stage.show();
	}
}
