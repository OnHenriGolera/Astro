package fr.astro.test.content.person;

import fr.astro.dao.SQLObject;
import fr.astro.dao.human.PersonDAO;
import fr.astro.entity.human.PersonEntity;
import fr.astro.test.specialized.ExistingTest;
import fr.astro.util.Generator;

/**
 * PersonExisting
 * 
 * Test the existence of persons
 * @see PersonCreation
 * @see ExistingTest
 */
public class PersonExisting extends ExistingTest<PersonEntity> {

    // Instances
    private Generator generatorInstance = Generator.getInstance();
    private final static boolean display = true;

    /**
     * Main
     * 
     * @param args
     */
    public static void main(String[] args) throws Exception {

        PersonExisting test = new PersonExisting();

        test.test(display);

    }
    
    /**
     * Constructor
     * 
     * Set the test name
     */
    public PersonExisting() {

        testName = "PersonExisting";

    }

    @Override
    public PersonEntity generateObject() throws Exception {

        return generatorInstance.generatePersonEntity();

    }

    @Override
    public SQLObject<PersonEntity> getDao() {
        return PersonDAO.getInstance();
    }

}
