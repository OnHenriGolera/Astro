package src.fr.astro.test;

import java.util.ArrayList;
import java.util.List;

import src.fr.astro.dao.SQLObject;

/**
 * CreationTester
 * 
 * Help making tests classes to test generations
 * 
 * @see Tester
 */
public abstract class CreationTester<T> extends Tester {

    protected String testName = "CreationTester";


    // Utils
    protected List<T> objects;
    protected List<T> objectsFromDatabase;
    private final int numberOfObjects = 10;

    /**
     * Run the test
     * - Generate objects
     * - Save them in the database
     * - Store them in a list
     */
    @Override
    public void run() throws Exception {

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
     * Generate an object
     * 
     * @return an object
     * @throws Exception if an error occured
     */
    public abstract T generateObject() throws Exception;

    /**
     * Get the DAO to the object T
     * 
     * @return the DAO instance
     */
    public abstract SQLObject<T> getDao();

    /**
     * Validate the test
     * - Check if the number of objects is the same
     * - Check if the objects are the same
     */
    @Override
    public void validate() throws Exception {

        // Fetch objects from database
        objectsFromDatabase = getDao().getAll();

        // Check if the number of objects is the same
        if (objectsFromDatabase.size() != numberOfObjects) {
            throw new Exception("❌ The number of objects is not the same");
        }

        // Check if the objects are the same
        for (int i = 0; i < numberOfObjects; i++) {

            if (!objects.get(i).equals(objectsFromDatabase.get(i))) {
                throw new Exception("❌ The objects are not the same");
            }

        }

        System.out.println("✅ " + testName + " validated.");

    }

    /**
     * Display the test
     * - Display all objects
     */
    @Override
    public void display() throws Exception {

        System.out.println("-------------------");
        System.out.println("Objects from database");

        for (T object : objectsFromDatabase) {
            System.out.println(object);
        }

        System.out.println("-------------------");

    }

}