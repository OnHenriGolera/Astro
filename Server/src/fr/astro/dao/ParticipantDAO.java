package src.fr.astro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.fr.astro.entity.ParticipantEntity;
import src.fr.astro.entity.PersonEntity;
import src.fr.astro.exception.sql.ObjectNotFound;

/**
 * ParticipantDAO
 * 
 * DAO for ParticipantEntity
 * @see ParticipantEntity
 * @see SQLObject
 */
public class ParticipantDAO implements SQLObject<ParticipantEntity> {

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
    private final String INSERT_QUERY = String.format("INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?)", TABLE_NAME,
            COLUMN_PERSON_ID, COLUMN_CATEGORY, COLUMN_PRESENT);
    private final String UPDATE_QUERY = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ? WHERE %s = ?", TABLE_NAME,
            COLUMN_PERSON_ID, COLUMN_CATEGORY, COLUMN_PRESENT, COLUMN_ID);
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
    private ParticipantDAO() {
        connection = Connector.getInstance();
    }

    /**
     * Return the instance of ParticipantDAO
     * Create it if it doesn't exist
     * @return the instance of ParticipantDAO
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

        if (!exist(object)) {
            throw new ObjectNotFound("ParticipantEntity", object.getParticipantId());
        }

        PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
        statement.setInt(1, object.getPersonId());
        statement.setString(2, object.getParticipantCategory());
        statement.setBoolean(3, object.isParticipantPresent());
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
        statement.setInt(3, object.getParticipantId());
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
    
}
