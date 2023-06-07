package fr.astro.test.content.participant;

import fr.astro.dao.SQLObject;
import fr.astro.dao.human.ParticipantDAO;
import fr.astro.entity.human.ParticipantEntity;
import fr.astro.test.specialized.SQLObjectTest;
import fr.astro.util.Generator;

public class ParticipantFullTest extends SQLObjectTest<ParticipantEntity> {

    private final boolean display = true;
    private Generator generatorInstance = Generator.getInstance();

    public static void main(String[] args) throws Exception {

        ParticipantFullTest test = new ParticipantFullTest();

        test.test(test.display);

    }

    public ParticipantFullTest() {

        testName = "ParticipantFullTest";

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

    @Override
    public void modifyObject(ParticipantEntity object) {
        
        // Modify the object
        object.setPersonName(generatorInstance.generateName());
        object.setPersonSurname(generatorInstance.generateSurname());

    }
    
}
