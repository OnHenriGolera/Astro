package src.fr.astro.dao;

import java.io.File;
import java.sql.*;

/**
 * Initializer
 * 
 * @see Connector
 */
public class Initializer {

	/**
	 * Initialize the database
	 */
	public static void Init() {

		Connection connect = Connector.getInstance();

		try {

			PreparedStatement preparedStatement;

			// Create the table "Person" : id, name, surname
			preparedStatement = connect.prepareStatement(
					"CREATE TABLE IF NOT EXISTS Person (personId INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), surname VARCHAR(255))");
			preparedStatement.executeUpdate();

			// Create the table "Role" : id, name, accessLevel
			preparedStatement = connect.prepareStatement(
					"CREATE TABLE IF NOT EXISTS Role (roleId INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), accessLevel INT)");
			preparedStatement.executeUpdate();

			// Create the table "User" : userId, personId, roleId, password
			preparedStatement = connect.prepareStatement(
					"CREATE TABLE IF NOT EXISTS User (userId INT PRIMARY KEY AUTO_INCREMENT, personId INT, roleId INT, password VARCHAR(255), FOREIGN KEY (personId) REFERENCES Person(personId), FOREIGN KEY (roleId) REFERENCES Role(roleId))");
			preparedStatement.executeUpdate();

			// Create the table "Participant" : participantId, personId, category, present
			preparedStatement = connect.prepareStatement(
					"CREATE TABLE IF NOT EXISTS Participant (participantId INT PRIMARY KEY AUTO_INCREMENT, personId INT, category VARCHAR(255), present BOOLEAN, FOREIGN KEY (personId) REFERENCES Person(personId))");
			preparedStatement.executeUpdate();

			// Create the table "FormulaElement" : formulaElementId, type, numberBefore,
			// numberAfter, description
			preparedStatement = connect.prepareStatement(
					"CREATE TABLE IF NOT EXISTS FormulaElement (formulaElementId INT PRIMARY KEY AUTO_INCREMENT, type VARCHAR(255), numberBefore INT, numberAfter INT, description VARCHAR(255))");
			preparedStatement.executeUpdate();

			// Create the table : "Stage" : stageId, name, formulaElementId
			preparedStatement = connect.prepareStatement(
					"CREATE TABLE IF NOT EXISTS Stage (stageId INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), formulaElementId INT, FOREIGN KEY (formulaElementId) REFERENCES FormulaElement(formulaElementId))");
			preparedStatement.executeUpdate();

			// Create the table : "Formula" : formulaId, name, description
			preparedStatement = connect.prepareStatement(
					"CREATE TABLE IF NOT EXISTS Formula (formulaId INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), description VARCHAR(255))");
			preparedStatement.executeUpdate();

			// Create the table : "Competition" : competitionId, formulaId, name, status,
			// category
			preparedStatement = connect.prepareStatement(
					"CREATE TABLE IF NOT EXISTS Competition (competitionId INT PRIMARY KEY AUTO_INCREMENT, formulaId INT, name VARCHAR(255), status VARCHAR(255), category VARCHAR(255), FOREIGN KEY (formulaId) REFERENCES Formula(formulaId))");
			preparedStatement.executeUpdate();

			// Create the table : "StageList" : stageId, competitionId
			preparedStatement = connect.prepareStatement(
					"CREATE TABLE IF NOT EXISTS StageList (stageId INT, competitionId INT, FOREIGN KEY (stageId) REFERENCES Stage(stageId), FOREIGN KEY (competitionId) REFERENCES Competition(competitionId))");
			preparedStatement.executeUpdate();

			// Create the table : "FormulaElementList" : formulaId, formulaElementId
			preparedStatement = connect.prepareStatement(
					"CREATE TABLE IF NOT EXISTS FormulaElementList (formulaId INT, formulaElementId INT, FOREIGN KEY (formulaId) REFERENCES Formula(formulaId), FOREIGN KEY (formulaElementId) REFERENCES FormulaElement(formulaElementId))");
			preparedStatement.executeUpdate();

		} catch (Exception e) {

			System.out.println("An error occured while initializing the database.");
			e.printStackTrace();

		}

	}

	/**
	 * Drop the database
	 */
	public static void Drop() {

		// Delete the database is destroying the file from Connector
		String filePath = Connector.getFilePath();

		File file = new File(filePath + ".mv.db");
		file.delete();

		System.out.println("Database deleted : " + filePath + ".mv.db");

	}

	/**
	 * Make a copy of the database in a file
	 * 
	 * @param filePath
	 */
	public static void Backup(String filePath) {

		Connection connect = Connector.getInstance();

		try {

			Statement statement = connect.createStatement();

			statement.execute("BACKUP TO '" + filePath + "'");

		} catch (Exception e) {

			System.out.println("An error occured while backing up the database.");
			e.printStackTrace();

		}

	}

	/**
	 * Load a database from a file (replace the current database)
	 * 
	 * @param filePath
	 */
	public static void Load(String filePath) {

		// If the connection is already established, close it
		Connector.close();

		// Load the database
		Connector.getInstance(filePath);

	}
	
	/**
	 * Get the path of the database
	 * 
	 * @return the path of the database
	 */
	public static String getFilePath() {
		return Connector.getFilePath();
	}

}
