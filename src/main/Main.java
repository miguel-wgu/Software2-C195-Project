package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.JDBC;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/view/LoginScene.fxml"));
		primaryStage.setTitle("Appointment Scheduler Login");
		primaryStage.setScene(new Scene(root, 488.0, 347.0));
		primaryStage.show();
	}

	public static void main(String[] args) {
		JDBC.openConnection();
		launch(args);

	}
}
