package am.aua.pom.searchresultpage;

import org.openqa.selenium.By;

class SearchResultPageLocators {
    static final By TITLE = By.className("page_title");
    static final By NO_RESULT_MESSAGE = By.cssSelector(".message.notice");
    static final By SORT_DROPDOWN = By.cssSelector(".sorting_block .dropdown-toggle");
    static final By SORT_DROPDOWN_ITEM = By.cssSelector(".sorting_block .dropdown-item");
}
