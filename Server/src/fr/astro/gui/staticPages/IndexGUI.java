package fr.astro.gui.staticPages;

import spark.Request;
import spark.Response;
import fr.astro.gui.Page;
import fr.astro.util.Instantiable;

public class IndexGUI extends Page implements Instantiable {

    // Constant
    public static final String ftlPath = "index.ftl";

    // Instance
    private static Page instance;

    /**
     * Constructor
     */
    private IndexGUI() {
        super(ftlPath);
    }

    /**
     * Return the instance of the Object
     * 
     * @return the instance of the Object
     */
    public static Page getInstance() {
        if (instance == null) {
            instance = new IndexGUI();
        }

        return instance;
    }

    @Override
    protected boolean hasToRedirect(Request request, Response response) throws Exception {
        return false;
    }

}
