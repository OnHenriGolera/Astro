package src.fr.astro.test;

import java.util.ArrayList;
import java.util.List;

public abstract class ModificationTester<T> extends CreationTester<T> {

    protected String testName = "DeletionTester";

    protected List<T> objectCopy;

    @Override
    public void init() throws Exception {

        super.init();

        // Initialize the copy list
        objectCopy = new ArrayList<>();

    }

    @Override
    public void run() throws Exception {

        super.run();

        // Create a copy of the objects
        copyList(objects, objectCopy);

        // Modify the objects
        for (T object : objects) {
            
            // Modify the object
            modifyObject(object);

            // Update the object in the database
            getDao().update(object);
        }

    }

    /**
     * Modify an object
     * 
     * @param object - the object to modify
     */
    public abstract void modifyObject(T object);

    /**
     * Validate the test
     * - Check if the objects are deleted
     */
    @Override
    public void validate() throws Exception {

        // Get the objects from the database
        objectsFromDatabase = getDao().getAll();

        // Check if the objects are deleted
        if (objectsFromDatabase.size() != objects.size()) {
            throw new Exception("❌ The objects are not modified");
        }

        // Check if the objects are modified
        for (int i = 0; i < objects.size(); i++) {

            // Get the object
            T object = objects.get(i);
            T objectFromDatabase = objectsFromDatabase.get(i);

            // Check if the object is modified
            if (!object.equals(objectFromDatabase)) {
                throw new Exception("❌ The objects are not modified");
            }

        }

        System.out.println("✅ " + testName + " validated.");

    }

    @Override
    public void display() throws Exception {

        System.out.println("-------------------");
        System.out.println("Objects from database");

        // Display same line "before - after"
        for (int i = 0; i < objects.size(); i++) {

            // Get the object
            T object = objects.get(i);
            T objectCopy = this.objectCopy.get(i);

            // Display the object
            System.out.println(objectCopy + " - " + object);

        }

        System.out.println("-------------------");


    }

    /**
     * Copy a list of object
     * 
     * @param original - the original list
     * @param copy     - where the copy will be stored (modified)
     */
    private void copyList(List<T> original, List<T> copy) {

        // If copy is null, create a new list
        if (copy == null) {
            copy = new ArrayList<>();
        }

        // Clear the copy list
        copy.clear();

        for (T object : original) {
            try {
                copy.add(getDao().copyObject(object));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
