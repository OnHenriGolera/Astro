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
import fr.astro.util.buffers.FFF;
import fr.astro.util.buffers.FileManagement;

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
    private final String COLUMN_LICENSE = "license";
    private final String COLUMN_INITIAL_LOCAL_RANKING = "initialLocalRanking";
    private final String COLUMN_INITIAL_INTERNATIONAL_RANKING = "initialInternationalRanking";
    private final String COLUMN_LEAGUE = "league";
    private final String COLUMN_CLUB = "club";
    private final String COLUMN_NATIONALITY = "nationality";

    // Queries
    private final String INSERT_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", TABLE_NAME,
            COLUMN_ID, COLUMN_PERSON_ID, COLUMN_CATEGORY, COLUMN_PRESENT, COLUMN_LICENSE, COLUMN_INITIAL_LOCAL_RANKING,
            COLUMN_INITIAL_INTERNATIONAL_RANKING, COLUMN_LEAGUE, COLUMN_CLUB, COLUMN_NATIONALITY);
    private final String UPDATE_QUERY = String.format(
            "UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?",
            TABLE_NAME,
            COLUMN_PERSON_ID, COLUMN_CATEGORY, COLUMN_PRESENT, COLUMN_LICENSE, COLUMN_INITIAL_LOCAL_RANKING,
            COLUMN_INITIAL_INTERNATIONAL_RANKING, COLUMN_LEAGUE, COLUMN_CLUB, COLUMN_NATIONALITY, COLUMN_ID);
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

        // Save as PersonEntity
        boolean success = PersonDAO.getInstance().save(object.getPersonEntity());

        PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
        statement.setInt(1, object.getParticipantId());
        statement.setInt(2, object.getPersonId());
        statement.setString(3, object.getParticipantCategory().getName());
        statement.setBoolean(4, object.isParticipantPresent());
        statement.setString(5, object.getParticipantLicense());
        statement.setInt(6, object.getParticipantInitialLocalRanking());
        statement.setInt(7, object.getParticipantInitialInternationalRanking());
        statement.setString(8, object.getParticipantLeague().getName());
        statement.setString(9, object.getParticipantClub().getName());
        statement.setString(10, object.getParticipantNationality().getName());

        success &= statement.executeUpdate() > 0;

        return success;

    }

    @Override
    public boolean update(ParticipantEntity object) throws ObjectNotFound, SQLException {

        if (object == null) {
            throw new ObjectNotFound("ParticipantEntity", "null");
        }

        if (!exist(object)) {
            throw new ObjectNotFound("ParticipantEntity", object.getParticipantId());
        }

        // Update as PersonEntity
        boolean success = PersonDAO.getInstance().update(object.getPersonEntity());

        PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
        statement.setInt(1, object.getPersonId());
        statement.setString(2, object.getParticipantCategory().getName());
        statement.setBoolean(3, object.isParticipantPresent());
        statement.setString(4, object.getParticipantLicense());
        statement.setInt(5, object.getParticipantInitialLocalRanking());
        statement.setInt(6, object.getParticipantInitialInternationalRanking());
        statement.setString(7, object.getParticipantLeague().getName());
        statement.setString(8, object.getParticipantClub().getName());
        statement.setString(9, object.getParticipantNationality().getName());
        statement.setInt(10, object.getParticipantId());

        success &= statement.executeUpdate() > 0;

        return success;

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

        boolean success = statement.executeUpdate() > 0;

        // Delete as PersonEntity
        success &= PersonDAO.getInstance().delete(object.getPersonEntity());

        return success;
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
            String license = result.getString(COLUMN_LICENSE);
            int initialLocalRanking = result.getInt(COLUMN_INITIAL_LOCAL_RANKING);
            int initialInternationalRanking = result.getInt(COLUMN_INITIAL_INTERNATIONAL_RANKING);
            String league = result.getString(COLUMN_LEAGUE);
            String club = result.getString(COLUMN_CLUB);
            String nationality = result.getString(COLUMN_NATIONALITY);

            PersonEntity person = PersonDAO.getInstance().get(personId);
            String name = person.getPersonName();
            String surname = person.getPersonSurname();
            String gender = person.getPersonGender().getName();
            String birthDate = person.getPersonBirthDate();

            return ParticipantEntity.of(personId, name, surname, gender, birthDate, id, category, present, license,
                    initialLocalRanking, initialInternationalRanking, league, club, nationality);

        }

        // Never reached because of the exist(id) check
        throw new ObjectNotFound("ParticipantEntity", id);
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
    public boolean exist(ParticipantEntity object) throws SQLException {

        if (object == null) {
            return false;
        }

        return exist(object.getParticipantId());
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
                String license = result.getString(COLUMN_LICENSE);
                int initialLocalRanking = result.getInt(COLUMN_INITIAL_LOCAL_RANKING);
                int initialInternationalRanking = result.getInt(COLUMN_INITIAL_INTERNATIONAL_RANKING);
                String league = result.getString(COLUMN_LEAGUE);
                String club = result.getString(COLUMN_CLUB);
                String nationality = result.getString(COLUMN_NATIONALITY);
                int id = result.getInt(COLUMN_ID);

                PersonEntity person = PersonDAO.getInstance().get(personId);
                String name = person.getPersonName();
                String surname = person.getPersonSurname();
                String gender = person.getPersonGender().getName();
                String birthDate = person.getPersonBirthDate();

                participants.add(
                        ParticipantEntity.of(personId, name, surname, gender, birthDate, id, category, present, license,
                                initialLocalRanking, initialInternationalRanking, league, club, nationality));
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

        PersonEntity person = object.getPersonEntity();

        return ParticipantEntity.of(person.getPersonId(),
                person.getPersonName(),
                person.getPersonSurname(),
                person.getPersonGender().getName(),
                person.getPersonBirthDate(),
                object.getParticipantId(),
                object.getParticipantCategory().getName(),
                object.isParticipantPresent(),
                object.getParticipantLicense(),
                object.getParticipantInitialLocalRanking(),
                object.getParticipantInitialInternationalRanking(),
                object.getParticipantLeague().getName(),
                object.getParticipantClub().getName(),
                object.getParticipantNationality().getName());
    }

    /**
     * Import a list of Participant from a file with .FFF format
     * 
     * @param path the path of the file
     * @return the list of Participant
     */
    public List<ParticipantEntity> importFromFFF(String path) throws Exception {

        // Initialize the list
        List<ParticipantEntity> participants = new ArrayList<>();

        FFF fff = new FFF(FileManagement.getInstance().getFile(path));

        // TODO

        return participants;

    }

}
