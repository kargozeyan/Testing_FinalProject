package am.aua;

import am.aua.pom.header.Header;
import am.aua.pom.item.Item;
import am.aua.pom.searchresultpage.SearchResultPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SortTest extends BaseTest {
    private SearchResultPage searchResultPage;

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();

        Header header = homePage.getHeader();
        header.inputSearch("harry potter");
        searchResultPage = header.submitSearch();
    }

    @Test
    public void testSortingByTitle() {
        searchResultPage = searchResultPage.sortBy("Name");
        List<Item> results = searchResultPage.getItems();
        assertThat(results).isSortedAccordingTo(Comparator.comparing(Item::getTitle));
    }

    @Test
    public void testSortingByPrice() {
        searchResultPage = searchResultPage.sortBy("Price");
        List<Item> results = searchResultPage.getItems();
        assertThat(results).isSortedAccordingTo(Comparator.comparingInt(Item::getPrice));
    }
}
