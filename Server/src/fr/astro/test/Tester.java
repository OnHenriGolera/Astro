package src.fr.astro.test;

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
     */
    public abstract void run() throws Exception;

    /**
     * Validate the test
     * 
     * @return void
     */
    public abstract void validate() throws Exception;

    /**
     * Display the test
     * 
     * @return void
     */
    public abstract void display() throws Exception;

    /**
     * Remove all the test data
     * 
     * @return void
     */
    public abstract void clean() throws Exception;

    public void test() {

        try {
            init();
            run();
            validate();
            display();
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
