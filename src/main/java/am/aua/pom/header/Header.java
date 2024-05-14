package am.aua.pom.header;

import am.aua.pom.BasePOM;
import am.aua.pom.cart.Cart;
import am.aua.pom.searchresultpage.SearchResultPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

import static am.aua.pom.header.HeaderLocators.*;

public class Header extends BasePOM {

    public Header(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
    }

    public void inputSearch(String query) {
        driver.findElement(SEARCH_INPUT).sendKeys(query);
    }

    public SearchResultPage submitSearch() {
        driver.findElement(SEARCH_SUBMIT).submit();
        return new SearchResultPage(driver, wait);
    }

    public Cart openCart() {
        driver.findElement(CART).click();
        return new Cart(driver, wait);
    }
}
