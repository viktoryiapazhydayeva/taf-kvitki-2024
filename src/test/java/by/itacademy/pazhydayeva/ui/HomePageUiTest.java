package by.itacademy.pazhydayeva.ui;

import by.itacademy.pazhydayeva.ui.pages.HomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("UI")
@Tag("HomePage")
public class HomePageUiTest extends BaseTest {

    @Test
    @DisplayName("Home Page rendering")
    public void testHomePage() {
        HomePage homePage = new HomePage();
        Assertions.assertTrue(homePage.getCopyRightText("© 2024 ООО \"Квитки Бел\""));
    }
}
