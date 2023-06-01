package src.fr.astro.test;

import java.util.ArrayList;
import java.util.List;

import src.fr.astro.dao.Initializer;
import src.fr.astro.dao.RoleDAO;
import src.fr.astro.dao.UserDAO;
import src.fr.astro.entity.RoleEntity;
import src.fr.astro.entity.UserEntity;
import src.fr.astro.util.Generator;

public class UserTest extends Tester {

    private final String backupFile = "~/astro_db_backup.db";
    private Generator generatorInstance = Generator.getInstance();

    private List<UserEntity> users;
    private List<UserEntity> usersFromDatabase;
    private int numberOfUsers = 10;

    public static void main(String[] args) {

        UserTest userTest = new UserTest();
        userTest.test();

    }

    @Override
    public void init() throws Exception {

        // Store make a copy of the database
        Initializer.Backup(backupFile);

        // Delete the current database
        Initializer.Drop();

        // Create a new database
        Initializer.Init();

    }

    @Override
    public void run() throws Exception {

        users = new ArrayList<UserEntity>();

        // Create a role
        RoleEntity role = generatorInstance.generateRoleEntity();

        // Insert the role
        RoleDAO.getInstance().save(role);

        UserEntity currentUser;

        // Generate users
        for (int i = 0; i < numberOfUsers; i++) {

            currentUser = generatorInstance.generateUserEntity();

            users.add(currentUser);

        }

        // Insert users
        for (UserEntity user : users) {

            System.out.println("Inserting " + user + "...");

            UserDAO.getInstance().save(user);

        }

    }

    @Override
    public void validate() throws Exception {

        // Fetch all users
        usersFromDatabase = UserDAO.getInstance().getAll();

        // Check if the number of users is correct
        if (usersFromDatabase.size() != numberOfUsers) {
            throw new Exception(
                    "❌ The number of users is incorrect : " + usersFromDatabase.size() + "/" + numberOfUsers);
        }

        // Check if the users are correct
        for (UserEntity user : users) {

            if (!usersFromDatabase.contains(user)) {
                throw new Exception("❌ The user " + user + " is not in the database." + usersFromDatabase.size() + "/"
                        + numberOfUsers);
            }

        }

        System.out.println("✅ UserTest validated.");

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
