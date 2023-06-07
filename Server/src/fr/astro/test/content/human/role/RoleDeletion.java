package fr.astro.test.content.human.role;

import fr.astro.dao.SQLObject;
import fr.astro.dao.human.RoleDAO;
import fr.astro.entity.human.RoleEntity;
import fr.astro.test.specialized.DeletionTest;
import fr.astro.util.HumanGenerator;

/**
 * UserDeletion
 * 
 * Test the deletion of users
 * 
 * @see RoleCreation
 * @deprecated
 */
public class RoleDeletion extends DeletionTest<RoleEntity> {

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

        RoleDeletion test = new RoleDeletion();

        test.test(display);

    }

    /**
     * Constructor
     * 
     * Set the test name
     */
    public RoleDeletion() {

        testName = "UserDeletion";

    }

    /**
     * Generate an object
     * 
     * @return the generated object
     * @throws Exception
     */
    @Override
    public RoleEntity generateObject() throws Exception {

        RoleEntity user = generatorInstance.generateRoleEntity();

        return user;

    }

    /**
     * Get the DAO
     * 
     * @return the DAO
     */
    @Override
    public SQLObject<RoleEntity> getDao() {
        

        return RoleDAO.getInstance();

    }

}
