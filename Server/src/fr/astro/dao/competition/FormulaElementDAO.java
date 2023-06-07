package fr.astro.dao.competition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.astro.dao.SQLObject;
import fr.astro.dao.database.Connector;
import fr.astro.entity.competition.FormulaElementEntity;
import fr.astro.exception.sql.ObjectNotFound;
import fr.astro.util.Instantiable;

/**
 * FormulaElementDAO
 * 
 * DAO for FormulaElementEntity
 * 
 * @see FormulaElementEntity
 * @see SQLObject
 */
public class FormulaElementDAO implements SQLObject<FormulaElementEntity>, Instantiable {

    // Instances
    private static FormulaElementDAO instance;

    private static Connection connection;

    /* --------------- Query Information --------------- */
    private final String TABLE_NAME = "FormulaElement";

    // Columns
    private final String COLUMN_ID = "formulaElementId";
    private final String COLUMN_TYPE = "type";
    private final String COLUMN_NUMBER_BEFORE = "numberBefore";
    private final String COLUMN_NUMBER_AFTER = "numberAfter";
    private final String COLUMN_DESCRIPTION = "description";

    // Queries
    private final String INSERT_QUERY = String.format("INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?)",
            TABLE_NAME, COLUMN_ID, COLUMN_TYPE, COLUMN_NUMBER_BEFORE, COLUMN_NUMBER_AFTER, COLUMN_DESCRIPTION);
    private final String UPDATE_QUERY = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?",
            TABLE_NAME, COLUMN_TYPE, COLUMN_NUMBER_BEFORE, COLUMN_NUMBER_AFTER, COLUMN_DESCRIPTION, COLUMN_ID);
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
    private FormulaElementDAO() {
        connection = Connector.getInstance();
    }

    /**
     * Return the FormulaElementDAO instance
     * Create it if it doesn't exist
     * 
     * @return the FormulaElementDAO instance
     */
    public static FormulaElementDAO getInstance() {
        if (instance == null) {
            instance = new FormulaElementDAO();
        }
        return instance;
    }

    @Override
    public boolean save(FormulaElementEntity object) throws ObjectNotFound, SQLException {
        
        if (object == null) {
            throw new ObjectNotFound("FormulaElementEntity cannot be null");
        }

        if (exist(object)) {
            return update(object);
        }

        PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
        statement.setInt(1, object.getFormulaElementId());
        statement.setString(2, object.getFormulaElementType());
        statement.setInt(3, object.getFormulaElementNumberBefore());
        statement.setInt(4, object.getFormulaElementNumberAfter());
        statement.setString(5, object.getFormulaElementDescription());

        return statement.executeUpdate() > 0;

    }

    @Override
    public boolean update(FormulaElementEntity object) throws ObjectNotFound, SQLException {
        
        if (object == null) {
            throw new ObjectNotFound("FormulaElementEntity cannot be null");
        }

        if (!exist(object)) {
            throw new ObjectNotFound("FormulaElementEntity", object.getFormulaElementId());
        }

        PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
        statement.setString(1, object.getFormulaElementType());
        statement.setInt(2, object.getFormulaElementNumberBefore());
        statement.setInt(3, object.getFormulaElementNumberAfter());
        statement.setString(4, object.getFormulaElementDescription());
        statement.setInt(5, object.getFormulaElementId());

        return statement.executeUpdate() > 0;

    }

    @Override
    public boolean delete(FormulaElementEntity object) throws ObjectNotFound, SQLException {
        
        if (object == null) {
            throw new ObjectNotFound("FormulaElementEntity cannot be null");
        }

        if (!exist(object)) {
            throw new ObjectNotFound("FormulaElementEntity", object.getFormulaElementId());
        }

        PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
        statement.setInt(1, object.getFormulaElementId());

        return statement.executeUpdate() > 0;

    }

    @Override
    public FormulaElementEntity get(int id) throws ObjectNotFound, SQLException {
        
        if (!exist(id)) {
            throw new ObjectNotFound("FormulaElementEntity", id);
        }

        PreparedStatement statement = connection.prepareStatement(GET_QUERY);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            try {
                return FormulaElementEntity.of(result.getInt(COLUMN_ID), result.getString(COLUMN_TYPE),
                        result.getInt(COLUMN_NUMBER_BEFORE), result.getInt(COLUMN_NUMBER_AFTER),
                        result.getString(COLUMN_DESCRIPTION));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Should never happen
        throw new ObjectNotFound("FormulaElementEntity", id);

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
    public boolean exist(FormulaElementEntity object) throws SQLException {
        
        if (object == null) {
            return false;
        }

        return exist(object.getFormulaElementId());

    }

    @Override
    public List<FormulaElementEntity> getAll() throws SQLException {
        
        return getAll(-1);

    }

    @Override
    public List<FormulaElementEntity> getAll(int limit) throws SQLException {
        
        if (limit < 0) {
            limit = +Integer.MAX_VALUE;
        }

        PreparedStatement statement = connection.prepareStatement(GET_ALL_LIMIT_QUERY);
        statement.setInt(1, limit);
        ResultSet result = statement.executeQuery();

        List<FormulaElementEntity> list = new ArrayList<>();

        while (result.next()) {
            try {
                list.add(FormulaElementEntity.of(result.getInt(COLUMN_ID), result.getString(COLUMN_TYPE),
                        result.getInt(COLUMN_NUMBER_BEFORE), result.getInt(COLUMN_NUMBER_AFTER),
                        result.getString(COLUMN_DESCRIPTION)));
            } catch (Exception e) {
                e.printStackTrace();
            }
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
    public FormulaElementEntity copyObject(FormulaElementEntity object) throws ObjectNotFound {
        
        if (object == null) {
            throw new ObjectNotFound("FormulaElementEntity cannot be null");
        }

        try {
            return FormulaElementEntity.of(object.getFormulaElementId(), object.getFormulaElementType(),
                    object.getFormulaElementNumberBefore(), object.getFormulaElementNumberAfter(),
                    object.getFormulaElementDescription());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Should never happen
        throw new ObjectNotFound("FormulaElementEntity", object.getFormulaElementId());

    }

}
