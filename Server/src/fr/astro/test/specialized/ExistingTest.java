package fr.astro.test.specialized;

import fr.astro.exception.test.TestFailed;

public abstract class ExistingTest<T> extends CreationTest<T> {

    protected String testName = "ExistingTest";

    @Override
    public void validate() throws Exception {

        // For each element of objects, use the dao to check if the object exist

        for (T object : objects) {

            // Check if the object exist
            if (!getDao().exist(object)) {

                throw new TestFailed("❌ The object " + object + " does not exist");

            }

        }

        System.out.println("✅ The test " + testName + " succeed");

    }

    @Override
    public void display() throws Exception {

        // Show each object with its existence
        for (T object : objects) {

            // Check if the object exist
            if (getDao().exist(object)) {
                System.out.println("✅ The object " + object + " exist");
            } else {
                System.out.println("❌ The object " + object + " does not exist");
            }

        }

    }

}
