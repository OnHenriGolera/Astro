package fr.astro.gui;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Page
 * 
 * Return a page in HTML
 */
public class PageGetter {

    /**
     * Return a page in HTML with the page name
     * @param page
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public static String getPage(String page, FreeMarkerInitializer.Lang lang) throws IOException, TemplateException {

        return getPage(page, new HashMap<>(), lang);

    }

    /**
     * Return a page in HTML with the page name and the input
     * @param page
     * @param input - HashMap with the input
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public static String getPage(String page, Map<String, Object> input, FreeMarkerInitializer.Lang lang)
            throws IOException, TemplateException {

        Configuration configuration = FreeMarkerInitializer.getConfiguration(lang);

        Writer output = new StringWriter();
        Template template;
        
        template = configuration.getTemplate(page + ".ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);
        return output.toString();
        
    }
    
    /**
     * Return the invalid template page
     * 
     * @return the invalid template page
     * @throws IOException
     * @throws TemplateException
     */
    public static String getInvalidPage(FreeMarkerInitializer.Lang lang) throws IOException, TemplateException {

        return getPage("Default Light/redirect/invalidTemplate", lang);

    }

}
