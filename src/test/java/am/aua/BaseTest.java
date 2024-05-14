package am.aua;

import am.aua.dataprovider.BrowserProvider;
import am.aua.pom.homepage.HomePage;
import am.aua.utils.Resources;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;

public abstract class BaseTest {

    private WebDriver driver;
    private Wait<WebDriver> wait;

    protected HomePage homePage;

    @BeforeClass
    @Parameters("browser")
    public void beforeClass(String browser) throws MalformedURLException {
        driver = new RemoteWebDriver(new URL(Constants.REMOTE_DRIVER_URL), BrowserProvider.browsers.get(browser));
        driver.manage().window().maximize();

        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(50))
                .ignoring(ElementNotInteractableException.class);
    }

    @BeforeMethod
    public void setUp() {
        driver.get(Constants.APP_URL);

        homePage = new HomePage(driver, wait);
    }


    @AfterMethod
    public void tearDown(ITestResult result) {
        driver.manage().deleteAllCookies();
        if (result.getStatus() == ITestResult.FAILURE) {
            if (driver instanceof TakesScreenshot takesScreenshot) {
                byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
                Resources.saveScreenshot(screenshot, result.getName() + "_" + LocalDateTime.now() + ".png");
            }
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
