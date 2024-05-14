package am.aua.pom.searchresultpage;

import am.aua.pom.BasePOM;
import am.aua.pom.header.Header;
import am.aua.pom.item.Item;
import am.aua.pom.item.ItemLocators;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;

import static am.aua.pom.searchresultpage.SearchResultPageLocators.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class SearchResultPage extends BasePOM {
    private final Header header;

    public SearchResultPage(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
        header = new Header(driver, wait);
        wait.until(visibilityOfElementLocated(TITLE));
    }

    public Header getHeader() {
        return header;
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public List<Item> getItems() {
        return driver.findElements(ItemLocators.CONTAINER)
                .stream()
                .map(element -> new Item(driver, wait, element))
                .toList();
    }

    public String noResultsMessage() {
        try {
            return driver.findElement(NO_RESULT_MESSAGE).getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public SearchResultPage sortBy(String sortBy) {
        wait.until(visibilityOfElementLocated(SORT_DROPDOWN)).click();
        wait.until(visibilityOfAllElementsLocatedBy(SORT_DROPDOWN_ITEM))
                .stream()
                .filter(webElement -> webElement.getText().equalsIgnoreCase(sortBy))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No sort by " + sortBy))
                .click();


        return new SearchResultPage(driver, wait);
    }
}
