package fr.astro.test.specialized;

import java.util.ArrayList;
import java.util.List;

import fr.astro.dao.SQLObject;
import fr.astro.exception.test.TestFailed;
import fr.astro.test.Test;

public abstract class SQLObjectTest<T> extends Test {

    protected String testName = "SQLObjectTest";

    // Utils
    protected List<T> objects;
    protected List<T> objectsFromDatabase;
    private final int numberOfObjects = 10;

    /**
     * Generate an object
     * 
     * @return an object
     */
    public abstract T generateObject() throws Exception;

    /**
     * Get the id of an object
     * 
     * @param object the object
     * @return the id of the object
     */
    public abstract int getIdFromObject(T object);

    /**
     * Get the DAO
     * 
     * @return the DAO
     */
    public abstract SQLObject<T> getDao();

    /**
     * Modify an object
     * 
     * @param object the object
     * @return void
     */
    public abstract void modifyObject(T object);

    /**
     * Fill the database
     * 
     * @return void
     * @throws Exception
     */
    public void fillDatabase() throws Exception {

        // Initialize the list
        objects = new ArrayList<T>();

        T currentObject;

        // Generate objects
        for (int i = 0; i < numberOfObjects; i++) {

            // Generate an object
            currentObject = generateObject();

            // Store the object
            objects.add(currentObject);

            // Save the object in the database
            getDao().save(currentObject);

        }

    }

    /**
     * Validate the test existence
     * - Check if the objects are in the database
     * 
     * @return true if the test succeed
     * @throws Exception if an error occured
     */
    public boolean validateExistence() throws Exception {

        // Check if the objects are in the database
        for (T object : objects) {
            if (!getDao().exist(object)) {
                throw new TestFailed("❌ The object " + object + " is not in the database");
            }
        }

        return true;

    }

    /**
     * Validate the test get
     * - Check if the objects are the same
     * 
     * @return true if the test succeed
     * @throws Exception if an error occured
     */
    public boolean validateGet() throws Exception {

        // Check if the objects are the same
        for (T object : objects) {
            if (!getDao().get(getIdFromObject(object)).equals(object)) {
                throw new TestFailed("❌ The object " + object + " is not the same as the object from the dao " + getDao().get(getIdFromObject(object)));
            }
        }

        return true;

    }

    /**
     * Validate the test getAll
     * - Check if the objects are the same
     * 
     * @return true if the test succeed
     * @throws Exception if an error occured
     */
    public boolean validateGetAll() throws Exception {

        List<T> objectsImported = getDao().getAll();

        // Check if the objects are the same
        for (T object : objects) {
            if (!objectsImported.contains(object)) {
                throw new TestFailed("❌ The object " + object + " is not in the database");
            }
        }

        return objectsImported.size() == objects.size();

    }

    /**
     * Validate the test delete
     * - Check if the objects are not in the database
     *
     * @return true if the test succeed
     * @throws Exception if an error occured
     */
    public boolean validateDelete() throws Exception {

        // Delete the objects
        for (T object : objects) {
            getDao().delete(object);
        }

        // Check if the objects are not in the database
        for (T object : objects) {
            if (getDao().exist(object)) {
                throw new TestFailed("❌ The object " + object + " is still in the database");
            }
        }

        return true;

    }

    /**
     * Validate the test update
     * - Check if the objects are the same
     * 
     * @return true if the test succeed
     * @throws Exception if an error occured
     */
    public boolean validateUpdate() throws Exception {

        // Update the objects
        for (T object : objects) {
            modifyObject(object);
            getDao().update(object);
        }

        // Check if the objects are the same
        for (T object : objects) {
            if (!getDao().get(getIdFromObject(object)).equals(object)) {
                throw new TestFailed("❌ The object " + object + " is not the same as the object from the dao " + getDao().get(getIdFromObject(object)));
            }
        }

        return true;
    }

    /**
     * Validate the test
     * 
     * @return void
     * @throws Exception if an error occured
     */
    @Override
    public void validate() throws Exception {

        // Check if the objects are in the database
        if (validateExistence()) {
            System.out.println("✅ Existence test succeed");
        }

        // Check if the objects are the same
        if (validateGet()) {
            System.out.println("✅ Get test succeed");
        }

        // Check if the objects are the same (getAll)
        if (validateGetAll()) {
            System.out.println("✅ GetAll test succeed");
        }

        // Check the update
        if (validateUpdate()) {
            System.out.println("✅ Update test succeed");
        }

        // Check the delete
        if (validateDelete()) {
            System.out.println("✅ Delete test succeed");
        }

    }

    /**
     * Display the test
     * 
     * @return void
     * @throws Exception if an error occured
     */
    @Override
    public void display() throws Exception {

        objectsFromDatabase = getDao().getAll();

        System.out.println("Test : " + testName);

        // Display the objects
        System.out.println("Objects : ");
        for (T object : objects) {
            System.out.println(object);
        }

        // Display the objects from the database
        System.out.println("Objects from the database : ");
        for (T object : objectsFromDatabase) {
            System.out.println(object);
        }

    }

    /**
     * Run the test
     * 
     * @return void
     * @throws Exception if an error occured
     */
    @Override
    public void run() throws Exception {

        // Fill the database
        fillDatabase();

    }

    /**
     * Run the test
     * 
     * @return void
     * @return void
     * @throws Exception if an error occured
     */
    @Override
    public void test(boolean display) throws TestFailed {

        boolean succeed = true;

        try {

            // Initialize the test
            init();

            // Run the test
            run();

            // Display the test
            if (display) {
                display();
            }

            // Validate the test
            validate();


        } catch (Exception e) {
            e.printStackTrace();
            succeed = false;
        }

        try {
            // Restore the database
            clean();
        } catch (Exception e) {
            e.printStackTrace();
            succeed = false;
        }

        if (!succeed) {
            System.out.println("❌ The test " + testName + " failed");
        }

    }

}
