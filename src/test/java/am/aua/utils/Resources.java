package am.aua.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Resources {
    public static final String ABSOLUTE_PATH = "src" + File.separator +
                                               "test" + File.separator +
                                               "resources" + File.separator;


    public static void saveScreenshot(byte[] screenshot, String name) {
        try {
            Files.write(Paths.get(ABSOLUTE_PATH + "screenshots" + File.separator + name), screenshot);
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }
    }
}