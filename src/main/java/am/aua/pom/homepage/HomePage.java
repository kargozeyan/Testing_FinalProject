package am.aua.pom.homepage;

import am.aua.pom.BasePOM;
import am.aua.pom.header.Header;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

public class HomePage extends BasePOM {
    private final Header header;

    public HomePage(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
        this.header = new Header(driver, wait);
    }

    public Header getHeader() {
        return header;
    }
}
