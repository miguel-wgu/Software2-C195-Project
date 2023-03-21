package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements DAO<User> {
	@Override
	public User get(int id) throws SQLException {
		return null;
	}

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

	@Override
	public void insert(User user) throws SQLException {
		// Not implemented
	}

	@Override
	public void update(User user) throws SQLException {
		// Not implemented
	}

	@Override
	public void delete(User user) throws SQLException {
		// Not implemented
	}
}
