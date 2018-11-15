package comp3111.webscraper;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Utility for application-wide helper methods
 */
public class Utils {

    /**
     * Correctly open URLs in the default browser on different desktop environments, OS dependent
     * @param url The URL to be opened
     */
    public static void openURL(String url) {
        try {
            new ProcessBuilder("x-www-browser", url).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
