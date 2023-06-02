package src.fr.astro.test;

/**
 * Tester
 * 
 * Help making tests classes
 */
public abstract class Tester {

    /**
     * Init the test
     * 
     * @return void
     */
    public abstract void init() throws Exception;

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
     * Remove all the test data
     * 
     * @return void
     * @throws Exception if an error occured
     */
    public abstract void clean() throws Exception;

    /**
     * Run the test
     * 
     * @return void
     */
    public void test(boolean display) {

        try {
            init();
            run();
            validate();
            if (display) {
                display();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Clean, even if an error occured
        try {
            clean();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
