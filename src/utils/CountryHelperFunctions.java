package utils;

import DAO.CountryDAOImpl;
import DAO.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Helper functions for countries.
 *
 * @author Miguel Guzman
 */
public class CountryHelperFunctions {
	/**
	 * Returns a list of all country names in the database.
	 *
	 * @return ObservableList<String>
	 */
	public static ObservableList<String> getCountryNames() {
		CountryDAOImpl country = new CountryDAOImpl();
		ObservableList<Country> countryList = FXCollections.observableArrayList();
		try {
			countryList.addAll(country.getAll());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		ObservableList<String> countries = FXCollections.observableArrayList();
		for (Country c : countryList) {
			countries.add(c.getCountryName());
		}
		return countries;
	}

	/**
	 * Returns a country's ID based on its name.
	 *
	 * @param countryName Country name
	 * @return int
	 */
	public static int getCountryID(String countryName) {
		int countryID;
		String sqlStatement = "SELECT Country_ID FROM countries WHERE Country = ?";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
			ps.setString(1, countryName);
			try (ResultSet result = ps.executeQuery()) {
				if (result.next()) {
					countryID = result.getInt("Country_ID");
					return countryID;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * Returns a country's name based on the customer's ID.
	 *
	 * @param customerID Customer ID
	 * @return String
	 */
	public static String getCountry(int customerID) {
		String countryName = "";
		String sqlStatement = "SELECT Country FROM countries WHERE Country_ID = (SELECT Country_ID FROM first_level_divisions WHERE Division_ID = (SELECT Division_ID FROM customers WHERE Customer_ID = ?))";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
			ps.setInt(1, customerID);
			try (ResultSet result = ps.executeQuery()) {
				if (result.next()) {
					countryName = result.getString("Country");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return countryName;
	}
}
