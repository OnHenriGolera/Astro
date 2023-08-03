package fr.astro.test.content.human.user;

import fr.astro.dao.SQLObject;
import fr.astro.dao.human.UserDAO;
import fr.astro.entity.human.UserEntity;
import fr.astro.test.specialized.DeletionTest;
import fr.astro.util.HumanGenerator;

/**
 * UserDeletion
 * 
 * Test the deletion of users
 * 
 * @see UserCreation
 * @deprecated
 */
@Deprecated
public class UserDeletion extends DeletionTest<UserEntity> {

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
