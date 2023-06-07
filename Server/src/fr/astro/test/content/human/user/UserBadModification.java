package fr.astro.test.content.human.user;

import fr.astro.dao.SQLObject;
import fr.astro.dao.human.UserDAO;
import fr.astro.entity.human.UserEntity;
import fr.astro.test.specialized.BadModificationTest;
import fr.astro.util.HumanGenerator;

/**
 * UserModification
 * 
 * Test the modification of users
 * 
 * @see UserCreation
 * @deprecated
 */
public class UserBadModification extends BadModificationTest<UserEntity> {

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

        UserModification test = new UserModification();

        test.test(display);

    }

    /**
     * Constructor
     * 
     * Set the test name
     */
    public UserBadModification() {

        testName = "UserModification";

    }

    /**
     * Modify the object
     * 
     * Change the password, name and surname
     * 
     * @param object - the object to modify
     * @see UserEntity
     * @see HumanGenerator
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
     * @see HumanGenerator
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
