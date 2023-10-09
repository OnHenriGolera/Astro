package fr.astro.exception.competition;

/**
 * ObjectNotFound
 * <p>
 * Exception thrown when an object is not found in the database
 *
 * @see Exception
 */
public class InvalidFFFFile extends Exception {

    /**
     * Constructor
     *
     * @param message the message
     */
    public InvalidFFFFile(String message) {
        super(message);
    }

    /**
     * Constructor
     *
     * @param file    the file
     * @param message the message
     */
    public InvalidFFFFile(String file, String message) {
        super("File '" + file + "' is not a valid FFF file : " + message);
    }

    /**
     * Constructor
     *
     * @param file the file
     */
    public InvalidFFFFile(String file, int line, String message) {
        super("File '" + file + "' is not a valid FFF file at line " + line + " : " + message);
    }

}

