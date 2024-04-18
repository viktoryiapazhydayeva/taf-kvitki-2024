package by.itacademy.pazhydayeva.ui.pages;

import by.itacademy.pazhydayeva.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage {

    private WebDriver driver;
    private static final String NO_SEARCH_RESULT_MSG_XPATH = "//div[@class='search_results_no_results_msg']";
    private static final String ITEM_SEARCHED_TITLE_XPATH = "//a[@data-event-id=422389]/span/span[1]/span[2]/span[@class='searchresult_foundword']";
    private static final String ITEM_SHORT_TITLE_XPATH = "//span[@class='event_short_title']/span[contains(text(), 'СЛАВЯНСКИЙ')]/..";

    public SearchPage() {
        this.driver = Driver.getDriver();
    }

    public String getSearchMsgText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(NO_SEARCH_RESULT_MSG_XPATH)));
        WebElement searchResultMessage = driver.findElement(By.xpath(NO_SEARCH_RESULT_MSG_XPATH));
        return searchResultMessage.getText();
    }

    public boolean ifSearchedItemContainsQueryText(String text) {
        WebElement searchedItemShortTitle = driver.findElement(By.xpath(ITEM_SHORT_TITLE_XPATH));
        new Actions(driver)
                .moveToElement(searchedItemShortTitle)
                .perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ITEM_SEARCHED_TITLE_XPATH)));

        List<WebElement> searchedResultsText = driver.findElements(By.xpath(ITEM_SEARCHED_TITLE_XPATH));
        String searchedItemTitleText = searchedResultsText.get(0).getText() + " " + searchedResultsText.get(1).getText();
        return searchedItemTitleText.toUpperCase().contains(text.toUpperCase());
    }
}
