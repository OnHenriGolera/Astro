package src.fr.astro.dao.human;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.fr.astro.dao.DAOObject;
import src.fr.astro.dao.SQLObject;
import src.fr.astro.dao.database.Connector;
import src.fr.astro.entity.human.PersonEntity;
import src.fr.astro.entity.human.RoleEntity;
import src.fr.astro.entity.human.UserEntity;
import src.fr.astro.exception.sql.ObjectNotFound;

/**
 * UserDAO
 * 
 * DAO for UserEntity
 * 
 * @see UserEntity
 * @see SQLObject
 */
public class UserDAO implements SQLObject<UserEntity>, DAOObject {

    // Instances
    private static UserDAO instance;
    private static Connection connection;

    /* --------------- Query Information --------------- */
    private final String TABLE_NAME = "User";

    // Columns
    private final String COLUMN_ID = "userId";
    private final String COLUMN_PERSON_ID = "personId";
    private final String COLUMN_PASSWORD = "password";
    private final String COLUMN_ROLE_ID = "roleId";

    // Queries
    private final String INSERT_QUERY = String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES (?, ?, ?, ?)", TABLE_NAME,
            COLUMN_ID, COLUMN_PERSON_ID, COLUMN_PASSWORD, COLUMN_ROLE_ID);
    private final String UPDATE_QUERY = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ? WHERE %s = ?", TABLE_NAME,
            COLUMN_PERSON_ID, COLUMN_PASSWORD, COLUMN_ROLE_ID, COLUMN_ID);
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
    private UserDAO() {
        connection = Connector.getInstance();
    }

    /**
     * Return the instance of UserDAO
     * Create it if it doesn't exist
     * 
     * @return the instance of UserDAO
     */
    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    @Override
    public boolean save(UserEntity object) throws ObjectNotFound, SQLException {

        if (object == null) {
            throw new ObjectNotFound("UserEntity", "null");
        }

        if (exist(object)) {
            return update(object);
        }

        // Save as PersonEntity
        PersonDAO.getInstance().save(object);

        // Save user
        PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
        statement.setInt(1, object.getUserId());
        statement.setInt(2, object.getPersonId());
        statement.setString(3, object.getUserPassword());
        statement.setInt(4, object.getUserRoleEntity().getRoleId());
        statement.executeUpdate();

        return true;
    }

    @Override
    public boolean update(UserEntity object) throws ObjectNotFound, SQLException {

        if (object == null) {
            throw new ObjectNotFound("UserEntity", "null");
        }

        if (!exist(object)) {
            throw new ObjectNotFound("UserEntity", object.getUserId());
        }

        // Update as PersonEntity
        PersonDAO.getInstance().update(object);

        // Update user
        PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
        statement.setInt(1, object.getPersonId());
        statement.setString(2, object.getUserPassword());
        statement.setInt(3, object.getUserRoleEntity().getRoleId());
        statement.setInt(4, object.getUserId());
        statement.executeUpdate();

        return true;

    }

    @Override
    public boolean delete(UserEntity object) throws ObjectNotFound, SQLException {

        if (object == null) {
            throw new ObjectNotFound("UserEntity", "null");
        }

        if (!exist(object)) {
            throw new ObjectNotFound("UserEntity", object.getUserId());
        }

        // Delete user
        PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
        statement.setInt(1, object.getUserId());
        statement.executeUpdate();

        // Delete as PersonEntity
        PersonDAO.getInstance().delete(object);

        return true;
    }

    @Override
    public UserEntity get(int id) throws ObjectNotFound, SQLException {

        if (!exist(id)) {
            throw new ObjectNotFound("UserEntity", id);
        }

        // Get as PersonEntity
        PersonDAO.getInstance().get(id);

        // Get role
        RoleDAO.getInstance().get(id);

        // Get user
        PreparedStatement statement = connection.prepareStatement(GET_QUERY);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            int personId = result.getInt(COLUMN_PERSON_ID);
            String password = result.getString(COLUMN_PASSWORD);
            int roleId = result.getInt(COLUMN_ROLE_ID);

            PersonEntity person = PersonDAO.getInstance().get(personId);
            String personName = person.getPersonName();
            String personSurname = person.getPersonSurname();

            RoleEntity role = RoleDAO.getInstance().get(roleId);

            return UserEntity.of(id, personName, personSurname, id, password, role);
        }

        // Normally, this line is never reached because of exist(id) check
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
    public boolean exist(UserEntity object) throws SQLException {

        if (object == null) {
            return false;
        }

        return exist(object.getUserId());

    }

    @Override
    public List<UserEntity> getAll() throws SQLException {
        return getAll(-1);
    }

    @Override
    public List<UserEntity> getAll(int limit) throws SQLException {

        if (limit < 0) {
            limit = +Integer.MAX_VALUE;
        }

        PreparedStatement statement = connection.prepareStatement(GET_ALL_LIMIT_QUERY);
        statement.setInt(1, limit);
        ResultSet result = statement.executeQuery();

        List<UserEntity> users = new ArrayList<>();

        while (result.next()) {
            try {
                int userId = result.getInt(COLUMN_ID);
                int personId = result.getInt(COLUMN_PERSON_ID);
                String password = result.getString(COLUMN_PASSWORD);
                int roleId = result.getInt(COLUMN_ROLE_ID);

                PersonEntity person = PersonDAO.getInstance().get(personId);
                String personName = person.getPersonName();
                String personSurname = person.getPersonSurname();

                RoleEntity role = RoleDAO.getInstance().get(roleId);

                users.add(UserEntity.of(userId, personName, personSurname, personId, password, role));

            } catch (ObjectNotFound e) {
                e.printStackTrace();
            }
        }

        return users;

    }

    @Override
    public int getLastInsertedId() throws SQLException {

        PreparedStatement statement = connection.prepareStatement(GET_LAST_INSERTED_ID);
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            return result.getInt(1);
        }

        return -1;

    }

    @Override
    public UserEntity copyObject(UserEntity object) throws ObjectNotFound {

        if (object == null) {
            throw new ObjectNotFound("UserEntity", "null");
        }

        return UserEntity.of(object.getUserId(), object.getPersonName(), object.getPersonSurname(),
                object.getPersonId(),
                object.getUserPassword(), object.getUserRoleEntity());

    }

}
