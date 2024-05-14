package am.aua.pom.header;

import org.openqa.selenium.By;

class HeaderLocators {
    static final By SEARCH_INPUT = By.id("search");
    static final By SEARCH_SUBMIT = By.cssSelector("#search_mini_form button[type='submit']");
    static final By CART = By.cssSelector(".action_btn.showcart");
}
