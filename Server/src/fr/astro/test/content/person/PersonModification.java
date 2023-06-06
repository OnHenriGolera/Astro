package fr.astro.test.content.person;

import fr.astro.dao.SQLObject;
import fr.astro.dao.human.PersonDAO;
import fr.astro.entity.human.PersonEntity;
import fr.astro.test.specialized.ModificationTest;
import fr.astro.util.Generator;

/**
 * PersonModification
 * 
 * Test the modification of Person
 * 
 * @see PersonCreation
 */
public class PersonModification extends ModificationTest<PersonEntity> {

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

        PersonModification test = new PersonModification();

        test.test(display);

    }

    /**
     * Constructor
     * 
     * Set the test name
     */
    public PersonModification() {

        testName = "PersonModification";

    }

    /**
     * Modify the object
     * 
     * Change the password, name and surname
     * 
     * @param object - the object to modify
     * @see PersonEntity
     * @see Generator
     */
    @Override
    public void modifyObject(PersonEntity object) {

        // Modify the object
        object.setPersonName(generatorInstance.generateName());
        object.setPersonSurname(generatorInstance.generateSurname());

    }

    /**
     * Generate a new person
     * 
     * @return a new person
     * @throws Exception if an error occurs
     * @see Generator
     */
    @Override
    public PersonEntity generateObject() throws Exception {

        // Create a new person
        PersonEntity person = generatorInstance.generatePersonEntity();

        return person;

    }

    /**
     * Get the DAO
     * 
     * @return the DAO
     * @see PersonDAO
     */
    @Override
    public SQLObject<PersonEntity> getDao() {

        return PersonDAO.getInstance();

    }

}
