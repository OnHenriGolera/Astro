package fr.astro.test.content.participant;

import fr.astro.dao.SQLObject;
import fr.astro.dao.human.ParticipantDAO;
import fr.astro.entity.human.ParticipantEntity;
import fr.astro.test.specialized.ModificationTest;
import fr.astro.util.Generator;

/**
 * ParticipantModification
 * 
 * Test the modification of Participant
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

        testName = "ParticipantModification";

    }

    /**
     * Modify the object
     * 
     * Change the password, name and surname
     * 
     * @param object - the object to modify
     * @see ParticipantEntity
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
     * Generate a new participant
     * 
     * @return a new participant
     * @throws Exception if an error occurs
     * @see Generator
     */
    @Override
    public ParticipantEntity generateObject() throws Exception {

        // Create a new participant
        ParticipantEntity participant = generatorInstance.generateParticipantEntity();

        return participant;

    }

    /**
     * Get the DAO
     * 
     * @return the DAO
     * @see ParticipantDAO
     */
    @Override
    public SQLObject<ParticipantEntity> getDao() {

        return ParticipantDAO.getInstance();

    }

}
