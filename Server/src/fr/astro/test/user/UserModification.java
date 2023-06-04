package fr.astro.test.user;

import fr.astro.dao.SQLObject;
import fr.astro.dao.human.UserDAO;
import fr.astro.entity.human.UserEntity;
import fr.astro.test.specialized.ModificationTest;
import fr.astro.util.Generator;

/**
 * UserModification
 * 
 * Test the modification of users
 * 
 * @see UserCreation
 */
public class UserModification extends ModificationTest<UserEntity> {

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

        UserModification test = new UserModification();

        test.test(display);

    }

    /**
     * Constructor
     * 
     * Set the test name
     */
    public UserModification() {

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
    public void modifyObject(UserEntity object) {

        // Modify the object
        object.setUserPassword(generatorInstance.generatePassword());
        object.setPersonName(generatorInstance.generateName());
        object.setPersonSurname(generatorInstance.generateSurname());

    }

    /**
     * Generate a new user
     * 
     * @return a new user
     * @throws Exception if an error occurs
     * @see Generator
     */
    @Override
    public UserEntity generateObject() throws Exception {

        // Create a new user
        UserEntity user = generatorInstance.generateUserEntity();

        return user;

    }

    /**
     * Get the DAO
     * 
     * @return the DAO
     * @see UserDAO
     */
    @Override
    public SQLObject<UserEntity> getDao() {

        return UserDAO.getInstance();

    }

}
