package src.fr.astro.dao;

import java.sql.*;

public class Initializer {

    public static void Init() {

        Connection connect = Connector.getInstance();

        try {

            PreparedStatement preparedStatement;

            // Create the table "Person" : id, name, surname
            preparedStatement = connect.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Person (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), surname VARCHAR(255))");
            preparedStatement.executeUpdate();

            // Create the table "Role" : id, name, accessLevel
            preparedStatement = connect.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Role (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), accessLevel INT)");
            preparedStatement.executeUpdate();

            // Create the table "User" : userId, personId, roleId, password
            preparedStatement = connect.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS User (userId INT PRIMARY KEY AUTO_INCREMENT, personId INT, roleId INT, password VARCHAR(255), FOREIGN KEY (personId) REFERENCES Person(id), FOREIGN KEY (roleId) REFERENCES Role(id))");
            preparedStatement.executeUpdate();

            // Create the table "Participant" : participantId, personId, category, present
            preparedStatement = connect.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Participant (participantId INT PRIMARY KEY AUTO_INCREMENT, personId INT, category VARCHAR(255), present BOOLEAN, FOREIGN KEY (personId) REFERENCES Person(id))");
            preparedStatement.executeUpdate();

            // Create the table "FormulaElement" : formulaElementId, type, numberBefore, numberAfter, description
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

            // Create the table : "Competition" : competitionId, formulaId, name, status, category
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
}
