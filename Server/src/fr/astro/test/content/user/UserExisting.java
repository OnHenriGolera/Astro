package fr.astro.test.content.user;

import fr.astro.dao.SQLObject;
import fr.astro.dao.human.UserDAO;
import fr.astro.entity.human.UserEntity;
import fr.astro.test.specialized.ExistingTest;
import fr.astro.util.Generator;

/**
 * UserExisting
 * 
 * Test the existence of users
 * @see UserCreation
 * @see ExistingTest
 */
public class UserExisting extends ExistingTest<UserEntity> {

    // Instances
    private Generator generatorInstance = Generator.getInstance();
    private final static boolean display = true;

    /**
     * Main
     * 
     * @param args
     */
    public static void main(String[] args) throws Exception {

        UserExisting test = new UserExisting();

        test.test(display);

    }
    
    /**
     * Constructor
     * 
     * Set the test name
     */
    public UserExisting() {

        testName = "UserExisting";

    }

    @Override
    public UserEntity generateObject() throws Exception {

        return generatorInstance.generateUserEntity();

    }

    @Override
    public SQLObject<UserEntity> getDao() {
        return UserDAO.getInstance();
    }

}
