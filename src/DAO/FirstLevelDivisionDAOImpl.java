package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FirstLevelDivisionDAOImpl implements DAO<FirstLevelDivision> {
	@Override
	public FirstLevelDivision get(int id) throws SQLException {
		String sqlStatement = "SELECT * FROM first_level_divisions WHERE Division_ID = ?";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement)) {
			ps.setInt(1, id);
			try (ResultSet result = ps.executeQuery()) {
				if (result.next()) {
					int divisionID = result.getInt("Division_ID");
					String divisionName = result.getString("Division");
					int countryID = result.getInt("Country_ID");
					return new FirstLevelDivision(divisionID, divisionName, countryID);
				}
			}
		}
		throw new SQLException("No first level division with ID " + id + " found in database.");
	}

	@Override
	public ObservableList<FirstLevelDivision> getAll() throws SQLException {
		ObservableList<FirstLevelDivision> firstLevelDivisions = FXCollections.observableArrayList();
		String sqlStatement = "SELECT * FROM first_level_divisions";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement);
		     ResultSet result = ps.executeQuery()) {
			while (result.next()) {
				int divisionID = result.getInt("Division_ID");
				String divisionName = result.getString("Division");
				int countryID = result.getInt("Country_ID");
				firstLevelDivisions.add(new FirstLevelDivision(divisionID, divisionName, countryID));
			}
			return firstLevelDivisions;
		}
	}

	// Return first level division ID
	public ObservableList<Integer> getDivisionID() throws SQLException {
		ObservableList<Integer> divisionID = FXCollections.observableArrayList();
		String sqlStatement = "SELECT Division_ID FROM first_level_divisions";
		try (PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement);
		     ResultSet result = ps.executeQuery()) {
			while (result.next()) {
				int divisionID1 = result.getInt("Division_ID");
				divisionID.add(divisionID1);
			}
			return divisionID;
		}
	}


	@Override
	public void insert(FirstLevelDivision firstLevelDivision) throws SQLException {

	}

	@Override
	public void update(FirstLevelDivision firstLevelDivision) throws SQLException {

	}

	@Override
	public void delete(FirstLevelDivision firstLevelDivision) throws SQLException {

	}
}
