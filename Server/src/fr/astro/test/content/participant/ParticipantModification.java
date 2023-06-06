package fr.astro.test.content.participant;

import fr.astro.dao.SQLObject;
import fr.astro.dao.human.ParticipantDAO;
import fr.astro.dao.human.UserDAO;
import fr.astro.entity.human.ParticipantEntity;
import fr.astro.test.specialized.ModificationTest;
import fr.astro.util.Generator;

/**
 * UserModification
 * 
 * Test the modification of users
 * 
 * @see ParticipantCreation
 */
public class ParticipantModification extends ModificationTest<ParticipantEntity> {

    // Instances
    private Generator generatorInstance = Generator.getInstance();

    // Display
    private final static boolean display = true;

    /**
     * Main
     * 
     * @param args
     */
    public static void main(String[] args) throws Exception {

        ParticipantModification test = new ParticipantModification();

        test.test(display);

    }

    /**
     * Constructor
     * 
     * Set the test name
     */
    public ParticipantModification() {

        testName = "UserModification";

    }

    /**
     * Modify the object
     * 
     * Change the password, name and surname
     * 
     * @param object - the object to modify
     * @see UserEntity
     * @see Generator
     */
    @Override
    public void modifyObject(ParticipantEntity object) {

        // Modify the object
        object.setPersonName(generatorInstance.generateName());
        object.setPersonSurname(generatorInstance.generateSurname());
        object.setParticipantPresent(generatorInstance.generatePresent());

    }

    /**
     * Generate a new user
     * 
     * @return a new user
     * @throws Exception if an error occurs
     * @see Generator
     */
    @Override
    public ParticipantEntity generateObject() throws Exception {

        // Create a new user
        ParticipantEntity user = generatorInstance.generateParticipantEntity();

        return user;

    }

    /**
     * Get the DAO
     * 
     * @return the DAO
     * @see UserDAO
     */
    @Override
    public SQLObject<ParticipantEntity> getDao() {

        return ParticipantDAO.getInstance();

    }

}
