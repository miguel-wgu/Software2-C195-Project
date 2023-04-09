package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The User DAO.
 * <br><br>
 * Responsible for all database operations related to users.
 *
 * @author Miguel Guzman
 */
public class UserDAOImpl implements DAO<User> {
	/**
	 * Returns all users in the database.
	 *
	 * @return ObservableList<User>
	 * @throws SQLException if the database cannot be accessed.
	 */
	@Override
	public ObservableList<User> getAll() throws SQLException {
		ObservableList<User> users = FXCollections.observableArrayList();
		String sqlStatement = "SELECT * FROM users";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
			try (ResultSet result = ps.executeQuery()) {
				while (result.next()) {
					int userID = result.getInt("User_ID");
					String userName = result.getString("User_Name");
					String password = result.getString("Password");
					User user = new User(userID, userName, password);
					users.add(user);
				}
				return users;
			}
		}
	}

	/**
	 * Get user by id
	 *
	 * @param id the id
	 * @return the user
	 * @throws SQLException if the user does not exist.
	 */
	@Override
	public User get(int id) throws SQLException {
		return null;
	}

	@Override
	public void insert(User user) throws SQLException {
		// Not used
	}

	@Override
	public void update(User user) throws SQLException {
		// Not used
	}

	@Override
	public void delete(User user) throws SQLException {
		// Not used
	}
}
