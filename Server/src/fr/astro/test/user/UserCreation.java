package src.fr.astro.test.user;

import src.fr.astro.dao.SQLObject;
import src.fr.astro.dao.human.UserDAO;
import src.fr.astro.entity.human.UserEntity;
import src.fr.astro.test.CreationTester;
import src.fr.astro.test.Tester;
import src.fr.astro.util.Generator;

/**
 * UserTest
 * 
 * Test the UserDAO
 * 
 * @see Tester
 */
public class UserCreation extends CreationTester<UserEntity> {

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

        UserCreation test = new UserCreation();

        test.test(display);

    }

    /**
     * Constructor
     * 
     * Set the test name
     */
    public UserCreation() {

        testName = "UserCreation";

    }

    /**
     * Generate an object
     * 
     * @return the generated object
     * @throws Exception
     * @see Generator
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
