package fr.astro.test.content.human.participant;

import fr.astro.dao.SQLObject;
import fr.astro.dao.human.ParticipantDAO;
import fr.astro.entity.human.ParticipantEntity;
import fr.astro.test.specialized.ExistingTest;
import fr.astro.util.HumanGenerator;

/**
 * ParticipantExisting
 * 
 * Test the existence of participants
 * @see ParticipantCreation
 * @see ExistingTest
 * @deprecated
 */
@Deprecated
public class ParticipantExisting extends ExistingTest<ParticipantEntity> {

    // Instances
    private HumanGenerator generatorInstance = HumanGenerator.getInstance();
    private final static boolean display = true;

    /**
     * Main
     * 
     * @param args
     */
    public static void main(String[] args) throws Exception {

        ParticipantExisting test = new ParticipantExisting();

        test.test(display);

    }
    
    /**
     * Constructor
     * 
     * Set the test name
     */
    public ParticipantExisting() {

        testName = "ParticipantExisting";

    }

    @Override
    public ParticipantEntity generateObject() throws Exception {

        return generatorInstance.generateParticipantEntity();

    }

    @Override
    public SQLObject<ParticipantEntity> getDao() {
        return ParticipantDAO.getInstance();
    }

}
