package fr.astro.test.specialized;

public abstract class DeletionTest<T> extends CreationTest<T> {

    protected String testName = "DeletionTest";

    @Override
    public void run() throws Exception {

        super.run();

        // Delete the objects
        for (T object : objects) {
            getDao().delete(object);
        }

        // Get the objects from the database
        objectsFromDatabase = getDao().getAll();

    }

    /**
     * Validate the test
     * - Check if the objects are deleted
     */
    @Override
    public void validate() throws Exception {

        // Check if the objects are deleted
        if (objectsFromDatabase.size() != 0) {
            throw new Exception("❌ The objects are not deleted (expected : 0, got : " + objectsFromDatabase.size() + ")");
        }

        System.out.println("✅ " + testName + " validated.");

    }

}
