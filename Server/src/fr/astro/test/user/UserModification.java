package src.fr.astro.test.user;

import src.fr.astro.dao.SQLObject;
import src.fr.astro.dao.human.UserDAO;
import src.fr.astro.entity.human.UserEntity;
import src.fr.astro.test.ModificationTester;
import src.fr.astro.util.Generator;

/**
 * UserModification
 * 
 * Test the modification of users
 * 
 * @see UserCreation
 */
public class UserModification extends ModificationTester<UserEntity> {

    // Instances
    private Generator generatorInstance = Generator.getInstance();

    /**
     * Main
     * 
     * @param args
     */
    public static void main(String[] args) {

        UserModification test = new UserModification();

        test.test();

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
