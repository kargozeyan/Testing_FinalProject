package am.aua.pom.item;

import org.openqa.selenium.By;

public class ItemLocators {
    public static final By CONTAINER = By.className("product_inner");
    static final By TITLE = By.className("product_name");
    static final By AUTHOR = By.className("product_author");
    static final By PRICE = By.cssSelector(".product_price .price-wrapper");
    static final By ADD_TO_CART = By.cssSelector(".btn_add.tocart");
}
