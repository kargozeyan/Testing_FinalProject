package am.aua.pom.cartitem;

import org.openqa.selenium.By;

public class CartItemLocators {
    public static final By CONTAINER = By.cssSelector(".cart_items .combo_hover");
    static final By TITLE = By.className("item_name");
    static final By QUANTITY = By.cssSelector(".count_action > input");
    static final By PRICE = By.cssSelector(".item_price .price");
    static final By DELETE = By.cssSelector(".action.delete");
    static final By DECREASE = By.cssSelector(".count_action .decrease_btn");
    static final By INCREASE = By.cssSelector(".count_action .increase_btn");
}
