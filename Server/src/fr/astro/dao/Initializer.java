package src.fr.astro.dao;

import java.sql.*;

public class Initializer {

    public static void Init() {

        Connection connect = Connector.getInstance();

        try {

            PreparedStatement preparedStatement;

            // Create the table "Person"
            preparedStatement = connect.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Person (personId INTEGER PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), surname VARCHAR(255))");
            preparedStatement.executeUpdate();

            // Create the table "Role"
            preparedStatement = connect.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Role (roleId INTEGER PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), accessLevel INTEGER)");
            preparedStatement.executeUpdate();

            // Create the table "User"
            preparedStatement = connect.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS User (userId INTEGER PRIMARY KEY AUTO_INCREMENT, password VARCHAR(255), personId INTEGER, roleId INTEGER, FOREIGN KEY(personId) REFERENCES Person(personId), FOREIGN KEY(roleId) REFERENCES Role(roleId))");
            preparedStatement.executeUpdate();

            // Create the table "Participant
            preparedStatement = connect.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Participant (participantId INTEGER PRIMARY KEY AUTO_INCREMENT, personId INTEGER, FOREIGN KEY(personId) REFERENCES Person(personId))");
            preparedStatement.executeUpdate();

            // Create the table "FormulaElement"
            preparedStatement = connect.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS FormulaElement (formulaElementId INTEGER PRIMARY KEY AUTO_INCREMENT, type VARCHAR(255), description VARCHAR(255), numberBefore INTEGER, numberAfter INTEGER)");
            preparedStatement.executeUpdate();

            // Create the table "Formula"
            preparedStatement = connect.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Formula (formulaId INTEGER PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), description VARCHAR(255), formulaElementId INTEGER, FOREIGN KEY(formulaElementId) REFERENCES FormulaElement(formulaElementId))");
            preparedStatement.executeUpdate();

            // Create the table "Stage"
            preparedStatement = connect.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Stage (stageId INTEGER PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), formulaElementId INTEGER, FOREIGN KEY(formulaElementId) REFERENCES FormulaElement(formulaElementId))");
            preparedStatement.executeUpdate();

            // Create the table "Competition"
            preparedStatement = connect.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Competition (competitionId INTEGER PRIMARY KEY AUTO_INCREMENT, formulaId INTEGER, stageId INTEGER, participantId INTEGER, name VARCHAR(255), status VARCHAR(255), category VARCHAR(255), FOREIGN KEY(formulaId) REFERENCES Formula(formulaId), FOREIGN KEY(stageId) REFERENCES Stage(stageId), FOREIGN KEY(participantId) REFERENCES Participant(participantId))");
            preparedStatement.executeUpdate();

            System.out.println("Database initialized.");

        } catch (Exception e) {

            System.out.println("An error occured while initializing the database.");
            e.printStackTrace();

        }

    }
}
