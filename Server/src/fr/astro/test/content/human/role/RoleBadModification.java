package fr.astro.test.content.human.role;

import fr.astro.dao.SQLObject;
import fr.astro.dao.human.RoleDAO;
import fr.astro.entity.human.RoleEntity;
import fr.astro.test.specialized.BadModificationTest;
import fr.astro.util.HumanGenerator;

/**
 * RoleModification
 * 
 * Test the modification of roles
 * 
 * @see RoleCreation
 * @deprecated
 */
public class RoleBadModification extends BadModificationTest<RoleEntity> {

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

        RoleModification test = new RoleModification();

        test.test(display);

    }

    /**
     * Constructor
     * 
     * Set the test name
     */
    public RoleBadModification() {

        testName = "RoleModification";

    }

    /**
     * Modify the object
     * 
     * Change the password, name and surname
     * 
     * @param object - the object to modify
     * @see RoleEntity
     * @see HumanGenerator
     */
    @Override
    public void modifyObject(RoleEntity object) {

        // Modify the object
        object.setRoleAccessLevel(generatorInstance.generateRoleAccessLevel());
        object.setRoleName(generatorInstance.generateRoleName());

    }

    /**
     * Generate a new role
     * 
     * @return a new role
     * @throws Exception if an error occurs
     * @see HumanGenerator
     */
    @Override
    public RoleEntity generateObject() throws Exception {

        // Create a new role
        RoleEntity role = generatorInstance.generateRoleEntity();

        return role;

    }

    /**
     * Get the DAO
     * 
     * @return the DAO
     * @see RoleDAO
     */
    @Override
    public SQLObject<RoleEntity> getDao() {

        return RoleDAO.getInstance();

    }

}
