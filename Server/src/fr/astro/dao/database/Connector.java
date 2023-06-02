package src.fr.astro.dao.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

	// Connection information
	private static String dbPath = System.getProperty("user.home") + "/bdd_astro";
	private static String filePath = dbPath;
	private static String url = "jdbc:h2:" + filePath;
	private static String user = "astro-user";
	private static String passwd = "";

	// Instance
	private static Connection connect;

	/**
	 * Return the instance of Connector
	 * Create it if it doesn't exist
	 * 
	 * @return the instance of Connector
	 */
	public static Connection getInstance() {
		if (connect == null) {
			return getInstance(dbPath);
		}
		return connect;
	}

	/**
	 * Return the instance of Connector with a specific path
	 * Create it if it doesn't exist
	 * 
	 * @param newPath
	 * @return the instance of Connector
	 */
	public static Connection getInstance(String newPath) {
		if (connect == null) {
			try {
				filePath = newPath;
				url = "jdbc:h2:" + filePath;
				connect = DriverManager.getConnection(url, user, passwd);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connect;
	}

	/**
	 * Close the connection
	 * 
	 * @return void
	 */
	public static void close() {
		try {
			connect.close();

			connect = null;
			filePath = dbPath;
			url = "jdbc:h2:" + filePath;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the path of the database
	 * 
	 * @return the path of the database
	 */
	public static String getFilePath() {
		return filePath;
	}

}
