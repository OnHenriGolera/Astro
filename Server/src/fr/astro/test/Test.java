package src.fr.astro.test;

import src.fr.astro.dao.database.Initializer;
import src.fr.astro.exception.test.TestFailed;

/**
 * Tester
 * 
 * Help making tests classes
 */
public abstract class Test {

    // Backup file
    protected final String backupFile = System.getProperty("user.home") + "/bdd_astro";
    protected final String testFile = System.getProperty("user.home") + "/bdd_astro_test";

    /**
     * Initialize the test
     * - Create a backup of the database
     * - Create a new database
     * - Initialize the database
     */
    public final void init() throws Exception {

        // Store make a copy of the database
        Initializer.Backup(backupFile);

        // Create a new database
        Initializer.Load(testFile);

        // Initialize the database
        Initializer.Init();

    }

    /**
     * Run the test
     * 
     * @return void
     * @throws Exception if an error occured
     */
    public abstract void run() throws Exception;

    /**
     * Validate the test
     * 
     * @return void
     * @throws Exception if an error occured
     */
    public abstract void validate() throws Exception;

    /**
     * Display the test
     * 
     * @return void
     * @throws Exception if an error occured
     */
    public abstract void display() throws Exception;

    /**
     * Clean the test
     * - Delete the current database
     * - Restore the database
     */
    public final void clean() throws Exception {

        // Delete the current database
        Initializer.Drop();

        // Restore the database
        Initializer.Load(backupFile);

    }

    /**
     * Run the test
     * 
     * @return void
     * @throws TestFailed
     */
    public void test(boolean display) throws TestFailed {

        boolean success = true;

        try {
            init();
            run();
            validate();
            if (display) {
                display();
            }
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }

        // Clean, even if an error occured
        try {
            clean();
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }

        if (!success) {
            throw new TestFailed("❌ test failed.");
        }

    }

}
