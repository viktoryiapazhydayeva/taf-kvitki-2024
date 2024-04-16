package by.itacademy.pazhydayeva.ui;

import by.itacademy.pazhydayeva.driver.Driver;
import by.itacademy.pazhydayeva.ui.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    WebDriver driver;
    HomePage homePage;

    @BeforeEach
    public void warmUp() {
        driver = Driver.getDriver();
        driver.get("https://www.kvitki.by/");
        homePage = new HomePage();
        homePage.acceptCookie();
        homePage.closeBanner();
        homePage.closeScrollBanner();
    }

    @AfterEach
    public void tearDown() {
        Driver.quitDriver();
    }
}
