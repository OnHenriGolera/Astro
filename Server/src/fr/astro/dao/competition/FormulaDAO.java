package fr.astro.dao.competition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.astro.dao.SQLObjectAndRelatedSet;
import fr.astro.dao.database.Connector;
import fr.astro.entity.competition.FormulaElementEntity;
import fr.astro.entity.competition.FormulaEntity;
import fr.astro.exception.sql.ObjectNotFound;
import fr.astro.util.Instantiable;

public class FormulaDAO implements SQLObjectAndRelatedSet<FormulaEntity, FormulaElementEntity>, Instantiable {

    // Instances
    private static FormulaDAO instance;
    private static Connection connection;

    /* --------------- Query Information --------------- */
    private final String TABLE_NAME = "Formula";

    // Columns (in formula)
    private final String COLUMN_ID = "formulaId";
    private final String COLUMN_NAME = "name";
    private final String COLUMN_DESCRIPTION = "description";

    // Queries
    private final String INSERT_QUERY = String.format("INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?)", TABLE_NAME,
            COLUMN_ID, COLUMN_NAME, COLUMN_DESCRIPTION);
    private final String UPDATE_QUERY = String.format("UPDATE %s SET %s = ?, %s = ? WHERE %s = ?", TABLE_NAME,
            COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_ID);
    private final String DELETE_QUERY = String.format("DELETE FROM %s WHERE %s = ?", TABLE_NAME, COLUMN_ID);
    private final String GET_QUERY = String.format("SELECT * FROM %s WHERE %s = ?", TABLE_NAME, COLUMN_ID);
    private final String EXIST_QUERY = String.format("SELECT COUNT(*) FROM %s WHERE %s = ?", TABLE_NAME, COLUMN_ID);
    private final String GET_ALL_LIMIT_QUERY = String.format("SELECT * FROM %s LIMIT ?", TABLE_NAME);
    private final String GET_LAST_INSERTED_ID = String.format("SELECT %s FROM %s ORDER BY %s DESC LIMIT 1", COLUMN_ID,
            TABLE_NAME, COLUMN_ID);

    // Query for the Set : Select * from FormulaElementList where formulaId = ?
    private final String GET_SET_QUERY = String.format("SELECT * FROM FormulaElementList WHERE %s = ?", COLUMN_ID);
    private final String formulaElementId = "formulaElementId";
    /* ------------------------------------------------- */

    /**
     * Constructor Create a connection to the database
     * 
     * @see Connector
     */
    private FormulaDAO() {
        connection = Connector.getInstance();
    }

    /**
     * Return the instance of FormulaElementDAO
     * 
     * @return the instance of FormulaElementDAO
     */
    public static FormulaDAO getInstance() {
        if (instance == null)
            instance = new FormulaDAO();
        return instance;
    }

    @Override
    public boolean save(FormulaEntity object) throws ObjectNotFound, SQLException {

        if (object == null) {
            throw new NullPointerException("FormulaEntity cannot be null");
        }

        if (exist(object)) {
            return update(object);
        }

        PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
        statement.setInt(1, object.getFormulaId());
        statement.setString(2, object.getFormulaName());
        statement.setString(3, object.getFormulaDescription());

        return statement.executeUpdate() > 0;

    }

    @Override
    public boolean update(FormulaEntity object) throws ObjectNotFound, SQLException {

        if (object == null) {
            throw new NullPointerException("FormulaEntity cannot be null");
        }

        if (!exist(object)) {
            throw new ObjectNotFound("FormulaEntity doesn't exist");
        }

        PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
        statement.setString(1, object.getFormulaName());
        statement.setString(2, object.getFormulaDescription());
        statement.setInt(3, object.getFormulaId());

        return statement.executeUpdate() > 0;

    }

    @Override
    public boolean delete(FormulaEntity object) throws ObjectNotFound, SQLException {

        if (object == null) {
            throw new NullPointerException("FormulaEntity cannot be null");
        }

        if (!exist(object)) {
            throw new ObjectNotFound("FormulaEntity doesn't exist");
        }

        PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
        statement.setInt(1, object.getFormulaId());

        return statement.executeUpdate() > 0;

    }

    @Override
    public FormulaEntity get(int id) throws ObjectNotFound, SQLException {

        if (!exist(id)) {
            throw new ObjectNotFound("FormulaEntity doesn't exist");
        }

        PreparedStatement statement = connection.prepareStatement(GET_QUERY);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return FormulaEntity.of(resultSet.getInt(COLUMN_ID), resultSet.getString(COLUMN_NAME),
                    resultSet.getString(COLUMN_DESCRIPTION));
        }

        // Should never happen
        throw new ObjectNotFound("FormulaEntity doesn't exist");

    }

    @Override
    public boolean exist(int id) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(EXIST_QUERY);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt(1) > 0;
        }

        return false;

    }

    @Override
    public boolean exist(FormulaEntity object) throws SQLException {

        if (object == null) {
            throw new NullPointerException("FormulaEntity cannot be null");
        }

        return exist(object.getFormulaId());

    }

    @Override
    public List<FormulaEntity> getAll() throws SQLException {

        return getAll(-1);

    }

    @Override
    public List<FormulaEntity> getAll(int limit) throws SQLException {

        if (limit < 0) {
            limit = +Integer.MAX_VALUE;
        }

        PreparedStatement statement = connection.prepareStatement(GET_ALL_LIMIT_QUERY);
        statement.setInt(1, limit);
        ResultSet resultSet = statement.executeQuery();

        List<FormulaEntity> formulaEntities = new ArrayList<>();

        while (resultSet.next()) {
            formulaEntities.add(FormulaEntity.of(resultSet.getInt(COLUMN_ID), resultSet.getString(COLUMN_NAME),
                    resultSet.getString(COLUMN_DESCRIPTION)));
        }

        return formulaEntities;

    }

    @Override
    public int getLastInsertedId() throws SQLException {

        PreparedStatement statement = connection.prepareStatement(GET_LAST_INSERTED_ID);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt(COLUMN_ID);
        }

        return -1;

    }

    @Override
    public FormulaEntity copyObject(FormulaEntity object) throws ObjectNotFound {

        if (object == null) {
            throw new NullPointerException("FormulaEntity cannot be null");
        }

        return FormulaEntity.of(object.getFormulaId(), object.getFormulaName(), object.getFormulaDescription());

    }

    @Override
    public Set<FormulaElementEntity> getRelatedSet(FormulaEntity object) throws SQLException {

        if (object == null) {
            throw new NullPointerException("FormulaEntity cannot be null");
        }

        PreparedStatement statement = connection.prepareStatement(GET_SET_QUERY);
        statement.setInt(1, object.getFormulaId());
        ResultSet resultSet = statement.executeQuery();

        Set<FormulaElementEntity> formulaElementEntities = new HashSet<>();

        while (resultSet.next()) {

            try {

                formulaElementEntities.add(FormulaElementDAO.getInstance().get(resultSet.getInt(formulaElementId)));

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return formulaElementEntities;

    }

}
