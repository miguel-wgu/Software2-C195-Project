package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserValidation extends User {
	public UserValidation(int userID, String userName, String password) {
		super(userID, userName, password);
	}

	public static int validateUserID(String userName, String password) throws SQLException {
		try {
			String sqlStatement = "SELECT * FROM users WHERE User_Name = ? AND Password = ?";
			PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement);
			ps.setString(1, userName);
			ps.setString(2, password);
			ps.execute();
			ResultSet result = ps.getResultSet();
			result.next();
			if (result.getRow() == 1) {
				System.out.println("Successfully logged in as " + userName);
				return result.getInt("User_ID");
			}
			else{
				System.out.println("Error: " + "Invalid username or password");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return -1;
	}

	public static ObservableList<User> getUsers() throws SQLException {
		ObservableList<User> users = FXCollections.observableArrayList();
		String sqlStatement = "SELECT * FROM users";
		PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement);
		ResultSet result = ps.executeQuery();
		while (result.next()) {
			int userID = result.getInt("User_ID");
			String userName = result.getString("User_Name");
			String password = result.getString("Password");
			User user = new User(userID, userName, password);
			users.add(user);
		} return users;

//		try {
//			String sqlStatement = "SELECT * FROM users";
//			PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement);
//			ps.execute();
//			ResultSet result = ps.getResultSet();
//			while (result.next()) {
//				int userID = result.getInt("User_ID");
//				String userName = result.getString("User_Name");
//				String password = result.getString("Password");
//				User user = new User(userID, userName, password);
//				users.add(user);
//			}
//		} catch (Exception e) {
//			System.out.println("Error: " + e.getMessage());
//		}
//		return users;
	}
}
