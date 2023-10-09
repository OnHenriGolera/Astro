package fr.astro.gui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.TemplateException;

/**
 * Represent a template a user can choose to personalize his website
 */
public class WebTemplate {

    private final static String defaultTheme = "Default_Light";

    // Attributes
    private final String pageName;
    private final String templateName;
    private final String author;
    private final String imgPath;

    /**
     * Constructor
     *
     * @param pageName the name of the page
     * @param name     the name of the template
     * @param author   the author of the template
     * @param imgPath  the path of the image of the template
     */
    private WebTemplate(String pageName, String name, String author, String imgPath) {

        this.templateName = name;
        this.author = author;
        this.imgPath = imgPath;
        this.pageName = pageName;

    }

    /**
     * Return a template
     *
     * @param pageI   the page name
     * @param nameI   the name of the template
     * @param author  the author of the template
     * @param imgPath the path of the image of the template
     * @return a template
     */
    public static WebTemplate getTemplate(String pageI, String nameI, String author, String imgPath) {

        return new WebTemplate(pageI, nameI, author, imgPath);

    }

    /**
     * Return a template, with the default page name
     *
     * @param nameI the name of the template
     * @param pageI the page name
     * @return
     */
    public static WebTemplate getTemplate(String pageI, String nameI) {

        return new WebTemplate(pageI, nameI, "Unknown", "/img/default.png");

    }

    /**
     * Return a page name
     *
     * @return the page name
     */
    public String getTemplatePageName() {

        return pageName;

    }

    /**
     * Return the name of the template
     *
     * @return the name of the template
     */
    public String getTemplateName() {

        return templateName;

    }

    /**
     * Return the author of the template
     *
     * @return the author of the template
     */
    public String getTemplateAuthor() {

        return author;

    }

    /**
     * Return the path of the image of the template
     *
     * @return the path of the image of the template
     */
    public String getTemplateImgPath() {

        return imgPath;

    }

    /**
     * Load the template
     *
     * @param lang the lang of the template
     * @return the template
     * @see PageGetter
     */
    public String loadTemplate(FreeMarkerInitializer.Lang lang) throws TemplateException, IOException {

        return loadTemplate(lang, new HashMap<>());

    }

    /**
     * Load the template
     *
     * @param lang  the lang of the template
     * @param input the input of the template
     * @return the template
     * @see PageGetter
     */
    public String loadTemplate(FreeMarkerInitializer.Lang lang, Map<String, Object> input)
            throws TemplateException, IOException {

        String page;

        try {

            if (templateName.isEmpty()) {

                page = PageGetter.getPage(pageName, input, lang);

            } else {

                page = PageGetter.getPage(templateName + "/" + pageName, input, lang);

            }

        } catch (Exception e) {

            // PageName is the default page in case of error
            try {

                page = PageGetter.getPage(defaultTheme + "/" + pageName, input, lang);

            } catch (Exception e2) {

                e2.printStackTrace();

                // If the default page is not found, return the invalid page
                page = PageGetter.getInvalidPage(lang);
            }

        }

        return page;

    }

    @Override
    public String toString() {

        return "Template : " + templateName + " by " + author;

    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof WebTemplate) {

            WebTemplate template = (WebTemplate) obj;

            return template.getTemplateName().equals(templateName)
                    && template.getTemplatePageName().equals(pageName)
                    && template.getTemplateAuthor().equals(author)
                    && template.getTemplateImgPath().equals(imgPath);

        }

        return false;

    }

    @Override
    public int hashCode() {

        return templateName.hashCode() + pageName.hashCode() + author.hashCode() + imgPath.hashCode();

    }

}