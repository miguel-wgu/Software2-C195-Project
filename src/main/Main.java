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

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("utils/Login", Locale.getDefault());
		Parent root = FXMLLoader.load(getClass().getResource("/view/LoginScene.fxml"));
		primaryStage.setTitle(resourceBundle.getString("loginTitle"));
		primaryStage.setScene(new Scene(root, 488.0, 375));
		primaryStage.show();
	}

	public static void main(String[] args) throws SQLException {
		JDBC.openConnection();
		// Print all user names
		UserValidation.getUsers().forEach(System.out::println);
		launch(args);
		JDBC.closeConnection();
	}
}
