package utils;

import DAO.JDBC;
import DAO.UserDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Helper functions for users.
 *
 * @author Miguel Guzman
 */
public class UserHelperFunctions {
	/**
	 * Retrieves a list of usernames using a lambda expression
	 * <p>
	 * LAMBDA #3
	 * <p>
	 * After retrieving a list of users from the database, it transforms the list into a list of usernames using the map()
	 * operation and then collecting the strings into a new ObservableList using the collect() operation.
	 * <br>
	 * It reduced the amount of code down from 13 lines to 11 and improved readability.
	 *
	 * @return ObservableList of usernames
	 */
	public static ObservableList<String> getUserNames() {
		UserDAOImpl userDAO = new UserDAOImpl();
		ObservableList<User> userList = FXCollections.observableArrayList();
		try {
			userList.addAll(userDAO.getAll());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return userList.stream()
				.map(User::getUserName)
				.collect(FXCollections::observableArrayList, ObservableList::add, ObservableList::addAll);
	}

	/**
	 * Get user ID from username
	 *
	 * @param userName Username
	 * @return User ID
	 */
	public static int getUserID(String userName) {
		int userID;
		String sqlStatement = "SELECT User_ID FROM users WHERE User_Name = ?";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
			ps.setString(1, userName);
			try (ResultSet result = ps.executeQuery()) {
				result.next();
				userID = result.getInt("User_ID");
				return userID;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Get username from user ID
	 *
	 * @param userID User ID
	 * @return Username
	 */
	public static String getUserName(int userID) {
		String userName = "";
		String sqlStatement = "SELECT User_Name FROM users WHERE User_ID = ?";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
			ps.setInt(1, userID);
			try (ResultSet result = ps.executeQuery()) {
				if (result.next()) {
					userName = result.getString("User_Name");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userName;
	}
}
