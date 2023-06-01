package src.fr.astro.test;

import src.fr.astro.dao.UserDAO;
import src.fr.astro.entity.UserEntity;

public class UserDeletion extends UserCreation {

    public static void main(String[] args) {

        UserDeletion userDeletion = new UserDeletion();
        userDeletion.test();

    }
    
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

    @Override
    public void validate() throws Exception {

        // Check if the users are deleted
        if (usersFromDatabase.size() != 0) {
            throw new Exception("The users are not deleted");
        }

        System.out.println("✅ UserDeletion validated.");

    }

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
