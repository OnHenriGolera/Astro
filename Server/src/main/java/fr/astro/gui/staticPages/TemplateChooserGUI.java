package fr.astro.gui.staticPages;

import fr.astro.gui.Page;
import fr.astro.util.Instantiable;
import spark.Request;
import spark.Response;

public class TemplateChooserGUI extends Page implements Instantiable {

    // Constant
    public static final String ftlPath = "redirect/templateChooser";

    // Instance
    private static Page instance;

    /**
     * Constructor
     */
    private TemplateChooserGUI() {
        super(ftlPath);
    }

    /**
     * Return the instance of the Object
     *
     * @return the instance of the Object
     */
    public static Page getInstance() {
        if (instance == null) {
            instance = new TemplateChooserGUI();
        }

        return instance;
    }

    @Override
    protected boolean hasToRedirect(Request request, Response response) throws Exception {
        return false;
    }

}
