package main;

import DAO.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * The Main class.
 */
public class Main extends Application {
	public static void main(String[] args) {
		JDBC.openConnection();
		launch(args);
		JDBC.closeConnection();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/LoginScene.fxml")));
		primaryStage.setTitle(ResourceBundle.getBundle("Language", Locale.getDefault()).getString("title"));
		primaryStage.setScene(new Scene(root, 488.0, 375));
		primaryStage.show();
	}
}
