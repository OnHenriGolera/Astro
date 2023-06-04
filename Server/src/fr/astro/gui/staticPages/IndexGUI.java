package src.fr.astro.gui.staticPages;

import spark.Request;
import spark.Response;
import src.fr.astro.gui.Page;

public class IndexGUI extends Page {

    // Constant
    public static final String ftlPath = "index.ftl";

    /**
     * Constructor
     */
    private IndexGUI() {
        super(ftlPath);
    }

    /**
     * Return the instance of the Object
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
