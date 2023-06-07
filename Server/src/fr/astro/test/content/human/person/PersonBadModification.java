package fr.astro.test.content.human.person;

import fr.astro.dao.SQLObject;
import fr.astro.dao.human.PersonDAO;
import fr.astro.entity.human.PersonEntity;
import fr.astro.test.specialized.BadModificationTest;
import fr.astro.util.HumanGenerator;

/**
 * PersonModification
 * 
 * Test the modification of persons
 * 
 * @see PersonCreation
 * @deprecated
 */
public class PersonBadModification extends BadModificationTest<PersonEntity> {

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

        PersonModification test = new PersonModification();

        test.test(display);

    }

    /**
     * Constructor
     * 
     * Set the test name
     */
    public PersonBadModification() {

        testName = "PersonModification";

    }

    /**
     * Modify the object
     * 
     * Change the password, name and surname
     * 
     * @param object - the object to modify
     * @see PersonEntity
     * @see HumanGenerator
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
     * @see HumanGenerator
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
