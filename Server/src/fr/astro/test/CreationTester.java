package src.fr.astro.test;

import java.util.ArrayList;
import java.util.List;

import src.fr.astro.dao.SQLObject;
import src.fr.astro.dao.database.Initializer;

/**
 * CreationTester
 * 
 * Help making tests classes to test generations
 * 
 * @see Tester
 */
public abstract class CreationTester<T> extends Tester {

    protected String testName = "CreationTester";

    // Backup file
    private final String backupFile = System.getProperty("user.home") + "/bdd_astro";
    private final String testFile = System.getProperty("user.home") + "/bdd_astro_test";

    // Utils
    protected List<T> objects;
    protected List<T> objectsFromDatabase;
    private final int numberOfObjects = 10;

    /**
     * Initialize the test
     * - Create a backup of the database
     * - Create a new database
     * - Initialize the database
     */
    @Override
    public void init() throws Exception {

        // Store make a copy of the database
        Initializer.Backup(backupFile);

        // Create a new database
        Initializer.Load(testFile);

        // Initialize the database
        Initializer.Init();

    }

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

    /**
     * Clean the test
     * - Delete the current database
     * - Restore the database
     */
    @Override
    public void clean() throws Exception {

        // Delete the current database
        Initializer.Drop();

        // Restore the database
        Initializer.Load(backupFile);

    }

}