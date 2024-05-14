package am.aua.dataprovider;

import org.testng.annotations.DataProvider;

public class SearchDataProvider {

    @DataProvider(name = "titles")
    public static String[] bookNames() {
        return new String[]{
                "Harry Potter",
                "harry potter",
                "HARRY POTTER",
                "HarRy PotTeR"
        };
    }

    @DataProvider(name = "authors")
    public static String[] bookAuthors() {
        return new String[]{
                "Agatha Christie",
                "agatha christie",
                "AGATHA CHRISTIE",
                "AgAtHa ChRiStIe"
        };
    }
}
