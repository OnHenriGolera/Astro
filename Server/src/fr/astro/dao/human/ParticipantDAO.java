package fr.astro.dao.human;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.astro.dao.SQLObject;
import fr.astro.dao.database.Connector;
import fr.astro.entity.human.ParticipantEntity;
import fr.astro.entity.human.PersonEntity;
import fr.astro.exception.sql.ObjectNotFound;
import fr.astro.util.Instantiable;

/**
 * ParticipantDAO
 * 
 * DAO for ParticipantEntity
 * 
 * @see ParticipantEntity
 * @see SQLObject
 */
public class ParticipantDAO implements SQLObject<ParticipantEntity>, Instantiable {

    // Instances
    private static ParticipantDAO instance;
    private static Connection connection;

    /* --------------- Query Information --------------- */
    private final String TABLE_NAME = "Participant";

    // Columns
    private final String COLUMN_ID = "participantId";
    private final String COLUMN_PERSON_ID = "personId";
    private final String COLUMN_CATEGORY = "category";
    private final String COLUMN_PRESENT = "present";

    // Queries
    private final String INSERT_QUERY = String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES (?, ?, ?, ?)", TABLE_NAME,
            COLUMN_ID, COLUMN_PERSON_ID, COLUMN_CATEGORY, COLUMN_PRESENT);
    private final String UPDATE_QUERY = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ? WHERE %s = ?", TABLE_NAME,
            COLUMN_PERSON_ID, COLUMN_CATEGORY, COLUMN_PRESENT, COLUMN_ID);
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
    private ParticipantDAO() {
        connection = Connector.getInstance();
    }

    /**
     * Return the instance of ParticipantDAO
     * Create it if it doesn't exist
     * 
     * @return the instance of ParticipantDAO
     * @see Instantiable
     */
    public static ParticipantDAO getInstance() {
        if (instance == null) {
            instance = new ParticipantDAO();
        }
        return instance;
    }

    @Override
    public boolean save(ParticipantEntity object) throws ObjectNotFound, SQLException {

        if (object == null) {
            throw new ObjectNotFound("ParticipantEntity", "null");
        }

        if (exist(object)) {
            return update(object);
        }

        PersonDAO.getInstance().save(object);

        PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
        statement.setInt(1, object.getParticipantId());
        statement.setInt(2, object.getPersonId());
        statement.setString(3, object.getParticipantCategory());
        statement.setBoolean(4, object.isParticipantPresent());
        statement.executeUpdate();

        return true;

    }

    @Override
    public boolean update(ParticipantEntity object) throws ObjectNotFound, SQLException {

        if (object == null) {
            throw new ObjectNotFound("ParticipantEntity", "null");
        }

        if (!exist(object)) {
            throw new ObjectNotFound("ParticipantEntity", object.getParticipantId());
        }

        PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
        statement.setInt(1, object.getPersonId());
        statement.setString(2, object.getParticipantCategory());
        statement.setBoolean(3, object.isParticipantPresent());
        statement.setInt(4, object.getParticipantId());
        statement.executeUpdate();

        return true;

    }

    @Override
    public boolean delete(ParticipantEntity object) throws ObjectNotFound, SQLException {

        if (object == null) {
            throw new ObjectNotFound("ParticipantEntity", "null");
        }

        if (!exist(object)) {
            throw new ObjectNotFound("ParticipantEntity", object.getParticipantId());
        }

        PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
        statement.setInt(1, object.getParticipantId());
        statement.executeUpdate();

        // Delete as PersonEntity
        PersonDAO.getInstance().delete(object);

        return true;
    }

    @Override
    public ParticipantEntity get(int id) throws ObjectNotFound, SQLException {

        if (!exist(id)) {
            throw new ObjectNotFound("ParticipantEntity", id);
        }

        PreparedStatement statement = connection.prepareStatement(GET_QUERY);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            int personId = result.getInt(COLUMN_PERSON_ID);
            String category = result.getString(COLUMN_CATEGORY);
            boolean present = result.getBoolean(COLUMN_PRESENT);

            PersonEntity person = PersonDAO.getInstance().get(personId);
            String name = person.getPersonName();
            String surname = person.getPersonSurname();

            return ParticipantEntity.of(personId, name, surname, id, category, present);

        }

        // Never reached because of the exist(id) check
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
    public boolean exist(ParticipantEntity object) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(EXIST_QUERY);
        statement.setInt(1, object.getParticipantId());
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            return result.getInt(1) == 1;
        }

        return false;
    }

    @Override
    public List<ParticipantEntity> getAll() throws SQLException {
        return getAll(-1);
    }

    @Override
    public List<ParticipantEntity> getAll(int limit) throws SQLException {

        if (limit < 0) {
            limit = +Integer.MAX_VALUE;
        }

        List<ParticipantEntity> participants = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(GET_ALL_LIMIT_QUERY);
        statement.setInt(1, limit);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            try {
                int personId = result.getInt(COLUMN_PERSON_ID);
                String category = result.getString(COLUMN_CATEGORY);
                boolean present = result.getBoolean(COLUMN_PRESENT);

                PersonEntity person = PersonDAO.getInstance().get(personId);
                String name = person.getPersonName();
                String surname = person.getPersonSurname();

                participants.add(ParticipantEntity.of(personId, name, surname, personId, category, present));
            } catch (ObjectNotFound e) {
                e.printStackTrace();
            }
        }

        return participants;
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
    public ParticipantEntity copyObject(ParticipantEntity object) throws ObjectNotFound {

        if (object == null) {
            throw new ObjectNotFound("ParticipantEntity", "null");
        }

        return ParticipantEntity.of(object.getPersonId(), object.getPersonName(), object.getPersonSurname(),
                object.getParticipantId(), object.getParticipantCategory(), object.isParticipantPresent());
    }

}
