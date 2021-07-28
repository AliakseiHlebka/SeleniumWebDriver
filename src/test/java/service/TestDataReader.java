package service;

import java.util.ResourceBundle;

public class TestDataReader {

    public static final String PROPERTIES_FILE_KEY = "environment";
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty(PROPERTIES_FILE_KEY));

    public static String getTestData(String key) {
        return resourceBundle.getString(key);
    }
}
