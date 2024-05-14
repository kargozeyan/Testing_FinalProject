package am.aua.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

public abstract class BasePOM {
    protected WebDriver driver;
    protected Wait<WebDriver> wait;

    public BasePOM(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
    }
}
