package fr.astro.test.content.human.participant;

import fr.astro.dao.SQLObject;
import fr.astro.dao.human.ParticipantDAO;
import fr.astro.entity.human.ParticipantEntity;
import fr.astro.test.specialized.GetterTest;
import fr.astro.util.HumanGenerator;

/**
 * ParticipantGetter
 * 
 * Test the getter of Participant
 * 
 * @deprecated
 */
@Deprecated
public class ParticipantGetter extends GetterTest<ParticipantEntity> {

    // Instances
    private HumanGenerator generatorInstance = HumanGenerator.getInstance();
    private final static boolean display = true;

    /**
     * Main
     * 
     * @param args
     */
    public static void main(String[] args) throws Exception {

        ParticipantGetter test = new ParticipantGetter();

        test.test(display);

    }

    /**
     * Constructor
     * 
     * Set the test name
     */
    public ParticipantGetter() {

        testName = "ParticipantGetter";

    }

    @Override
    public ParticipantEntity generateObject() throws Exception {

        return generatorInstance.generateParticipantEntity();

    }

    @Override
    public int getIdFromObject(ParticipantEntity object) {

        return object.getParticipantId();

    }

    @Override
    public SQLObject<ParticipantEntity> getDao() {

        return ParticipantDAO.getInstance();

    }

}
