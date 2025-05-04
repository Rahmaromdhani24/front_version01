package Front_java.ConfigurationLangue;

import java.util.Locale;
import java.util.ResourceBundle;

public class LangManager {

    private static Locale currentLocale = new Locale("fr");
    private static ResourceBundle bundle = ResourceBundle.getBundle("lang", currentLocale);

    public static void setLocale(Locale locale) {
        currentLocale = locale;
        bundle = ResourceBundle.getBundle("lang", currentLocale);
    }

    public static Locale getLocale() {
        return currentLocale;
    }

    public static ResourceBundle getBundle() {
        return bundle;
    }
}