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
import fr.astro.dao.human.ParticipantDAO;
import fr.astro.entity.competition.StageEntity;
import fr.astro.entity.human.ParticipantEntity;
import fr.astro.exception.sql.ObjectNotFound;
import fr.astro.util.Instantiable;

public class StageDAO implements SQLObjectAndRelatedSet<StageEntity, ParticipantEntity>, Instantiable {

    // Instances
    private static StageDAO instance;
    private static Connection connection;

    /* --------------- Query Information --------------- */
    private final String TABLE_NAME = "Stage";

    // Columns (in stage)
    private final String COLUMN_ID = "stageId";
    private final String COLUMN_NAME = "name";
    private final String COLUMN_FORMULA_ELEMENT_ID = "formulaElementId";

    // Queries
    private final String INSERT_QUERY = String.format("INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?)", TABLE_NAME,
            COLUMN_ID, COLUMN_NAME, COLUMN_FORMULA_ELEMENT_ID);
    private final String UPDATE_QUERY = String.format("UPDATE %s SET %s = ?, %s = ? WHERE %s = ?", TABLE_NAME,
            COLUMN_NAME, COLUMN_FORMULA_ELEMENT_ID, COLUMN_ID);
    private final String DELETE_QUERY = String.format("DELETE FROM %s WHERE %s = ?", TABLE_NAME, COLUMN_ID);
    private final String GET_QUERY = String.format("SELECT * FROM %s WHERE %s = ?", TABLE_NAME, COLUMN_ID);
    private final String EXIST_QUERY = String.format("SELECT COUNT(*) FROM %s WHERE %s = ?", TABLE_NAME, COLUMN_ID);
    private final String GET_ALL_LIMIT_QUERY = String.format("SELECT * FROM %s LIMIT ?", TABLE_NAME);
    private final String GET_LAST_INSERTED_ID = String.format("SELECT %s FROM %s ORDER BY %s DESC LIMIT 1", COLUMN_ID,
            TABLE_NAME, COLUMN_ID);

    // Query for the Set : Select * from ParticipantList where stageId = ?
    private final String GET_SET_QUERY = String.format("SELECT * FROM ParticipantList WHERE %s = ?", COLUMN_ID);
    private final String participantId = "participantId";
    /* ------------------------------------------------- */

    /**
     * Constructor Create a connection to the database
     * 
     * @see Connector
     */
    private StageDAO() {
        connection = Connector.getInstance();
    }

    /**
     * Return the instance of StageDAO
     * 
     * @return the instance of StageDAO
     */
    public static StageDAO getInstance() {
        if (instance == null)
            instance = new StageDAO();
        return instance;
    }

    @Override
    public boolean save(StageEntity object) throws ObjectNotFound, SQLException {

        if (object == null) {
            throw new ObjectNotFound("StageEntity cannot be null");
        }

        if (exist(object)) {
            return update(object);
        }

        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
        preparedStatement.setInt(1, object.getStageId());
        preparedStatement.setString(2, object.getStageName());
        preparedStatement.setInt(3, object.getFormulaElementEntity().getFormulaElementId());

        return preparedStatement.executeUpdate() > 0;

    }

    @Override
    public boolean update(StageEntity object) throws ObjectNotFound, SQLException {

        if (object == null) {
            throw new ObjectNotFound("StageEntity cannot be null");
        }

        if (!exist(object)) {
            throw new ObjectNotFound("StageEntity cannot be found in the database");
        }

        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
        preparedStatement.setString(1, object.getStageName());
        preparedStatement.setInt(2, object.getFormulaElementEntity().getFormulaElementId());
        preparedStatement.setInt(3, object.getStageId());

        return preparedStatement.executeUpdate() > 0;

    }

    @Override
    public boolean delete(StageEntity object) throws ObjectNotFound, SQLException {

        if (object == null) {
            throw new ObjectNotFound("StageEntity cannot be null");
        }

        if (!exist(object)) {
            throw new ObjectNotFound("StageEntity cannot be found in the database");
        }

        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
        preparedStatement.setInt(1, object.getStageId());

        return preparedStatement.executeUpdate() > 0;

    }

    @Override
    public StageEntity get(int id) throws ObjectNotFound, SQLException {

        if (!exist(id)) {
            throw new ObjectNotFound("StageEntity cannot be found in the database");
        }

        PreparedStatement preparedStatement = connection.prepareStatement(GET_QUERY);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return StageEntity.of(resultSet.getInt(COLUMN_ID), resultSet.getString(COLUMN_NAME),
                    FormulaElementDAO.getInstance().get(resultSet.getInt(COLUMN_FORMULA_ELEMENT_ID)));
        }

        // Should never happen
        throw new ObjectNotFound("StageEntity cannot be found in the database");

    }

    @Override
    public boolean exist(int id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(EXIST_QUERY);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt(1) > 0;
        }

        return false;

    }

    @Override
    public boolean exist(StageEntity object) throws SQLException {

        if (object == null) {
            throw new NullPointerException("StageEntity cannot be null");
        }

        return exist(object.getStageId());

    }

    @Override
    public List<StageEntity> getAll() throws SQLException {

        return getAll(-1);

    }

    @Override
    public List<StageEntity> getAll(int limit) throws SQLException {

        if (limit < 0) {
            limit = +Integer.MAX_VALUE;
        }

        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_LIMIT_QUERY);
        preparedStatement.setInt(1, limit);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<StageEntity> stageEntities = new ArrayList<>();

        while (resultSet.next()) {
            try {
                stageEntities.add(StageEntity.of(resultSet.getInt(COLUMN_ID), resultSet.getString(COLUMN_NAME),
                        FormulaElementDAO.getInstance().get(resultSet.getInt(COLUMN_FORMULA_ELEMENT_ID))));
            } catch (ObjectNotFound e) {
                // Should never happen except if the database is corrupted
                e.printStackTrace();
            }
        }

        return stageEntities;

    }

    @Override
    public int getLastInsertedId() throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(GET_LAST_INSERTED_ID);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt(COLUMN_ID);
        }

        return -1;

    }

    @Override
    public StageEntity copyObject(StageEntity object) throws ObjectNotFound {

        if (object == null) {
            throw new ObjectNotFound("StageEntity cannot be null");
        }

        return StageEntity.of(object.getStageId(), object.getStageName(), object.getFormulaElementEntity());

    }

    @Override
    public Set<ParticipantEntity> getRelatedSet(StageEntity object) throws SQLException {

        if (object == null) {
            throw new NullPointerException("StageEntity cannot be null");
        }

        PreparedStatement preparedStatement = connection.prepareStatement(GET_SET_QUERY);
        preparedStatement.setInt(1, object.getStageId());
        ResultSet resultSet = preparedStatement.executeQuery();

        Set<ParticipantEntity> participantEntities = new HashSet<>();

        while (resultSet.next()) {
            try {
                participantEntities.add(ParticipantDAO.getInstance().get(resultSet.getInt(participantId)));
            } catch (ObjectNotFound e) {
                // Should never happen except if the database is corrupted
                e.printStackTrace();
            }
        }

        return participantEntities;

    }

}
