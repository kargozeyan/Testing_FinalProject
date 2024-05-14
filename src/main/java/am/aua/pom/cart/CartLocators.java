package am.aua.pom.cart;

import org.openqa.selenium.By;

class CartLocators {
    static final By EMPTY_MESSAGE = By.cssSelector("#minicart-content-wrapper .subtitle.empty");
    static final By TOTAL_PRICE = By.cssSelector(".subtotal .price");
}
