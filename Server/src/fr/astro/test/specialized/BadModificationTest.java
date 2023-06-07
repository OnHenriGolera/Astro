package fr.astro.test.specialized;

import java.util.ArrayList;
import java.util.List;

/**
 * BadModificationTest
 * 
 * Test if the objects are modified
 * 
 * @deprecated
 */
@Deprecated
public abstract class BadModificationTest<T> extends CreationTest<T> {

    protected String testName = "DeletionTest";
    protected List<T> objectCopy;

    @Override
    public void run() throws Exception {

        super.run();

        objectCopy = new ArrayList<>();

        // Create a copy of the objects
        copyList(objects, objectCopy);

        // Modify the objects
        for (T object : objects) {

            // Duplicate the object
            T objectCopy = getDao().copyObject(object);

            // Modify the object
            modifyObject(objectCopy);

            // Update the object in the database
            getDao().update(objectCopy);
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
            throw new Exception("❌ The objects are not modified (expected : " + objects.size() + ", got : "
                    + objectsFromDatabase.size() + ")");
        }

        // Check if the objects are modified
        for (int i = 0; i < objects.size(); i++) {

            // Check if the object is contained in the database
            if (objectsFromDatabase.contains(objects.get(i))) {
                throw new Exception("❌ The object " + objects.get(i) + " is not modified");
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
