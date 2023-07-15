package fr.astro.exception.competition;

/**
 * ObjectNotFound
 * 
 * Exception thrown when an object is not found in the database
 * 
 * @see Exception
 */
public class InvalidFFFFile extends Exception {

    /**
     * Constructor
     * 
     * @param message
     */
    public InvalidFFFFile(String message) {
        super(message);
    }

    /**
     * Constructor
     * 
     * @param file
     */
    public InvalidFFFFile(String file, String message) {
        super("File '" + file + "' is not a valid FFF file : " + message);
    }

    /**
     * Constructor
     * 
     * @param file
     * @param line
     */
    public InvalidFFFFile(String file, int line, String message) {
        super("File '" + file + "' is not a valid FFF file at line " + line + " : " + message);
    }

}

