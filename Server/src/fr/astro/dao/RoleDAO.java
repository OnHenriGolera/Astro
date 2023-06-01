package src.fr.astro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.fr.astro.entity.RoleEntity;
import src.fr.astro.exception.sql.ObjectNotFound;

/**
 * RoleDAO
 * 
 * DAO for RoleEntity
 * @see RoleEntity
 * @see SQLObject
 */
public class RoleDAO implements SQLObject<RoleEntity> {

    // Instances
    private static RoleDAO instance;
    private static Connection connection;

    /* --------------- Query Information --------------- */
    private final String TABLE_NAME = "Role";
    
    // Columns
    private final String COLUMN_ID = "roleId";
    private final String COLUMN_NAME = "name";
    private final String COLUMN_ACCESS_LEVEL = "access_level";

    // Queries
    private final String INSERT_QUERY = String.format("INSERT INTO %s (%s, %s) VALUES (?, ?)", TABLE_NAME,
            COLUMN_NAME, COLUMN_ACCESS_LEVEL);
    private final String UPDATE_QUERY = String.format("UPDATE %s SET %s = ?, %s = ? WHERE %s = ?", TABLE_NAME,
            COLUMN_NAME, COLUMN_ACCESS_LEVEL, COLUMN_ID);
    private final String DELETE_QUERY = String.format("DELETE FROM %s WHERE %s = ?", TABLE_NAME, COLUMN_ID);
    private final String GET_QUERY = String.format("SELECT * FROM %s WHERE %s = ?", TABLE_NAME, COLUMN_ID);
    private final String EXIST_QUERY = String.format("SELECT COUNT(*) FROM %s WHERE %s = ?", TABLE_NAME, COLUMN_ID);
    /* ------------------------------------------------- */

    /**
     * Constructor
     * Create a connection to the database
     * 
     * @see Connector
     */
    private RoleDAO() {
        connection = Connector.getInstance();
    }

    /**
     * Return the instance of RoleDAO
     * Create it if it doesn't exist
     * @return the instance of RoleDAO
     */
    public static RoleDAO getInstance() {
        if (instance == null) {
            instance = new RoleDAO();
        }
        return instance;
    }

    @Override
    public boolean save(RoleEntity object) throws ObjectNotFound, SQLException {

        if (object == null) {
            throw new ObjectNotFound("RoleEntity", "null");
        }

        if (!exist(object)) {
            throw new ObjectNotFound("RoleEntity", object.getRoleId());
        }

        PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
        statement.setString(1, object.getRoleName());
        statement.setInt(2, object.getRoleAccessLevel());
        statement.executeUpdate();

        return true;
    }

    @Override
    public boolean update(RoleEntity object) throws ObjectNotFound, SQLException {

        if (object == null) {
            throw new ObjectNotFound("RoleEntity", "null");
        }

        if (!exist(object)) {
            throw new ObjectNotFound("RoleEntity", object.getRoleId());
        }

        PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
        statement.setString(1, object.getRoleName());
        statement.setInt(2, object.getRoleAccessLevel());
        statement.setInt(3, object.getRoleId());
        statement.executeUpdate();

        return true;
    }

    @Override
    public boolean delete(RoleEntity object) throws ObjectNotFound, SQLException {

        if (object == null) {
            throw new ObjectNotFound("RoleEntity", "null");
        }

        if (!exist(object)) {
            throw new ObjectNotFound("RoleEntity", object.getRoleId());
        }

        PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
        statement.setInt(1, object.getRoleId());
        statement.executeUpdate();

        return true;
    }

    @Override
    public RoleEntity get(int id) throws ObjectNotFound, SQLException {

        if (!exist(id)) {
            throw new ObjectNotFound("RoleEntity", id);
        }

        PreparedStatement statement = connection.prepareStatement(GET_QUERY);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            String name = result.getString(COLUMN_NAME);
            int accessLevel = result.getInt(COLUMN_ACCESS_LEVEL);
            return RoleEntity.of(id, name, accessLevel);
        }

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
    public boolean exist(RoleEntity object) throws SQLException {

        if (object == null) {
            return false;
        }

        return exist(object.getRoleId());
    }

}
