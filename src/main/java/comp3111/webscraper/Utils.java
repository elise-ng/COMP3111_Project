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
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("win") || os.contains("mac")) {
            if(Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)){
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(new URI(url));
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open " + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
