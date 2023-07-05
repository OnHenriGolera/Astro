package fr.astro.test.content.human.person;

import fr.astro.dao.SQLObject;
import fr.astro.dao.human.PersonDAO;
import fr.astro.entity.human.PersonEntity;
import fr.astro.test.specialized.GetterTest;
import fr.astro.util.HumanGenerator;

/**
 * PersonGetter
 * 
 * Test the getter of persons
 * 
 * @deprecated
 */
@Deprecated
public class PersonGetter extends GetterTest<PersonEntity> {

    // Instances
    private HumanGenerator generatorInstance = HumanGenerator.getInstance();
    private final static boolean display = true;

    /**
     * Main
     * 
     * @param args
     */
    public static void main(String[] args) throws Exception {

        PersonGetter test = new PersonGetter();

        test.test(display);

    }

    /**
     * Constructor
     * 
     * Set the test name
     */
    public PersonGetter() {

        testName = "PersonGetter";

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

}
