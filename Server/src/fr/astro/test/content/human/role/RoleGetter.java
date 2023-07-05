package fr.astro.test.content.human.role;

import fr.astro.dao.SQLObject;
import fr.astro.dao.human.RoleDAO;
import fr.astro.entity.human.RoleEntity;
import fr.astro.test.specialized.GetterTest;
import fr.astro.util.HumanGenerator;

/**
 * RoleGetter
 * 
 * Test the getter of roles
 * 
 * @deprecated
 */
@Deprecated
public class RoleGetter extends GetterTest<RoleEntity> {

    // Instances
    private HumanGenerator generatorInstance = HumanGenerator.getInstance();
    private final static boolean display = true;

    /**
     * Main
     * 
     * @param args
     */
    public static void main(String[] args) throws Exception {

        RoleGetter test = new RoleGetter();

        test.test(display);

    }

    /**
     * Constructor
     * 
     * Set the test name
     */
    public RoleGetter() {

        testName = "RoleGetter";

    }

    @Override
    public RoleEntity generateObject() throws Exception {

        return generatorInstance.generateRoleEntity();

    }

    @Override
    public int getIdFromObject(RoleEntity object) {

        return object.getRoleId();

    }

    @Override
    public SQLObject<RoleEntity> getDao() {

        return RoleDAO.getInstance();

    }

}
