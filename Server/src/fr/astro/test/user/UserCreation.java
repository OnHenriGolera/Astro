package fr.astro.test.user;

import fr.astro.dao.SQLObject;
import fr.astro.dao.human.UserDAO;
import fr.astro.entity.human.UserEntity;
import fr.astro.test.Test;
import fr.astro.test.specialized.CreationTest;
import fr.astro.util.Generator;

/**
 * UserTest
 * 
 * Test the UserDAO
 * 
 * @see Test
 */
public class UserCreation extends CreationTest<UserEntity> {

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
