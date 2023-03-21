package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryDAOImpl implements DAO<Country> {
	@Override
	public Country get(int id) throws SQLException {
		String sqlStatement = "SELECT * FROM countries WHERE Country_ID = ?";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
			ps.setInt(1, id);
			try (ResultSet result = ps.executeQuery()) {
				if (result.next()) {
					int countryID = result.getInt("Country_ID");
					String countryName = result.getString("Country");
					return new Country(countryID, countryName);
				}
			}
		}
		throw new SQLException("No country with ID " + id + " found in database.");
	}

	@Override
	public ObservableList<Country> getAll() throws SQLException {
		ObservableList<Country> countries = FXCollections.observableArrayList();
		String sqlStatement = "SELECT * FROM countries";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement);
		     ResultSet result = ps.executeQuery()) {
			while (result.next()) {
				int countryID = result.getInt("Country_ID");
				String countryName = result.getString("Country");
				countries.add(new Country(countryID, countryName));
			}
			return countries;
		}
	}

	@Override
	public void insert(Country country) throws SQLException {
		// Not used
	}

	@Override
	public void update(Country country) throws SQLException {
		// Not used
	}

	@Override
	public void delete(Country country) throws SQLException {
		// Not used
	}
}
