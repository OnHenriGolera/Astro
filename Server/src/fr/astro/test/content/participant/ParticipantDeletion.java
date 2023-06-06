package fr.astro.test.content.participant;

import fr.astro.dao.SQLObject;
import fr.astro.dao.human.ParticipantDAO;
import fr.astro.entity.human.ParticipantEntity;
import fr.astro.test.specialized.DeletionTest;
import fr.astro.util.Generator;

/**
 * UserDeletion
 * 
 * Test the deletion of users
 * 
 * @see ParticipantCreation
 */
public class ParticipantDeletion extends DeletionTest<ParticipantEntity> {

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

        ParticipantDeletion test = new ParticipantDeletion();

        test.test(display);

    }

    /**
     * Constructor
     * 
     * Set the test name
     */
    public ParticipantDeletion() {

        testName = "UserDeletion";

    }

    /**
     * Generate an object
     * 
     * @return the generated object
     * @throws Exception
     */
    @Override
    public ParticipantEntity generateObject() throws Exception {

        ParticipantEntity user = generatorInstance.generateParticipantEntity();

        return user;

    }

    /**
     * Get the DAO
     * 
     * @return the DAO
     */
    @Override
    public SQLObject<ParticipantEntity> getDao() {
        

        return ParticipantDAO.getInstance();

    }

}
