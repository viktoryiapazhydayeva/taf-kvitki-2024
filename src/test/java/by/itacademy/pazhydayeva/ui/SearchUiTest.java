package by.itacademy.pazhydayeva.ui;

import by.itacademy.pazhydayeva.ui.pages.SearchPage;
import by.itacademy.pazhydayeva.utils.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static by.itacademy.pazhydayeva.ui.pages.SearchPage.EXPECTED_NO_SEARCH_RESULT_MSG;

@Tag("UI")
@Tag("Search")
public class SearchUiTest extends BaseTest {

    @Test
    @DisplayName("No search results")
    public void testSearchWithNoResults() {
        homePage.searchItem(Util.getRandomString(5));
        SearchPage searchPage = new SearchPage();
        Assertions.assertEquals(EXPECTED_NO_SEARCH_RESULT_MSG, searchPage.getSearchMsgText());
    }

    @Test
    @DisplayName("Success search")
    public void testSearch() {
        String searchQuery = "славянский базар";
        homePage.searchItem(searchQuery);
        SearchPage searchPage = new SearchPage();
        Assertions.assertTrue(searchPage.ifSearchedItemContainsQueryText(searchQuery));
    }
}
