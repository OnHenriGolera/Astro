package src.fr.astro.gui;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import src.fr.astro.Server;

import java.util.Locale;

/**
 * FreeMarkerInitializer
 * 
 * Initialize FreeMarker
 */
public class FreeMarkerInitializer {

    /**
     * Return the configuration of FreeMarker
     * @return
     */
    public static Configuration getContext() {
        // Configure FreeMarker
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
        configuration.setClassForTemplateLoading(Server.class, "/views");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setLocale(Locale.FRANCE);
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        return configuration;
    }
}
