package src.fr.astro.test;

import java.util.ArrayList;
import java.util.List;

import src.fr.astro.dao.human.UserDAO;
import src.fr.astro.entity.human.UserEntity;

/**
 * UserModification
 * 
 * Test the modification of users
 * @see UserCreation
 */
public class UserModification extends UserCreation {

    // Copy of the users (to compare before / after)
    private List<UserEntity> copyUsers;

    /**
     * Main
     * @param args
     */
    public static void main(String[] args) {

        UserModification userModification = new UserModification();
        userModification.test();

    }

    /**
     * Initialize the test
     * - Initialize like UserCreation
     * - Initialize copyUsers
     * 
     * @see UserCreation
     * @throws Exception
     */
    @Override
    public void init() throws Exception {

        super.init();

        // Get the users from the database
        copyUsers = new ArrayList<>();

    }

    /**
     * Run the test
     * - Run like UserCreation
     * - Modify the users
     * 
     * @see UserCreation
     * @throws Exception
     */
    @Override
    public void run() throws Exception {

        super.run();

        // Make a copy of the currentUsers
        copyList(users, copyUsers);

        // Modify the users
        for (UserEntity user : users) {

            // Modify the user : generate a new name
            user.setPersonName(generatorInstance.generateName());

            // Modify the user : generate a new surname
            user.setPersonSurname(generatorInstance.generateSurname());

            // Update the user in the database
            UserDAO.getInstance().update(user);

        }

    }

    /**
     * Validate the test
     * - Validate like UserCreation
     * - Check if the users have been modified
     * 
     * @see UserCreation
     * @throws Exception
     */
    @Override
    public void validate() throws Exception {

        super.validate();

        // Check if the users have been modified
        for (int i = 0; i < users.size(); i++) {

            // Check if the name has been modified
            if (users.get(i).getPersonName().equals(copyUsers.get(i).getPersonName())) {
                throw new Exception("❌ The name of the user " + users.get(i).getPersonId() + " has not been modified");
            }

            // Check if the surname has been modified
            if (users.get(i).getPersonSurname().equals(copyUsers.get(i).getPersonSurname())) {
                throw new Exception(
                        "❌ The surname of the user " + users.get(i).getPersonId() + " has not been modified");
            }

        }

        System.out.println("✅ UserModification validated.");

    }

    /**
     * Display the test
     * - Display the users (before / after)
     * 
     * @throws Exception
     */
    @Override
    public void display() throws Exception {

        System.out.println("--------------------");
        System.out.println("✨ Test : UserModification");

        // Display the users (before / after)
        System.out.println("Users (before - after) :");

        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i) + " - " + copyUsers.get(i));
        }

        System.out.println("--------------------");

    }

    /**
     * Copy a list of users
     * 
     * @param original - the original list
     * @param copy     - where the copy will be stored (modified)
     */
    private void copyList(List<UserEntity> original, List<UserEntity> copy) {

        for (UserEntity user : original) {
            copy.add(UserEntity.Of(user));
        }

    }

}
