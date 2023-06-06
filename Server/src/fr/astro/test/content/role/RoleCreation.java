package fr.astro.test.content.role;

import fr.astro.dao.SQLObject;
import fr.astro.dao.human.RoleDAO;
import fr.astro.entity.human.RoleEntity;
import fr.astro.test.Test;
import fr.astro.test.specialized.CreationTest;
import fr.astro.util.Generator;

/**
 * roleTest
 * 
 * Test the roleDAO
 * 
 * @see Test
 */
public class RoleCreation extends CreationTest<RoleEntity> {

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

        RoleCreation test = new RoleCreation();

        test.test(display);

    }

    /**
     * Constructor
     * 
     * Set the test name
     */
    public RoleCreation() {

        testName = "roleCreation";

    }

    /**
     * Generate an object
     * 
     * @return the generated object
     * @throws Exception
     * @see Generator
     */
    @Override
    public RoleEntity generateObject() throws Exception {

        RoleEntity role = generatorInstance.generateRoleEntity();

        return role;

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
