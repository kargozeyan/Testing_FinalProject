package am.aua.dataprovider;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Map;

public class BrowserProvider {

    public static final Map<String, Capabilities> browsers = Map.of(
            "chrome", new ChromeOptions(),
            "firefox", new FirefoxOptions(),
            "edge", new EdgeOptions()
    );
}
