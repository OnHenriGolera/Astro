package fr.astro.gui;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import fr.astro.Server;

import java.util.HashMap;
import java.util.Locale;

/**
 * FreeMarkerInitializer
 * 
 * Initialize FreeMarker
 */
public class FreeMarkerInitializer {

    public enum Lang {
        FR(Locale.FRANCE),
        EN(Locale.ENGLISH);

        // Locale
        private Locale locale;

        /**
         * Constructor
         * 
         * @param locale
         */
        Lang(Locale locale) {
            this.locale = locale;
        }

        /**
         * Return the locale
         * 
         * @return
         */
        public Locale getLocale() {
            return locale;
        }
    }

    private static HashMap<Lang, Configuration> configurations = new HashMap<>();

    private final static Lang DEFAULT_LANG = Lang.EN;

    /**
     * Return the default configuration of FreeMarker
     *
     * @return
     */
    public static Configuration getConfiguration() {
        return getConfiguration(DEFAULT_LANG);
    }
        

    /**
     * Return the configuration of FreeMarker
     * 
     * @param lang
     * @return
     */
    public static Configuration getConfiguration(Lang lang) {
        if (!configurations.containsKey(lang)) {
            configurations.put(lang, getNewConfiguration(lang));
        }

        return configurations.get(lang);
    }

    /**
     * Create a new configuration of FreeMarker
     * 
     * @return
     */
    public static Configuration getNewConfiguration(Lang lang) {
        // Configure FreeMarker
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
        configuration.setClassForTemplateLoading(Server.class, "/views");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setLocale(lang.getLocale());
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        return configuration;
    }
}
