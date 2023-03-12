package DAO;

import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type User validation.
 */
public abstract class UserValidation extends User {
	/**
	 * Instantiates a new User validation.
	 *
	 * @param userID   the user id
	 * @param userName the username
	 * @param password the password
	 */
	public UserValidation(int userID, String userName, String password) {
		super(userID, userName, password);
	}

	/**
	 * Validate user id int.
	 *
	 * @param userName the username
	 * @param password the password
	 * @return the int
	 * @throws SQLException the sql exception
	 */
	public static int validateUserID(String userName, String password) throws SQLException {
		String sqlStatement = "SELECT * FROM users WHERE User_Name = ? AND Password = ?";
		PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement);
		ps.setString(1, userName);
		ps.setString(2, password);
		ps.execute();
		ResultSet result = ps.getResultSet();
		result.next();
		if (result.getRow() == 1) {
			System.out.println("Successfully logged in as " + userName + " this message is from UserValidation.java");
			return result.getInt("User_ID");
		}
		return -1;
	}
}
