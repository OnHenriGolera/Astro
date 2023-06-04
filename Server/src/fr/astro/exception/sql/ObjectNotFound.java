package fr.astro.exception.sql;

/**
 * ObjectNotFound
 * 
 * Exception thrown when an object is not found in the database
 * @see Exception
 */
public class ObjectNotFound extends Exception {

    /**
     * Constructor
     * 
     * @param message
     */
    public ObjectNotFound(String message) {
        super(message);
    }

    /**
     * Constructor
     * @param objectName
     * @param id - the id of the object
     */
    public ObjectNotFound(String objectName, int id) {
        super("Object '" + objectName + "' with id '" + id + "' not found");
    }

    /**
     * Constructor
     * @param objectName
     * @param name - the name of the object
     */
    public ObjectNotFound(String objectName, String name) {
        super("Object '" + objectName + "' with name '" + name + "' not found");
    }

}
