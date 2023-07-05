package fr.astro.test.specialized;

import fr.astro.exception.test.TestFailed;

/**
 * GetterTest
 * 
 * Test if the objects are get
 * 
 * @deprecated
 */
@Deprecated
public abstract class GetterTest<T> extends CreationTest<T> {

    protected String testName = "GetterTest";

    @Override
    public void validate() throws Exception {

        objectsFromDatabase = getDao().getAll();

        // For each element of objects, use the dao to get the object
        for (T object : objects) {

            // Get the object
            T objectFromDao = getDao().get(getIdFromObject(object));

            // Check if the object exist
            if (objectFromDao == null) {

                throw new TestFailed("❌ The object " + object + " does not exist");

            }

            // Check if the object is the same
            if (!object.equals(objectFromDao)) {

                throw new TestFailed(
                        "❌ The object " + object + " is not the same as the object from the dao " + objectFromDao);

            }

        }

        System.out.println("✅ The test " + testName + " succeed");

    }

    /**
     * Get the id from an object of type T
     * 
     * @param object
     * @return
     */
    public abstract int getIdFromObject(T object);

}
