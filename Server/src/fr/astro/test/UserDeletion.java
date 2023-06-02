package src.fr.astro.test;

import src.fr.astro.dao.human.UserDAO;
import src.fr.astro.entity.human.UserEntity;

/**
 * UserDeletion
 * 
 * Test the deletion of users
 * @see UserCreation
 */
public class UserDeletion extends UserCreation {

    /**
     * Main
     * @param args
     */
    public static void main(String[] args) {

        UserDeletion userDeletion = new UserDeletion();
        userDeletion.test();

    }
    
    /**
     * Run the test
     * - Call the super method (create users) @see UserCreation
     * - Delete the users
     */
    @Override
    public void run() throws Exception {

        super.run();

        // Delete the users
        for (UserEntity user : users) {
            UserDAO.getInstance().delete(user);
        }

        // Get the users from the database
        usersFromDatabase = UserDAO.getInstance().getAll();

    }

    /**
     * Validate the test
     * - Check if the users are deleted
     */
    @Override
    public void validate() throws Exception {

        // Check if the users are deleted
        if (usersFromDatabase.size() != 0) {
            throw new Exception("The users are not deleted");
        }

        System.out.println("✅ UserDeletion validated.");

    }

    /**
     * Display the test
     * - Display all users (normally none)
     */
    @Override
    public void display() throws Exception {

        System.out.println("--------------------");
        System.out.println("✨ Test : UserDeletion");

        // Display all users
        for (UserEntity user : usersFromDatabase) {
            System.out.println(user);
        }

        System.out.println("--------------------");

    }
    
}
