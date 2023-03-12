package main;

import DAO.UserValidation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import DAO.JDBC;

import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The Main class.
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/view/LoginScene.fxml"));
		primaryStage.setTitle(ResourceBundle.getBundle("Language", Locale.getDefault()).getString("title"));
		primaryStage.setScene(new Scene(root, 488.0, 375));
		primaryStage.show();
	}

	public static void main(String[] args) throws SQLException {
		JDBC.openConnection();
		launch(args);
		JDBC.closeConnection();
	}
}
