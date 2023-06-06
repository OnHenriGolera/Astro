package fr.astro.gui.staticPages;

import fr.astro.gui.Page;
import fr.astro.util.Instantiable;
import spark.Request;
import spark.Response;

public class Error404GUI extends Page implements Instantiable {

    // Constant
    public static final String ftlPath = "redirect/404.ftl";

    // Instance
    private static Page instance;

    /**
     * Constructor
     * 
     * @param name
     */
    private Error404GUI() {
        super(ftlPath);
    }

    /**
     * Return the instance of the Object
     * 
     * @return the instance of the Object
     */
    public static Page getInstance() {
        if (instance == null) {
            instance = new Error404GUI();
        }

        return instance;
    }

    @Override
    protected boolean hasToRedirect(Request request, Response response) throws Exception {
        return false;
    }

}
