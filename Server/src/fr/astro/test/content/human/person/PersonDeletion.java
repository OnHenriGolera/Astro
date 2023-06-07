package fr.astro.test.content.human.person;

import fr.astro.dao.SQLObject;
import fr.astro.dao.human.PersonDAO;
import fr.astro.entity.human.PersonEntity;
import fr.astro.test.specialized.DeletionTest;
import fr.astro.util.HumanGenerator;

/**
 * PersonDeletion
 * 
 * Test the deletion of Person
 * 
 * @see PersonCreation
 * @deprecated
 */
@Deprecated
public class PersonDeletion extends DeletionTest<PersonEntity> {

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

        PersonDeletion test = new PersonDeletion();

        test.test(display);

    }

    /**
     * Constructor
     * 
     * Set the test name
     */
    public PersonDeletion() {

        testName = "PersonDeletion";

    }

    /**
     * Generate an object
     * 
     * @return the generated object
     * @throws Exception
     */
    @Override
    public PersonEntity generateObject() throws Exception {

        PersonEntity person = generatorInstance.generatePersonEntity();

        return person;

    }

    /**
     * Get the DAO
     * 
     * @return the DAO
     */
    @Override
    public SQLObject<PersonEntity> getDao() {
        

        return PersonDAO.getInstance();

    }

}
