package am.aua;

import am.aua.dataprovider.SearchDataProvider;
import am.aua.pom.header.Header;
import am.aua.pom.item.Item;
import am.aua.pom.searchresultpage.SearchResultPage;
import org.testng.annotations.Test;

import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.*;

public class SearchTest extends BaseTest {

    @Test(dataProvider = "titles", dataProviderClass = SearchDataProvider.class)
    public void testSearchByTitle(String query) {
        testSuccessResults(query, Item::getTitle);
    }

    @Test(dataProvider = "authors", dataProviderClass = SearchDataProvider.class)
    public void testSearchByAuthor(String query) {
        testSuccessResults(query, Item::getAuthor);
    }

    @Test
    public void testSearchNoResultFound() {
        final String query = "NOTFOUND";
        Header header = homePage.getHeader();
        header.inputSearch(query);
        SearchResultPage searchResultPage = header.submitSearch();

        String pageTitle = searchResultPage.getTitle();
        assertThat(pageTitle).isEqualToIgnoringCase("Search results for: '%s'".formatted(query));

        List<Item> results = searchResultPage.getItems();
        assertThat(results).isEmpty();

        String noResultMessage = searchResultPage.noResultsMessage();
        assertThat(noResultMessage).isEqualToIgnoringCase("Your search returned no results.");
    }

    private void testSuccessResults(String query, Function<Item, String> mapper) {
        Header header = homePage.getHeader();
        header.inputSearch(query);
        SearchResultPage searchResultPage = header.submitSearch();

        String pageTitle = searchResultPage.getTitle();
        assertThat(pageTitle).isEqualToIgnoringCase("Search results for: '%s'".formatted(query));

        String noResultMessage = searchResultPage.noResultsMessage();
        assertThat(noResultMessage).isNull();

        List<Item> results = searchResultPage.getItems();
        assertThat(results)
                .map(mapper)
                .map(String::toLowerCase)
                .allMatch(title -> title.contains(query.toLowerCase()));
    }
}
