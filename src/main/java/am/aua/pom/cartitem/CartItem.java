package am.aua.pom.cartitem;

import am.aua.pom.BasePOM;
import am.aua.pom.confirmmodal.ConfirmModal;
import am.aua.utils.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import static am.aua.pom.cartitem.CartItemLocators.*;

public class CartItem extends BasePOM {
    private final WebElement element;

    public CartItem(WebDriver driver, Wait<WebDriver> wait, WebElement element) {
        super(driver, wait);
        this.element = element;
    }

    public String getTitle() {
        return element.findElement(TITLE).getText();
    }

    public int getQuantity() {
        return Integer.parseInt(element
                .findElement(QUANTITY)
                .getAttribute(CartItemAttributes.QUANTITY));
    }

    public int getPrice() {
        return StringUtils.extractPrice(element.findElement(PRICE).getText());
    }

    public void increment() {
        int quantity = getQuantity();
        wait.until(ExpectedConditions.elementToBeClickable(
                element.findElement(INCREASE))).click();
        wait.until(((driver) -> getQuantity() != quantity));
    }

    public void decrement() {
        element.findElement(DECREASE).click();
    }

    public ConfirmModal delete() {
        wait.until(ExpectedConditions.elementToBeClickable(DELETE)).click();
        return new ConfirmModal(driver, wait);
    }
}
