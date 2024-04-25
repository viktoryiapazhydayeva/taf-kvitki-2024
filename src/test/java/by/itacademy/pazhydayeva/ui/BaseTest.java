package by.itacademy.pazhydayeva.ui;

import by.itacademy.pazhydayeva.driver.Driver;
import by.itacademy.pazhydayeva.ui.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    HomePage homePage;

    @BeforeEach
    public void warmUp() {
        homePage = new HomePage();
        homePage.open();
        homePage.acceptCookie();
        homePage.closeBanner();
        homePage.closeScrollBanner();
    }

    @AfterEach
    public void tearDown() {
        Driver.quitDriver();
    }
}
