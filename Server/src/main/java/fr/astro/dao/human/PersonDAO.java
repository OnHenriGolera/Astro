package fr.astro.dao.human;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.astro.dao.SQLObject;
import fr.astro.dao.database.Connector;
import fr.astro.entity.human.PersonEntity;
import fr.astro.exception.sql.ObjectNotFound;
import fr.astro.util.Instantiable;

/**
 * PersonDAO
 * 
 * DAO for PersonEntity
 * 
 * @see PersonEntity
 * @see SQLObject
 */
public class PersonDAO implements SQLObject<PersonEntity>, Instantiable {

    // Instances
    private static PersonDAO instance;
    private static Connection connection;

    /* --------------- Query Information --------------- */
    private final String TABLE_NAME = "Person";

    // Columns
    private final String COLUMN_ID = "personId";
    private final String COLUMN_FIRSTNAME = "name";
    private final String COLUMN_SURNAME = "surname";
    private final String COLUMN_GENDER = "gender";
    private final String COLUMN_BIRTH_DATE = "birthDate";

    // Queries
    private final String INSERT_QUERY = String.format("INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?)", TABLE_NAME,
            COLUMN_ID, COLUMN_FIRSTNAME, COLUMN_SURNAME, COLUMN_GENDER, COLUMN_BIRTH_DATE);
    private final String UPDATE_QUERY = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?", TABLE_NAME,
            COLUMN_FIRSTNAME, COLUMN_SURNAME, COLUMN_GENDER, COLUMN_BIRTH_DATE, COLUMN_ID);
    private final String DELETE_QUERY = String.format("DELETE FROM %s WHERE %s = ?", TABLE_NAME, COLUMN_ID);
    private final String GET_QUERY = String.format("SELECT * FROM %s WHERE %s = ?", TABLE_NAME, COLUMN_ID);
    private final String EXIST_QUERY = String.format("SELECT COUNT(*) FROM %s WHERE %s = ?", TABLE_NAME, COLUMN_ID);
    private final String GET_ALL_LIMIT_QUERY = String.format("SELECT * FROM %s LIMIT ?", TABLE_NAME);
    private final String GET_LAST_INSERTED_ID = String.format("SELECT %s FROM %s ORDER BY %s DESC LIMIT 1", COLUMN_ID,
            TABLE_NAME, COLUMN_ID);
    /* ------------------------------------------------- */

    /**
     * Constructor
     * Create a connection to the database
     * 
     * @see Connector
     */
    private PersonDAO() {
        connection = Connector.getInstance();
    }

    /**
     * Return the instance of PersonDAO
     * Create it if it doesn't exist
     * 
     * @return the instance of PersonDAO
     * @see Instantiable
     */
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

        if (exist(object)) {
            return update(object);
        }

        PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
        statement.setInt(1, object.getPersonId());
        statement.setString(2, object.getPersonName());
        statement.setString(3, object.getPersonSurname());
        statement.setString(4, object.getPersonGender().getName());
        statement.setString(5, object.getPersonBirthDate());

        return statement.executeUpdate() > 0;
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
        statement.setString(3, object.getPersonGender().getName());
        statement.setString(4, object.getPersonBirthDate());
        statement.setInt(5, object.getPersonId());
        

        return statement.executeUpdate() > 0;

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

        return statement.executeUpdate() > 0;

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
            String gender = result.getString(COLUMN_GENDER);
            String birthDate = result.getString(COLUMN_BIRTH_DATE);
            return new PersonEntity(id, name, surname, gender, birthDate);
        }

        // This should never happen because we check if the object exist before
        throw new ObjectNotFound("PersonEntity", id);

    }

    @Override
    public boolean exist(int id) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(EXIST_QUERY);

        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            return result.getInt(1) > 0;
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

    @Override
    public List<PersonEntity> getAll() throws SQLException {

        return getAll(-1);

    }

    @Override
    public List<PersonEntity> getAll(int limit) throws SQLException {

        if (limit < 0) {
            limit = +Integer.MAX_VALUE;
        }

        PreparedStatement statement = connection.prepareStatement(GET_ALL_LIMIT_QUERY);
        statement.setInt(1, limit);
        ResultSet result = statement.executeQuery();

        List<PersonEntity> list = new ArrayList<PersonEntity>();

        while (result.next()) {
            int id = result.getInt(COLUMN_ID);
            String name = result.getString(COLUMN_FIRSTNAME);
            String surname = result.getString(COLUMN_SURNAME);
            String gender = result.getString(COLUMN_GENDER);
            String birthDate = result.getString(COLUMN_BIRTH_DATE);
            list.add(new PersonEntity(id, name, surname, gender, birthDate));
        }

        return list;

    }

    @Override
    public int getLastInsertedId() throws SQLException {

        PreparedStatement statement = connection.prepareStatement(GET_LAST_INSERTED_ID);
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            return result.getInt(COLUMN_ID);
        }

        return -1;

    }

    @Override
    public PersonEntity copyObject(PersonEntity object) throws ObjectNotFound {

        if (object == null) {
            throw new ObjectNotFound("PersonEntity", "null");
        }

        return new PersonEntity(object.getPersonId(), object.getPersonName(), object.getPersonSurname(), object.getPersonGender().getName(), object.getPersonBirthDate());

    }

}
