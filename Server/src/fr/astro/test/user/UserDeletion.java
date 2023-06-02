package src.fr.astro.test.user;

import src.fr.astro.dao.SQLObject;
import src.fr.astro.dao.human.UserDAO;
import src.fr.astro.entity.human.UserEntity;
import src.fr.astro.test.DeletionTest;
import src.fr.astro.util.Generator;

/**
 * UserDeletion
 * 
 * Test the deletion of users
 * 
 * @see UserCreation
 */
public class UserDeletion extends DeletionTest<UserEntity> {

    // Instances
    private Generator generatorInstance = Generator.getInstance();
    
    // Display
    private final static boolean display = true;


    /**
     * Main
     * 
     * @param args
     */
    public static void main(String[] args) {

        UserDeletion test = new UserDeletion();

        test.test(display);

    }

    /**
     * Constructor
     * 
     * Set the test name
     */
    public UserDeletion() {

        testName = "UserDeletion";

    }

    /**
     * Generate an object
     * 
     * @return the generated object
     * @throws Exception
     */
    @Override
    public UserEntity generateObject() throws Exception {

        UserEntity user = generatorInstance.generateUserEntity();

        return user;

    }

    /**
     * Get the DAO
     * 
     * @return the DAO
     */
    @Override
    public SQLObject<UserEntity> getDao() {

        return UserDAO.getInstance();

    }

}
