package fr.astro.test.content.human.person;

import fr.astro.dao.SQLObject;
import fr.astro.dao.human.PersonDAO;
import fr.astro.entity.human.PersonEntity;
import fr.astro.test.specialized.SQLObjectTest;
import fr.astro.util.HumanGenerator;

public class PersonFullTest extends SQLObjectTest<PersonEntity> {

    private final boolean display = true;
    private HumanGenerator generatorInstance = HumanGenerator.getInstance();

    public static void main(String[] args) throws Exception {

        PersonFullTest test = new PersonFullTest();

        test.test(test.display);

    }

    public PersonFullTest() {

        testName = "PersonFullTest";

    }

    @Override
    public PersonEntity generateObject() throws Exception {

        return generatorInstance.generatePersonEntity();

    }

    @Override
    public int getIdFromObject(PersonEntity object) {
        
        return object.getPersonId();

    }

    @Override
    public SQLObject<PersonEntity> getDao() {
        
        return PersonDAO.getInstance();

    }

    @Override
    public void modifyObject(PersonEntity object) {
        
        // Modify the object
        object.setPersonName(generatorInstance.generateName());
        object.setPersonSurname(generatorInstance.generateSurname());

    }
    
}
