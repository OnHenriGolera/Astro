package fr.astro.util;

import java.sql.SQLException;

import com.github.javafaker.Faker;

import fr.astro.dao.human.ParticipantDAO;
import fr.astro.dao.human.PersonDAO;
import fr.astro.dao.human.RoleDAO;
import fr.astro.entity.human.ParticipantEntity;
import fr.astro.entity.human.PersonEntity;
import fr.astro.entity.human.RoleEntity;
import fr.astro.entity.human.UserEntity;

public class HumanGenerator implements Instantiable {

    private static HumanGenerator instance;
    private Faker fakerInstance;

    private RoleEntity roleEntity;

    /**
     * Constructor
     */
    private HumanGenerator() {
        fakerInstance = new Faker();
    }

    /**
     * Return a Generator
     * Create a new Generator if instance is null
     * 
     * @return a Generator
     */
    public static HumanGenerator getInstance() {
        if (instance == null) {
            instance = new HumanGenerator();
        }
        return instance;
    }

    /**
     * Generate a name
     * 
     * @return a name
     */
    public String generateName() {
        return fakerInstance.name().firstName();
    }

    /**
     * Generate a surname
     * 
     * @return a surname
     */
    public String generateSurname() {
        return fakerInstance.name().lastName();
    }

    /**
     * Generate a password
     * 
     * @return a password
     */
    public String generatePassword() {
        return fakerInstance.internet().password();
    }

    /**
     * Generate a role
     * 
     * @return a role
     */
    public String generateRoleName() {
        return "role"; // TODO
    }

    /**
     * Generate a role access level
     * 
     * @return a role access level
     */
    public int generateRoleAccessLevel() {
        return 0; // TODO
    }

    /**
     * Generate a category
     *
     * @return a category
     */
    public String generateCategory() {
        return "category"; // TODO
    }

    /**
     * Generate a present
     *
     * @return a present
     */
    public boolean generatePresent() {
        return true; // TODO
    }

    
    /**
     * Generate PersonEntity
     * 
     * @return a PersonEntity
     */
    public PersonEntity generatePersonEntity() throws SQLException {
        return PersonEntity.of(PersonDAO.getInstance().getLastInsertedId() + 1, generateName(), generateSurname());
    }

    /**
     * Generate a RoleEntity
     * 
     * @return a RoleEntity
     */
    public RoleEntity generateRoleEntity() throws SQLException {
        return RoleEntity.of(RoleDAO.getInstance().getLastInsertedId() + 1, generateRoleName(),
                generateRoleAccessLevel());
    }

    /**
     * Generate a RoleEntity
     * Create a new RoleEntity if instance is null
     * 
     * @return a RoleEntity
     */
    public RoleEntity getRoleEntity() throws SQLException {
        if (roleEntity == null) {
            try {
                // Check if a role exists
                if (RoleDAO.getInstance().getLastInsertedId() != -1) {
                    roleEntity = RoleDAO.getInstance().get(RoleDAO.getInstance().getLastInsertedId());
                } else {
                    roleEntity = generateRoleEntity();

                    // Save the role
                    RoleDAO.getInstance().save(roleEntity);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return roleEntity;
    }

    /**
     * Generate a UserEntity
     * 
     * @return a UserEntity
     */
    public UserEntity generateUserEntity() throws SQLException {
        // First, generate a PersonEntity
        PersonEntity personEntity = generatePersonEntity();

        // Then, generate a RoleEntity
        RoleEntity roleEntity = getRoleEntity();

        // Finally, generate a UserEntity
        return UserEntity.of(
                personEntity.getPersonId(),
                personEntity.getPersonName(),
                personEntity.getPersonSurname(),
                personEntity.getPersonId(),
                generatePassword(),
                roleEntity);
    }

    /**
     * Generate a ParticipantEntity
     * 
     * @return a ParticipantEntity
     *
     */
    public ParticipantEntity generateParticipantEntity() throws SQLException {

        // First, generate a PersonEntity
        PersonEntity personEntity = generatePersonEntity();

        // Then, generate a ParticipantEntity
        return ParticipantEntity.of(
                personEntity.getPersonId(),
                personEntity.getPersonName(),
                personEntity.getPersonSurname(),
                ParticipantDAO.getInstance().getLastInsertedId() + 1,
                generateCategory(),
                generatePresent());
    }

}
