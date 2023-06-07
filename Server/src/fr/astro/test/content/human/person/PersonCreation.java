package fr.astro.test.content.human.person;

import fr.astro.dao.SQLObject;
import fr.astro.dao.human.PersonDAO;
import fr.astro.entity.human.PersonEntity;
import fr.astro.test.Test;
import fr.astro.test.specialized.CreationTest;
import fr.astro.util.HumanGenerator;

/**
 * PersonTest
 * 
 * Test the PersonDAO
 * 
 * @see Test
 * @deprecated
 */
public class PersonCreation extends CreationTest<PersonEntity> {

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

        PersonCreation test = new PersonCreation();

        test.test(display);

    }

    /**
     * Constructor
     * 
     * Set the test name
     */
    public PersonCreation() {

        testName = "PersonCreation";

    }

    /**
     * Generate an object
     * 
     * @return the generated object
     * @throws Exception
     * @see HumanGenerator
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
