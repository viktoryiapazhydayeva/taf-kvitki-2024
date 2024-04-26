package by.itacademy.pazhydayeva.ui.pages;

import by.itacademy.pazhydayeva.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class UpdateAccountPage {

    private WebDriver driver;
    private static final String UPDATE_CONTACT_DETAILS_BTN_XPATH = "//button[@class='ng-tns-c10-10']";
    private static final String FORENAME_FIELD_NAME = "firstName";
    private static final String SURNAME_FIELD_NAME = "lastName";
    private static final String FIELD_ERROR_SELECTOR = ".form-field-error";
    private static final String CONFIRM_BTN_XPATH = "//div[@role='region']//app-ui-button/button";
    private static final String CONFIRMATION_MSG_XPATH = "//div[@class='info-text']";
    private static final String COUNTRY_DDL_XPATH = "//span[@class='select-value']";
    private static final String COUNTRY_SEARCH_FIELD_XPATH = "//input[@class='dropdown-search-input']";
    private static final String COUNTRY_ITEM_XPATH = "//li[@class='list-item ng-star-inserted']";
    private static final String CURRENT_COUNTRY_XPATH = "//span[@class='select-value']";
    public static final String EMPTY_FORENAME_VALIDATION_ERROR = "Пожалуйста, заполните поле (Имя)";
    public static final String EMPTY_SURNAME_VALIDATION_ERROR = "Пожалуйста, заполните поле (Фамилия)";
    public static final String SUCCESS_CONFIRMATION_MSG = "Данные сохранены";

    public UpdateAccountPage() {
        this.driver = Driver.getDriver();
    }

    public void clickUpdateContactDetailsBtn() {
        WebElement updateContactDetailsBtn = driver.findElement(By.xpath(UPDATE_CONTACT_DETAILS_BTN_XPATH));
        updateContactDetailsBtn.click();
    }

    public void removeForenameValue() {
        WebElement forenameField = driver.findElement(By.name(FORENAME_FIELD_NAME));
        String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;
        forenameField.sendKeys(deleteString);
    }

    public void removeSurnameValue() {
        WebElement surnameField = driver.findElement(By.name(SURNAME_FIELD_NAME));
        String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;
        surnameField.sendKeys(deleteString);
    }

    public void enterNewForename(String newForename) {
        WebElement forenameField = driver.findElement(By.name(FORENAME_FIELD_NAME));
        forenameField.sendKeys(newForename);
    }

    public void enterNewSurname(String newSurname) {
        WebElement surnameField = driver.findElement(By.name(SURNAME_FIELD_NAME));
        surnameField.sendKeys(newSurname);
    }

    public void clickConfirmBtn() {
        WebElement confirmButton = driver.findElement(By.xpath(CONFIRM_BTN_XPATH));
        new Actions(driver)
                .scrollToElement(confirmButton)
                .perform();
        confirmButton.click();
    }

    public String getConfirmationMsgText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CONFIRMATION_MSG_XPATH)));

        WebElement confirmationMessage = driver.findElement(By.xpath(CONFIRMATION_MSG_XPATH));
        return confirmationMessage.getText();
    }

    public void clickOnSurnameField() {
        WebElement surnameField = driver.findElement(By.name(SURNAME_FIELD_NAME));
        surnameField.click();
    }

    public void clickOnForenameField() {
        WebElement forenameField = driver.findElement(By.name(FORENAME_FIELD_NAME));
        forenameField.click();
    }

    public String getEmptyFieldErrorText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(FIELD_ERROR_SELECTOR)));

        WebElement emptyForenameValidationError = driver.findElement(By.cssSelector(FIELD_ERROR_SELECTOR));
        return emptyForenameValidationError.getText();
    }

    public void expandCountryDdl() {
        WebElement countryDdl = driver.findElement(By.xpath(COUNTRY_DDL_XPATH));
        countryDdl.click();
    }

    public void searchCountry(String countryName) {
        WebElement countrySearchField = driver.findElement(By.xpath(COUNTRY_SEARCH_FIELD_XPATH));
        countrySearchField.sendKeys(countryName);
    }

    public void selectSearchedCountryItem() {
        WebElement countrySearchedResult = driver.findElement(By.xpath(COUNTRY_ITEM_XPATH));
        countrySearchedResult.click();
    }

    public List<WebElement> getCountryItemsList() {
        return driver.findElements(By.xpath(COUNTRY_ITEM_XPATH));
    }

    public List<String> getCountryNamesTextList() {
        List<String> countryNames = new ArrayList<>();
        for (WebElement country : getCountryItemsList()) {
            countryNames.add(country.getText());
        }
        return countryNames;
    }

    public String getCurrentCountryName() {
        WebElement currentCountry = driver.findElement(By.xpath(CURRENT_COUNTRY_XPATH));
        return currentCountry.getText();
    }

    public void selectNewCountry() {
        int i;
        for (i = 0; i < getCountryItemsList().size(); i++) {
            if (!getCurrentCountryName().equals(getCountryItemsList().get(i).getText())) {
                getCountryItemsList().get(i).click();
                return;
            }
        }
    }
}
