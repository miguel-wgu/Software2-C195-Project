package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * The Java Database Connectivity class.
 *
 * @author Miguel Guzman
 */
public abstract class JDBC {
	/**
	 * The constant protocol.
	 */
	private static final String protocol = "jdbc";
	/**
	 * The constant vendor.
	 */
	private static final String vendor = ":mysql:";
	/**
	 * The constant location.
	 */
	private static final String location = "//localhost/";
	/**
	 * The constant databaseName.
	 */
	private static final String databaseName = "client_schedule";
	/**
	 * The constant jdbcUrl.
	 */
	private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
	/**
	 * The constant driver.
	 */
	private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
	/**
	 * The constant userName.
	 */
	private static final String userName = "sqlUser"; // Username
	/**
	 * The constant password.
	 */
	private static final String password = "Passw0rd!"; // Password
	/**
	 * The constant connection.
	 */
	public static Connection connection;  // Connection Interface

	/**
	 * Open connection.
	 */
	public static void openConnection() {
		try {
			Class.forName(driver); // Locate Driver
			connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
			System.out.println("Connection successful!");
		} catch (Exception e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	/**
	 * Close connection.
	 */
	public static void closeConnection() {
		try {
			connection.close();
			System.out.println("Connection closed!");
		} catch (Exception e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
}
