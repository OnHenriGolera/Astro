package src.fr.astro.test;

import java.util.ArrayList;
import java.util.List;

import src.fr.astro.dao.Initializer;
import src.fr.astro.dao.RoleDAO;
import src.fr.astro.dao.UserDAO;
import src.fr.astro.entity.UserEntity;
import src.fr.astro.util.Generator;

/**
 * UserTest
 * 
 * Test the UserDAO
 */
public class UserCreation extends Tester {

    // Backup file
    private final String backupFile = System.getProperty("user.home") + "/bdd_astro";
    private final String testFile = System.getProperty("user.home") + "/bdd_astro_test";
    
    // To generate data
    private Generator generatorInstance = Generator.getInstance();
    
    // Utils
    protected List<UserEntity> users;
    protected List<UserEntity> usersFromDatabase;
    private int numberOfUsers = 10;

    /**
     * Main
     * 
     * @param args
     */
    public static void main(String[] args) {

        UserCreation userTest = new UserCreation();
        userTest.test();

    }

    @Override
    public void init() throws Exception {

        // Store make a copy of the database
        Initializer.Backup(backupFile);

        // Create a new database
        Initializer.Load(testFile);

        // Initialize the database
        Initializer.Init();

    }

    @Override
    public void run() throws Exception {

        users = new ArrayList<UserEntity>();

        UserEntity currentUser;

        // Generate users
        for (int i = 0; i < numberOfUsers; i++) {

            currentUser = generatorInstance.generateUserEntity();

            UserDAO.getInstance().save(currentUser);

            users.add(currentUser);

        }


    }

    @Override
    public void validate() throws Exception {

        // Fetch all users
        usersFromDatabase = UserDAO.getInstance().getAll();

        // Check if the number of users is correct
        if (usersFromDatabase.size() != numberOfUsers) {
            throw new Exception(
                    "❌ The number of users is incorrect : " + usersFromDatabase.size() + "/" + numberOfUsers + "%n");
        }

        // Check if the users are correct
        for (UserEntity user : users) {

            if (!usersFromDatabase.contains(user)) {
                throw new Exception("❌ The user " + user + " is not in the database." + usersFromDatabase.size() + "/"
                        + numberOfUsers + "%n");
            }

        }

        System.out.println("✅ UserCreation validated.");

    }

    @Override
    public void display() throws Exception {

        System.out.println("--------------------");
        System.out.println("✨ Test : UserTest");

        // Display all users
        for (UserEntity user : usersFromDatabase) {
            System.out.println(user);
        }

        System.out.println("--------------------");

    }

    @Override
    public void clean() throws Exception {

        // Delete the current database
        Initializer.Drop();

        // Restore the database
        Initializer.Load(backupFile);

    }

}
