package fr.astro.test.content.user;

import fr.astro.dao.SQLObject;
import fr.astro.dao.human.UserDAO;
import fr.astro.entity.human.UserEntity;
import fr.astro.test.specialized.SQLObjectTest;
import fr.astro.util.Generator;

public class UserFullTest extends SQLObjectTest<UserEntity> {

    private final boolean display = true;
    private Generator generatorInstance = Generator.getInstance();

    public static void main(String[] args) throws Exception {

        UserFullTest test = new UserFullTest();

        test.test(test.display);

    }

    public UserFullTest() {

        testName = "UserFullTest";

    }

    @Override
    public UserEntity generateObject() throws Exception {

        return generatorInstance.generateUserEntity();

    }

    @Override
    public int getIdFromObject(UserEntity object) {
        
        return object.getUserId();

    }

    @Override
    public SQLObject<UserEntity> getDao() {
        
        return UserDAO.getInstance();

    }

    @Override
    public void modifyObject(UserEntity object) {
        
        // Modify the object
        object.setUserPassword(generatorInstance.generatePassword());
        object.setPersonName(generatorInstance.generateName());
        object.setPersonSurname(generatorInstance.generateSurname());

    }
    
}
