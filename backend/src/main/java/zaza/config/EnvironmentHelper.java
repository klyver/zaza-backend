package zaza.config;

public class EnvironmentHelper {

    public static boolean isDevelopment() {
        return System.getProperty("environment").equals("development");
    }

    public static boolean isProduction() {
        return System.getProperty("environment").equals("us") || System.getProperty("environment").equals("china");
    }


}
