package fr.astro.test.content.human.role;

import fr.astro.dao.SQLObject;
import fr.astro.dao.human.RoleDAO;
import fr.astro.entity.human.RoleEntity;
import fr.astro.test.specialized.SQLObjectTest;
import fr.astro.util.HumanGenerator;

public class RoleFullTest extends SQLObjectTest<RoleEntity> {

    private final boolean display = true;
    private HumanGenerator generatorInstance = HumanGenerator.getInstance();

    public static void main(String[] args) throws Exception {

        RoleFullTest test = new RoleFullTest();

        test.test(test.display);

    }

    public RoleFullTest() {

        testName = "RoleFullTest";

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

    @Override
    public void modifyObject(RoleEntity object) {

        // Modify the object
        object.setRoleAccessLevel(generatorInstance.generateRoleAccessLevel());
        object.setRoleName(generatorInstance.generateRoleName());

    }

}
