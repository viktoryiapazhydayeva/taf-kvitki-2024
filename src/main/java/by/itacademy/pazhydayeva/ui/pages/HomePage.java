package by.itacademy.pazhydayeva.ui.pages;

import by.itacademy.pazhydayeva.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {
    private WebDriver driver;
    private static final String COOKIE_BTN_XPATH = "//button[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']";
    private static final String BANNER_XPATH = "//div[@class='popupbanner_close']";
    private static final String SCROLL_BANNER_XPATH = "//div[@class='scrollbanners_close']";
    private static final String COPY_RIGHT_XPATH = "//div[@class='footer_copyright footer_article-30633']/div/div[1]";
    private static final String ACCOUNT_ICON_NAME = "user-account-link";
    private static final String SEARCH_FIELD_NAME = "search";

    //public final String SEARCH_BTN_XPATH = "//a[@class='iconpl-search search_component_button']"; // ПОКА НЕ ИСПОЛЬЗУЕТСЯ

    public HomePage() {
        this.driver = Driver.getDriver();
    }

    public void acceptCookie() {
        WebElement cookieDialogBtn = driver.findElement(By.xpath(COOKIE_BTN_XPATH));
        if (cookieDialogBtn.isDisplayed()) {
            cookieDialogBtn.click();
        }
    }

    public void closeBanner() {
        List<WebElement> bannerPopUp = driver.findElements(By.xpath(BANNER_XPATH));
        if (!bannerPopUp.isEmpty() && bannerPopUp.get(0).isDisplayed()) {
            bannerPopUp.get(0).click();
        }
    }

    public void closeScrollBanner() {
        List<WebElement> scrollBanner = driver.findElements(By.xpath(SCROLL_BANNER_XPATH));
        if (!scrollBanner.isEmpty() && scrollBanner.get(0).isDisplayed()) {
            scrollBanner.get(0).click();
        }
    }

    public boolean getCopyRightText(String text) {
        String copyRightText = driver.findElement(By.xpath(COPY_RIGHT_XPATH)).getText();
        return copyRightText.contains(text);
    }

    public void openLoginForm() {
        WebElement loginIcon = driver.findElement(By.name(ACCOUNT_ICON_NAME));
        loginIcon.click();
    }

    public void searchItem(String query) {
        WebElement searchField = driver.findElement(By.name(SEARCH_FIELD_NAME));
        searchField.sendKeys(query);
    }
}
