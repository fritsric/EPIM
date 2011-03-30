package cz.cvut.indepmod.classmodel.resources;

import java.util.Locale;
import java.util.ResourceBundle;

public class Resources {

    private static final ResourceBundle bundle = ResourceBundle.getBundle("cz.cvut.indepmod.classmodel.Bundle", Locale.getDefault());

    public static String getString(String key) {
        if (! bundle.containsKey(key)) {
            return "{<"+ key +">}";
        } else {
            return bundle.getString(key);
        }
    }

}
