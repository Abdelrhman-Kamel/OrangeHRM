package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    public final By loginHeader = By.xpath("//h5[@class='oxd-text oxd-text--h5 orangehrm-login-title']");
    private final By userNameField = By.name("username");
    private final By passwordField = By.name("password");
    private final By loginButton = By.xpath("//button[@type='submit']");
    private final By errorMessage = By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']");
    private final By dashboard = By.xpath("//h6[text()='Dashboard']");
    private final By usernameRequired = By.xpath("(//span[contains(@class, 'oxd-input-field-error-message') and text()='Required'])[1]");
    private final By passwordRequired = By.xpath("(//span[contains(@class, 'oxd-input-field-error-message') and text()='Required'])[2]");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void login(String user, String pass) {
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(userNameField));
        username.sendKeys(user);
        driver.findElement(passwordField).sendKeys(pass, Keys.ENTER);
    }

    public void clickLoginButton() {
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginBtn.click();
    }

    public By getDashboard() {
        return dashboard;
    }

    public By getErrorMessage() {
        return errorMessage;
    }

    public By getUsernameRequired() {
        return usernameRequired;
    }

    public By getPasswordRequired() {
        return passwordRequired;
    }
}
