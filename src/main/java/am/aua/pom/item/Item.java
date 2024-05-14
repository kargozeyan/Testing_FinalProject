package am.aua.pom.item;

import am.aua.pom.BasePOM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;

import static am.aua.pom.item.ItemLocators.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public final class Item extends BasePOM {
    private final WebElement element;

    public Item(WebDriver driver, Wait<WebDriver> wait, WebElement element) {
        super(driver, wait);
        this.element = element;
    }

    public String getTitle() {
        return element.findElement(TITLE).getText();
    }

    public String getAuthor() {
        return element.findElement(AUTHOR).getText();
    }

    public int getPrice() {
        return Integer.parseInt(element
                .findElement(PRICE)
                .getAttribute(ItemAttributes.PRICE)
        );
    }

    public void addToCart() {
        new Actions(driver)
                .moveToElement(element.findElement(TITLE))
                .perform();

        WebElement addToCartButton = element.findElement(ADD_TO_CART);
        wait.until(visibilityOf(addToCartButton)).submit();
        wait.until(textToBePresentInElement(element.findElement(ADD_TO_CART), "Add to Cart"));
    }
}
