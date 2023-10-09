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
     * @param name the name of the page
     */
    protected Page(String name) {

        this.name = name;

    }

    /**
     * Constructor
     *
     * @param name         the name of the page
     * @param redirectPath the path to redirect the user
     */
    protected Page(String name, String redirectPath) {

        this.name = name;
        this.redirectPath = redirectPath;

    }

    /**
     * Render the page without any input
     *
     * @param request  the http request
     * @param response the http response
     * @return the page in HTML
     * @throws Exception if the page name is null
     */
    public String renderPage(Request request, Response response) throws Exception {

        return renderPage(new HashMap<>(), request, response);

    }

    /**
     * Render the page with the input
     *
     * @param input    the input of the page
     * @param request  the http request
     * @param response the http response
     * @return the page in HTML
     * @throws Exception if the page name is null
     */
    protected String renderPage(Map<String, Object> input, Request request, Response response) throws Exception {

        if (name == null) {
            throw new InvalidArguments("Page name is null");
        }

        // Get the theme cookie
        String theme = request.cookie("theme");

        // If the theme cookie is null, set it to the page name
        if (theme == null) {
            response.cookie("theme", "");
            theme = "";
        }

        WebTemplate template = WebTemplate.getTemplate(name, theme);

        // Get the lang
        FreeMarkerInitializer.Lang lang = FreeMarkerInitializer.Lang.getLang(request);

        return template.loadTemplate(lang);

    }

    /**
     * Redirect the user if he has to be redirected
     *
     * @param request  the http request
     * @param response the http response
     * @return true if the user is redirected, false otherwise
     * @throws Exception if the page name is null
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
     * @param request  the http request
     * @param response the http response
     * @return true if the user has to be redirected, false otherwise
     */
    protected abstract boolean hasToRedirect(Request request, Response response) throws Exception;

}
