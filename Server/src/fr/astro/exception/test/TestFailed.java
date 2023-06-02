package src.fr.astro.exception.test;

public class TestFailed extends Exception {
    
    /**
     * Constructor
     * @param message
     */
    public TestFailed(String message) {
        super(message);
    }

}
