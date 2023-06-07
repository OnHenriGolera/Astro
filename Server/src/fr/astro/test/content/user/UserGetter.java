package fr.astro.test.content.user;

import fr.astro.dao.SQLObject;
import fr.astro.dao.human.UserDAO;
import fr.astro.entity.human.UserEntity;
import fr.astro.test.specialized.GetterTest;
import fr.astro.util.Generator;

public class UserGetter extends GetterTest<UserEntity> {

    // Instances
    private Generator generatorInstance = Generator.getInstance();
    private final static boolean display = true;

    /**
     * Main
     * 
     * @param args
     */
    public static void main(String[] args) throws Exception {

        UserGetter test = new UserGetter();

        test.test(display);

    }

    /**
     * Constructor
     * 
     * Set the test name
     */
    public UserGetter() {

        testName = "UserGetter";

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

}
