package fr.astro.test.content.human.role;

import fr.astro.dao.SQLObject;
import fr.astro.dao.human.RoleDAO;
import fr.astro.entity.human.RoleEntity;
import fr.astro.test.specialized.ExistingTest;
import fr.astro.util.HumanGenerator;

/**
 * RoleExisting
 * 
 * Test the existence of roles
 * 
 * @see RoleCreation
 * @see ExistingTest
 * @deprecated
 */
@Deprecated
public class RoleExisting extends ExistingTest<RoleEntity> {

    // Instances
    private HumanGenerator generatorInstance = HumanGenerator.getInstance();
    private final static boolean display = true;

    /**
     * Main
     * 
     * @param args
     */
    public static void main(String[] args) throws Exception {

        RoleExisting test = new RoleExisting();

        test.test(display);

    }

    /**
     * Constructor
     * 
     * Set the test name
     */
    public RoleExisting() {

        testName = "RoleExisting";

    }

    @Override
    public RoleEntity generateObject() throws Exception {

        return generatorInstance.generateRoleEntity();

    }

    @Override
    public SQLObject<RoleEntity> getDao() {
        return RoleDAO.getInstance();
    }

}
