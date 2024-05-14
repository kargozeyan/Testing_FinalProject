package am.aua.pom.cart;

import am.aua.pom.BasePOM;
import am.aua.pom.cartitem.CartItem;
import am.aua.pom.cartitem.CartItemLocators;
import am.aua.utils.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;

import static am.aua.pom.cart.CartLocators.EMPTY_MESSAGE;
import static am.aua.pom.cart.CartLocators.TOTAL_PRICE;

public class Cart extends BasePOM {

    public Cart(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
    }

    public String getEmptyMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(EMPTY_MESSAGE)).getText();
    }

    public List<CartItem> getItems() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CartItemLocators.CONTAINER))
                .stream()
                .map(element -> new CartItem(driver, wait, element))
                .toList();
    }

    public Cart incrementItemAt(int index) {
        modifyWaitingForTotalPriceUpdate(() -> getItems().get(index).increment());
        return new Cart(driver, wait);
    }

    public Cart decrementItemAt(int index) {
        CartItem item = getItems().get(index);
        if (item.getQuantity() > 1) {
            modifyWaitingForTotalPriceUpdate(item::decrement);
        } else {
            item.decrement();
        }
        return new Cart(driver, wait);
    }

    private void modifyWaitingForTotalPriceUpdate(Runnable runnable) {
        int totalPrice = getTotalPrice();
        runnable.run();
        wait.until((driver) -> totalPrice != getTotalPrice());
    }

    public int getTotalPrice() {
        return getTotalPrice(this.driver);
    }

    public int getTotalPrice(WebDriver driver) {
        return StringUtils.extractPrice(driver.findElement(TOTAL_PRICE).getText());
    }
}
