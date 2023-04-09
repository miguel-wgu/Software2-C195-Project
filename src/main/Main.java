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
 *
 * @author Miguel Guzman
 */
public class Main extends Application {
	/**
	 * The main method that launches the application and opens the database connection.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		JDBC.openConnection();
		launch(args);
		JDBC.closeConnection();
	}

	/**
	 * Loads the login screen and sets the stage.
	 *
	 * @param primaryStage the primary stage
	 * @throws Exception if the login screen cannot be loaded.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/LoginScene.fxml")));
		primaryStage.setTitle(ResourceBundle.getBundle("Language", Locale.getDefault()).getString("title"));
		primaryStage.setScene(new Scene(root, 488.0, 375));
		primaryStage.show();
	}
}
