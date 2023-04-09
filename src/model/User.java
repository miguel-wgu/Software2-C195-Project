package model;

/**
 * The User class
 *
 * @author Miguel Guzman
 */
public class User {
	/**
	 * The User ID.
	 */
	private int userID;
	/**
	 * The Username.
	 */
	private String userName;
	/**
	 * The Password.
	 */
	private String password;

	/**
	 * Instantiates a new User.
	 *
	 * @param userID   the user id
	 * @param userName the username
	 * @param passWord the password
	 */
	public User(int userID, String userName, String passWord) {
		this.userID = userID;
		this.userName = userName;
		this.password = passWord;
	}

	/**
	 * Gets user id.
	 *
	 * @return the user id
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * Sets user id.
	 *
	 * @param userID the user id
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * Gets username.
	 *
	 * @return the username
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets username.
	 *
	 * @param userName the username
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets password.
	 *
	 * @param password the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
