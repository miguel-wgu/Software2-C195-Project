package DAO;

import javafx.collections.ObservableList;

import java.sql.SQLException;

/**
 * Generic interface Dao.
 *
 * @param <T> the type parameter
 */
public interface DAO<T> {
	/**
	 * Get t.
	 *
	 * @param id the id
	 * @return the t
	 * @throws SQLException the sql exception
	 */
	T get(int id) throws SQLException;

	/**
	 * Gets all.
	 *
	 * @return the all
	 * @throws SQLException the sql exception
	 */
	ObservableList<T> getAll() throws SQLException;

	/**
	 * Save.
	 *
	 * @param t the t
	 * @throws SQLException the sql exception
	 */
	void save(T t) throws SQLException;

	/**
	 * Insert.
	 *
	 * @param t the t
	 * @throws SQLException the sql exception
	 */
	void insert(T t) throws SQLException;

	/**
	 * Update.
	 *
	 * @param t the t
	 * @throws SQLException the sql exception
	 */
	void update(T t) throws SQLException;

	/**
	 * Delete.
	 *
	 * @param t the t
	 */
	void delete(T t) throws SQLException;

}
