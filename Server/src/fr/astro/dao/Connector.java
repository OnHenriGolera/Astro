package src.fr.astro.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

	// Connection information
	private static String filePath = "~/bdd_astro";
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
			try {
				connect = DriverManager.getConnection(url, user, passwd);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connect;
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
