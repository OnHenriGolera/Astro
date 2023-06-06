package fr.astro.test.content.participant;

import fr.astro.dao.SQLObject;
import fr.astro.dao.human.ParticipantDAO;
import fr.astro.entity.human.ParticipantEntity;
import fr.astro.test.Test;
import fr.astro.test.specialized.CreationTest;
import fr.astro.util.Generator;

/**
 * ParticipantTest
 * 
 * Test the ParticipantDAO
 * 
 * @see Test
 */
public class ParticipantCreation extends CreationTest<ParticipantEntity> {

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

        ParticipantCreation test = new ParticipantCreation();

        test.test(display);

    }

    /**
     * Constructor
     * 
     * Set the test name
     */
    public ParticipantCreation() {

        testName = "ParticipantCreation";

    }

    /**
     * Generate an object
     * 
     * @return the generated object
     * @throws Exception
     * @see Generator
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
