package by.itacademy.pazhydayeva.ui;

import by.itacademy.pazhydayeva.ui.pages.HomePage;
import by.itacademy.pazhydayeva.ui.pages.SearchPage;
import by.itacademy.pazhydayeva.utils.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("UI")
@Tag("Search")
public class SearchUiTest extends BaseTest {
    private static final String EXPECTED_NO_SEARCH_RESULT_MSG = "По вашему запросу ничего не найдено";
    private HomePage homePage = new HomePage();
    private SearchPage searchPage = new SearchPage();

    @Test
    @DisplayName("No search results")
    public void testSearchWithNoResults() {
        homePage.searchItem(Util.getRandomString(5));
        Assertions.assertEquals(EXPECTED_NO_SEARCH_RESULT_MSG, searchPage.getSearchMsgText());
    }

    @Test
    @DisplayName("Success search")
    public void testSearch() {
        homePage.searchItem("славянский базар");
        Assertions.assertTrue(searchPage.ifSearchedItemContainsQueryText("славянский базар"));
    }
}
