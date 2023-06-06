package fr.astro.test.content.role;

import fr.astro.dao.SQLObject;
import fr.astro.dao.human.RoleDAO;
import fr.astro.entity.human.RoleEntity;
import fr.astro.test.specialized.ModificationTest;
import fr.astro.util.Generator;

/**
 * UserModification
 * 
 * Test the modification of users
 * 
 * @see RoleCreation
 */
public class RoleModification extends ModificationTest<RoleEntity> {

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

        RoleModification test = new RoleModification();

        test.test(display);

    }

    /**
     * Constructor
     * 
     * Set the test name
     */
    public RoleModification() {

        testName = "UserModification";

    }

    /**
     * Modify the object
     * 
     * Change the password, name and surname
     * 
     * @param object - the object to modify
     * @see UserEntity
     * @see Generator
     */
    @Override
    public void modifyObject(RoleEntity object) {

        // Modify the object
        // object.setPersonName(generatorInstance.generateName());
        // object.setPersonSurname(generatorInstance.generateSurname());
        // object.setParticipantPresent(generatorInstance.generatePresent());

    }

    /**
     * Generate a new user
     * 
     * @return a new user
     * @throws Exception if an error occurs
     * @see Generator
     */
    @Override
    public RoleEntity generateObject() throws Exception {

        // Create a new user
        RoleEntity user = generatorInstance.generateRoleEntity();

        return user;

    }

    /**
     * Get the DAO
     * 
     * @return the DAO
     * @see UserDAO
     */
    @Override
    public SQLObject<RoleEntity> getDao() {

        return RoleDAO.getInstance();

    }

}
