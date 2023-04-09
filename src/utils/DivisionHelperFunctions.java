package utils;

import DAO.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Helper functions for divisions.
 *
 * @author Miguel Guzman
 */
public class DivisionHelperFunctions {

	/**
	 * Returns a list of all divisions based on the ID.
	 *
	 * @param ID The ID of the country.
	 * @return ObservableList<String>
	 */
	public static ObservableList<String> getDivisions(int ID) {
		ObservableList<String> divisionList = FXCollections.observableArrayList();
		String sqlStatement = "SELECT DIVISION FROM first_level_divisions WHERE COUNTRY_ID = ?";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
			ps.setInt(1, ID);
			try (ResultSet result = ps.executeQuery()) {
				while (result.next()) {
					divisionList.add(result.getString("Division"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return divisionList;
	}

	/**
	 * Returns the division ID based on the division name.
	 *
	 * @param divisionName The name of the division.
	 * @return int
	 */
	public static int getDivisionID(String divisionName) {
		int divisionID;
		String sqlStatement = "SELECT Division_ID FROM first_level_divisions WHERE Division = ?";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
			ps.setString(1, divisionName);
			try (ResultSet result = ps.executeQuery()) {
				if (result.next()) {
					divisionID = result.getInt("Division_ID");
					return divisionID;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
