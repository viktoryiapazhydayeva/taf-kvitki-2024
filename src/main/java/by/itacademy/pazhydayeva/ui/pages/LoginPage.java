package by.itacademy.pazhydayeva.ui.pages;

import by.itacademy.pazhydayeva.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private static final String LOGIN_TAB_TITLE_XPATH = "//span[@class='mdc-tab__text-label']";
    private static final String EMAIL_FIELD_NAME = "email";
    private static final String PASSWORD_FIELD_NAME = "password";
    private static final String LOGIN_BTN_XPATH = "//button[@class='ng-tns-c28-4']";
    private static final String ERROR_MSG_XPATH = "//div[@class='info-text']";
    private static final String USER_EMAIL_ON_ACCOUNT_PAGE_XPATH = "//div[@class='account-side-menu-account-email']";

    public LoginPage() {
        this.driver = Driver.getDriver();
    }

    public String getLoginTabTitleText() {
        WebElement loginTabTitle = driver.findElement(By.xpath(LOGIN_TAB_TITLE_XPATH));
        return loginTabTitle.getText();
    }

    public void enterEmail(String email) {
        WebElement emailField = driver.findElement(By.name(EMAIL_FIELD_NAME));
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(By.name(PASSWORD_FIELD_NAME));
        passwordField.sendKeys(password);
    }

    public void confirmLogin() {
        WebElement loginBtn = driver.findElement(By.xpath(LOGIN_BTN_XPATH));
        loginBtn.click();
    }

    public String getLoginErrorMsgText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ERROR_MSG_XPATH)));

        WebElement loginErrorMsg = driver.findElement(By.xpath(ERROR_MSG_XPATH));
        return loginErrorMsg.getText();
    }

    public String getLoggedUserEmailFromAccount() {
        WebElement loggedUserEmail = driver.findElement(By.xpath(USER_EMAIL_ON_ACCOUNT_PAGE_XPATH));
        return loggedUserEmail.getText();
    }
}
