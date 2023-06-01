package src.fr.astro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.fr.astro.entity.PersonEntity;
import src.fr.astro.exception.sql.ObjectNotFound;

public class PersonDAO implements SQLObject<PersonEntity> {

    private static PersonDAO instance;
    private static Connection connection;

    private final String TABLE_NAME = "person";

    private final String COLUMN_ID = "personId";
    private final String COLUMN_FIRSTNAME = "firstname";
    private final String COLUMN_SURNAME = "surname";
    
    private final String INSERT_QUERY = String.format("INSERT INTO %s (%s, %s) VALUES (?, ?)", TABLE_NAME,
            COLUMN_FIRSTNAME, COLUMN_SURNAME);
    private final String UPDATE_QUERY = String.format("UPDATE %s SET %s = ?, %s = ? WHERE %s = ?", TABLE_NAME,
            COLUMN_FIRSTNAME, COLUMN_SURNAME, COLUMN_ID);
    private final String DELETE_QUERY = String.format("DELETE FROM %s WHERE %s = ?", TABLE_NAME, COLUMN_ID);
    private final String GET_QUERY = String.format("SELECT * FROM %s WHERE %s = ?", TABLE_NAME, COLUMN_ID);
    private final String EXIST_QUERY = String.format("SELECT COUNT(*) FROM %s WHERE %s = ?", TABLE_NAME, COLUMN_ID);


    private PersonDAO() {
        connection = Connector.getInstance();
    }

    public static PersonDAO getInstance() {
        if (instance == null) {
            instance = new PersonDAO();
        }
        return instance;
    }

    @Override
    public boolean save(PersonEntity object) throws ObjectNotFound, SQLException {
        
        if (object == null) {
            throw new ObjectNotFound("PersonEntity", "null");
        }

        if (!exist(object)) {
            throw new ObjectNotFound("PersonEntity", object.getPersonId());
        }

        PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
        statement.setString(1, object.getPersonName());
        statement.setString(2, object.getPersonSurname());
        statement.executeUpdate();

        return true;
    }

    @Override
    public boolean update(PersonEntity object) throws ObjectNotFound, SQLException {
        
        if (object == null) {
            return false;
        }

        if (!exist(object)) {
            throw new ObjectNotFound("PersonEntity", object.getPersonId());
        }

        PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
        statement.setString(1, object.getPersonName());
        statement.setString(2, object.getPersonSurname());
        statement.setInt(3, object.getPersonId());
        statement.executeUpdate();

        return true;

    }

    @Override
    public boolean delete(PersonEntity object) throws ObjectNotFound, SQLException {
        
        if (object == null) {
            return false;
        }

        if (!exist(object)) {
            throw new ObjectNotFound("PersonEntity", object.getPersonId());
        }

        PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
        statement.setInt(1, object.getPersonId());
        statement.executeUpdate();

        return true;
    }

    @Override
    public PersonEntity get(int id) throws ObjectNotFound, SQLException {

        if (!exist(id)) {
            throw new ObjectNotFound("PersonEntity", id);
        }

        PreparedStatement statement = connection.prepareStatement(GET_QUERY);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            String name = result.getString(COLUMN_FIRSTNAME);
            String surname = result.getString(COLUMN_SURNAME);
            return new PersonEntity(id, name, surname);
        }

        // This should never happen because we check if the object exist before
        return null;

    }

    @Override
    public boolean exist(int id) throws SQLException {
        
        PreparedStatement statement = connection.prepareStatement(EXIST_QUERY);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            return result.getInt(1) == 1;
        }

        return false;

    }

    @Override
    public boolean exist(PersonEntity object) throws SQLException {

        if (object == null) {
            return false;
        }

        return exist(object.getPersonId());
    }

}
