package src.fr.astro.exception.sql;

public class ObjectNotFound extends Exception {

    public ObjectNotFound(String message) {
        super(message);
    }

    public ObjectNotFound(String objectName, int id) {
        super("Object '" + objectName + "' with id '" + id + "' not found");
    }

    public ObjectNotFound(String objectName, String name) {
        super("Object '" + objectName + "' with name '" + name + "' not found");
    }
    
}
