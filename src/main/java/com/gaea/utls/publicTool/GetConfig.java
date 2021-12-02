package com.gaea.utls.publicTool;

import java.util.Locale;
import java.util.ResourceBundle;

public class GetConfig {

    public static String getApplication(String param){
        ResourceBundle bundle;
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        return bundle.getString(param);
    }
}
