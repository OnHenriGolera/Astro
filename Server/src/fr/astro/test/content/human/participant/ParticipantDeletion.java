package fr.astro.test.content.human.participant;

import fr.astro.dao.SQLObject;
import fr.astro.dao.human.ParticipantDAO;
import fr.astro.entity.human.ParticipantEntity;
import fr.astro.test.specialized.DeletionTest;
import fr.astro.util.HumanGenerator;

/**
 * ParticipantDeletion
 * 
 * Test the deletion of Participant
 * 
 * @see ParticipantCreation
 * @deprecated
 */
@Deprecated
public class ParticipantDeletion extends DeletionTest<ParticipantEntity> {

    // Instances
    private HumanGenerator generatorInstance = HumanGenerator.getInstance();
    
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

        testName = "ParticipantDeletion";

    }

    /**
     * Generate an object
     * 
     * @return the generated object
     * @throws Exception
     */
    @Override
    public ParticipantEntity generateObject() throws Exception {

        ParticipantEntity participant = generatorInstance.generateParticipantEntity();

        return participant;

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
