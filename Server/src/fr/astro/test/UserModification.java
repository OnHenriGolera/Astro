package src.fr.astro.test;

import java.util.ArrayList;
import java.util.List;

import src.fr.astro.dao.human.UserDAO;
import src.fr.astro.entity.human.UserEntity;

public class UserModification extends UserCreation {

    private List<UserEntity> copyUsers;

    public static void main(String[] args) {

        UserModification userModification = new UserModification();
        userModification.test();

    }
    
    @Override
    public void init() throws Exception {

        super.init();

        // Get the users from the database
        copyUsers = new ArrayList<>();

    }
    
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
                throw new Exception("❌ The surname of the user " + users.get(i).getPersonId() + " has not been modified");
            }

        }

        System.out.println("✅ UserModification validated.");

    }

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

    private void copyList(List<UserEntity> original, List<UserEntity> copy) {

        for (UserEntity user : original) {
            copy.add(UserEntity.Of(user));
        }

    }
    
}
