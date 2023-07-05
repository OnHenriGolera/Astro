package fr.astro.gui;

import java.util.HashMap;
import java.util.Map;

import fr.astro.exception.InvalidArguments;
import spark.Request;
import spark.Response;

public abstract class Page {

    // Attributes
    protected String name;
    protected String redirectPath;

    /**
     * Constructor
     * 
     * @param name
     */
    protected Page(String name) {

        this.name = name;

    }

    /**
     * Constructor
     * 
     * @param name
     * @param redirectPath
     */
    protected Page(String name, String redirectPath) {

        this.name = name;
        this.redirectPath = redirectPath;

    }

    /**
     * Render the page without any input
     * 
     * @return
     * @throws Exception
     */
    public String renderPage(Request request, Response response) throws Exception {

        return renderPage(new HashMap<>(), request, response);

    }

    /**
     * Render the page with the input
     * 
     * @param input
     * @return the page in HTML
     * @throws Exception
     */
    protected String renderPage(Map<String, Object> input, Request request, Response response) throws Exception {

        if (name == null) {
            throw new InvalidArguments("Page name is null");
        }

        // Get the lang
        FreeMarkerInitializer.Lang lang = FreeMarkerInitializer.Lang.getLang(request);

        return PageGetter.getPage(name, input, lang);

    }

    /**
     * Redirect the user if he has to be redirected
     * 
     * @param request
     * @param response
     * @return true if the user is redirected, false otherwise
     * @throws Exception
     */
    protected boolean redirectUser(Request request, Response response) throws Exception {

        // Check if the user has to be redirected
        if (!hasToRedirect(request, response)) {
            return false;
        }

        // If the redirectPath is null, redirect to "/"
        redirectPath = redirectPath == null ? "/" : redirectPath;

        // Redirect the user
        response.redirect(redirectPath, 301);

        return true;

    }

    /**
     * Return true if the user has to be redirected, false otherwise
     * 
     * @return true if the user has to be redirected, false otherwise
     */
    protected abstract boolean hasToRedirect(Request request, Response response) throws Exception;

}
